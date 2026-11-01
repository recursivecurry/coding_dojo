import argparse
import json
import sys
from .core import Tools, ScriptedModel, run


def main():
    parser = argparse.ArgumentParser(description="Read-only scripted agent demo; no API calls")
    parser.add_argument("--repo", required=True)
    parser.add_argument("--task", required=True)
    parser.add_argument("--max-steps", type=int, default=8)
    args = parser.parse_args()
    try:
        result = run(ScriptedModel(), Tools(args.repo), args.task, max_steps=args.max_steps,
                     emit=lambda event: print(json.dumps(event, ensure_ascii=False)))
    except (OSError, ValueError) as e:
        parser.error(str(e))
    print(json.dumps({"status": result["status"], "final": result.get("final")}, ensure_ascii=False))
    return 0 if result["status"] == "completed" else 2


if __name__ == "__main__":
    sys.exit(main())
