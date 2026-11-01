import tempfile
import unittest
from pathlib import Path
from starter.core import Tools, ScriptedModel, run


class CoreTest(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.root = Path(self.temp.name)
        (self.root / "a.go").write_text("package demo\n")
        self.tools = Tools(self.root)

    def tearDown(self):
        self.temp.cleanup()

    def test_protocol_demo(self):
        out = run(ScriptedModel(), self.tools, "inspect")
        self.assertEqual(out["status"], "completed")
        self.assertEqual([m["id"] for m in out["history"] if m["role"] == "tool"], ["list_1", "read_1"])

    def test_path_and_secret_rejected(self):
        (self.root / ".env").write_text("secret")
        for path in ["../outside.go", str(self.root / "a.go"), ".env"]:
            self.assertFalse(self.tools.call("read_file", {"path": path})["ok"])

    def test_symlink_escape(self):
        with tempfile.TemporaryDirectory() as other:
            outside = Path(other) / "secret.go"
            outside.write_text("secret")
            (self.root / "link.go").symlink_to(outside)
            self.assertFalse(self.tools.call("read_file", {"path": "link.go"})["ok"])

    def test_output_truncation(self):
        (self.root / "big.go").write_text("x" * 12001)
        result = self.tools.call("read_file", {"path": "big.go"})
        self.assertTrue(result["truncated"])
        self.assertEqual(len(result["text"]), 12000)

    def test_step_limit(self):
        result = run(ScriptedModel(), self.tools, "inspect", max_steps=1)
        self.assertEqual(result["status"], "step_limit")

    def test_multiple_tool_results(self):
        def model(history):
            if len(history) == 1:
                return {"calls": [{"id": "a", "name": "list_files", "arguments": {}},
                                  {"id": "b", "name": "missing", "arguments": {}}]}
            self.assertEqual([m["id"] for m in history if m["role"] == "tool"], ["a", "b"])
            self.assertFalse(history[-1]["result"]["ok"])
            return {"final": "done"}
        self.assertEqual(run(model, self.tools, "inspect")["status"], "completed")

    def test_duplicate_ids_are_protocol_error(self):
        call = {"id": "same", "name": "list_files", "arguments": {}}
        self.assertEqual(run(lambda _: {"calls": [call, call]}, self.tools, "inspect")["status"], "provider_error")

    def test_invalid_arguments(self):
        self.assertFalse(self.tools.call("read_file", {"path": 12})["ok"])
        self.assertFalse(self.tools.call("list_files", {"extra": 1})["ok"])


if __name__ == "__main__":
    unittest.main()
