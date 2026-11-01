"""Grade a candidate inside Docker, using external tests. Never calls a model."""
import argparse
import hashlib
import json
import os
from pathlib import Path
import selectors
import shutil
import subprocess
import tempfile
import time
import uuid

BASE = Path(__file__).resolve().parent


def tasks():
    return json.loads((BASE / "tasks.json").read_text())


def prepare(candidate, target):
    """Only copy root Go source; do not trust candidate go.mod or tests."""
    candidate, target = Path(candidate).resolve(), Path(target)
    sources = list(candidate.glob("*.go"))
    if not sources or len(sources) > 50:
        raise ValueError("candidate must contain 1..50 root Go files")
    for path in sources:
        if path.is_symlink() or path.stat().st_size > 200000:
            raise ValueError("symlink or oversized source is not allowed")
        if not path.name.endswith("_test.go"):
            shutil.copyfile(path, target / path.name)
    (target / "go.mod").write_text("module example.com/agentlab\n\ngo 1.23\n")
    shutil.copyfile(BASE / "acceptance_test.go", target / "acceptance_test.go")
    shutil.copyfile(BASE.parent / "fixtures/go-service/service_test.go", target / "baseline_test.go")
    target.chmod(0o755)
    for path in target.iterdir():
        path.chmod(0o644)


def source_hash(candidate):
    h = hashlib.sha256()
    for path in sorted(Path(candidate).glob("*.go")):
        if not path.is_symlink() and not path.name.endswith("_test.go"):
            h.update(path.name.encode())
            h.update(b"\0")
            h.update(path.read_bytes())
    return h.hexdigest()


def bounded_run(argv, timeout=90, output_limit=100000):
    started = time.monotonic()
    process = subprocess.Popen(argv, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    data = bytearray()
    status = "finished"
    selector = selectors.DefaultSelector()
    selector.register(process.stdout, selectors.EVENT_READ)
    try:
        while selector.get_map():
            if time.monotonic() - started > timeout:
                status = "timeout"
                break
            ready = selector.select(timeout=0.1)
            for key, _ in ready:
                chunk = os.read(key.fd, 4096)
                if not chunk:
                    selector.unregister(key.fileobj)
                    continue
                available = max(0, output_limit - len(data))
                data.extend(chunk[:available])
                if len(chunk) > available:
                    status = "output_limit"
                    break
            if status != "finished":
                break
        if status != "finished":
            process.kill()
        try:
            process.wait(timeout=max(0.1, timeout - (time.monotonic() - started)))
        except subprocess.TimeoutExpired:
            status = "timeout"
            process.kill()
            process.wait()
    finally:
        selector.close()
        process.stdout.close()
        if process.poll() is None:
            process.kill()
            process.wait()
    return process.returncode, status, data.decode("utf-8", errors="replace"), time.monotonic() - started


def grade(candidate, task_id, image="golang:1.23-bookworm"):
    task = next((t for t in tasks() if t["id"] == task_id), None)
    if task is None:
        raise ValueError("unknown task")
    if shutil.which("docker") is None:
        raise RuntimeError("Docker is required; grading never falls back to host execution")
    name = "agentlab-grade-" + uuid.uuid4().hex[:12]
    digest = subprocess.run(["docker", "image", "inspect", image, "--format", "{{.Id}}"],
                            capture_output=True, text=True, timeout=15)
    if digest.returncode:
        raise RuntimeError("Docker image is unavailable; run docker pull first")
    with tempfile.TemporaryDirectory(prefix="agentlab-grade-") as temp:
        prepare(candidate, temp)
        command = ["docker", "run", "--rm", "--pull", "never", "--name", name,
                   "--network", "none", "--read-only", "--cap-drop", "ALL",
                   "--security-opt", "no-new-privileges", "--pids-limit", "128",
                   "--memory", "1g", "--cpus", "2", "--user", "65534:65534",
                   "--tmpfs", "/tmp:rw,nosuid,size=512m,mode=1777", "-e", "GOCACHE=/tmp/go-cache",
                   "-e", "GOPATH=/tmp/go", "-e", "GOTOOLCHAIN=local", "-e", "GOPROXY=off",
                   "-e", "GOMAXPROCS=2", "-e", "GOFLAGS=-p=2",
                   "--mount", "type=bind,src=" + temp + ",dst=/work,readonly",
                   "-w", "/work", image, "go", "test", "-count=1", "-timeout=20s",
                   "-run", "^(TestBaseline|" + task["test"] + ")$", "./..."]
        try:
            code, status, output, seconds = bounded_run(command)
        finally:
            subprocess.run(["docker", "rm", "-f", name], stdout=subprocess.DEVNULL,
                           stderr=subprocess.DEVNULL, timeout=15)
    return {"task_id": task_id, "source_hash": source_hash(candidate),
            "success": status == "finished" and code == 0, "exit_code": code,
            "status": status, "wall_seconds": round(seconds, 3),
            "image": image, "image_id": digest.stdout.strip(), "output": output}


def main():
    p = argparse.ArgumentParser(description=__doc__)
    p.add_argument("--candidate", required=True)
    p.add_argument("--task", required=True, choices=[t["id"] for t in tasks()])
    p.add_argument("--image", default="golang:1.23-bookworm")
    args = p.parse_args()
    try:
        result = grade(args.candidate, args.task, args.image)
    except (OSError, ValueError, RuntimeError, subprocess.SubprocessError) as e:
        print(json.dumps({"status": "grader_error", "success": None, "error": str(e)}))
        return 2
    print(json.dumps(result, ensure_ascii=False))
    return 0 if result["success"] else 1


if __name__ == "__main__":
    raise SystemExit(main())
