"""Minimal loop: no real model, editing, shell, checkpoint, or budget accounting."""
import json
import time
from pathlib import Path


class Tools:
    allowed = {".go", ".mod", ".md", ".txt", ".json"}

    def __init__(self, root):
        self.root = Path(root).resolve(strict=True)
        if not self.root.is_dir():
            raise ValueError("repo must be a directory")

    def path(self, relative):
        if not isinstance(relative, str) or not relative:
            raise ValueError("path must be a nonempty string")
        p = Path(relative)
        if p.is_absolute() or ".." in p.parts or any(x.startswith(".") for x in p.parts):
            raise ValueError("path is outside allowed workspace")
        resolved = (self.root / p).resolve(strict=True)
        if not resolved.is_relative_to(self.root) or not resolved.is_file():
            raise ValueError("file is outside allowed workspace")
        if resolved.suffix not in self.allowed:
            raise ValueError("file type is not allowed")
        return resolved

    def call(self, name, arguments):
        try:
            if not isinstance(arguments, dict):
                raise ValueError("arguments must be an object")
            if name == "list_files":
                if arguments:
                    raise ValueError("list_files accepts no arguments")
                files = []
                for p in self.root.rglob("*"):
                    rel = p.relative_to(self.root)
                    if p.is_symlink() or any(x.startswith(".") for x in rel.parts):
                        continue
                    if p.is_file() and p.suffix in self.allowed:
                        files.append(str(rel))
                files.sort()
                return {"ok": True, "files": files[:200], "truncated": len(files) > 200}
            if name == "read_file":
                if set(arguments) != {"path"}:
                    raise ValueError("read_file requires only path")
                with self.path(arguments["path"]).open(encoding="utf-8") as f:
                    text = f.read(12001)
                return {"ok": True, "text": text[:12000], "truncated": len(text) > 12000}
            raise ValueError("unknown tool: " + str(name))
        except (OSError, ValueError, TypeError, UnicodeError) as e:
            return {"ok": False, "error": str(e)}


def run(model, tools, task, *, max_steps=8, emit=None):
    if max_steps < 1:
        raise ValueError("max_steps must be positive")
    history = [{"role": "user", "text": task}]
    events = []
    started = time.monotonic()

    def record(kind, **fields):
        event = {"type": kind, "elapsed_seconds": round(time.monotonic() - started, 6), **fields}
        events.append(event)
        if emit:
            emit(event)

    for step in range(max_steps):
        record("model_start", step=step)
        try:
            response = model(history)
            if not isinstance(response, dict):
                raise ValueError("response must be an object")
            calls = response.get("calls", [])
            if not isinstance(calls, list) or len(calls) > 16:
                raise ValueError("calls must be a list of at most 16 entries")
            if calls:
                ids = []
                for call in calls:
                    if not isinstance(call, dict) or not all(k in call for k in ("id", "name", "arguments")):
                        raise ValueError("invalid tool call")
                    if not isinstance(call["id"], str) or not call["id"]:
                        raise ValueError("tool call id must be a nonempty string")
                    ids.append(call["id"])
                if len(set(ids)) != len(ids):
                    raise ValueError("duplicate tool call id")
            elif not isinstance(response.get("final"), str):
                raise ValueError("response needs calls or final text")
        except Exception as e:
            record("provider_error", step=step, error=type(e).__name__)
            return {"status": "provider_error", "history": history, "events": events}
        history.append({"role": "assistant", "response": response})
        record("model_response", step=step, response=response)
        if calls:
            for call in calls:
                result = tools.call(call["name"], call["arguments"])
                history.append({"role": "tool", "id": call["id"], "result": result})
                record("tool_result", step=step, id=call["id"], name=call["name"], result=result)
        else:
            record("stop", reason="completed")
            return {"status": "completed", "final": response["final"], "history": history, "events": events}
    record("stop", reason="step_limit")
    return {"status": "step_limit", "history": history, "events": events}


class ScriptedModel:
    """A deterministic protocol demo, NOT a language model."""
    def __call__(self, history):
        observations = [m for m in history if m["role"] == "tool"]
        if not observations:
            return {"calls": [{"id": "list_1", "name": "list_files", "arguments": {}}]}
        if len(observations) == 1:
            files = observations[0]["result"].get("files", [])
            if files:
                return {"calls": [{"id": "read_1", "name": "read_file", "arguments": {"path": files[0]}}]}
        return {"final": "시뮬레이터가 도구 루프를 완료했습니다. 실제 코드 분석·수정은 수행하지 않았습니다."}
