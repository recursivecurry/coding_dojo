import sys
import tempfile
import unittest
from pathlib import Path
from evals.grade import bounded_run, prepare, tasks


class GraderTest(unittest.TestCase):
    def test_manifest(self):
        cases = tasks()
        self.assertEqual(len(cases), 6)
        self.assertEqual(sum(t["split"] == "dev" for t in cases), 4)
        self.assertEqual(len({t["id"] for t in cases}), 6)

    def test_external_tests_replace_candidate_tests(self):
        with tempfile.TemporaryDirectory() as src, tempfile.TemporaryDirectory() as dst:
            p = Path(src)
            (p / "service.go").write_text("package service")
            (p / "fake_test.go").write_text("untrusted")
            (p / "go.mod").write_text("untrusted")
            prepare(src, dst)
            self.assertFalse((Path(dst) / "fake_test.go").exists())
            self.assertIn("TestEmptyIDs", (Path(dst) / "acceptance_test.go").read_text())
            self.assertNotIn("untrusted", (Path(dst) / "go.mod").read_text())

    def test_source_symlink_rejected(self):
        with tempfile.TemporaryDirectory() as src, tempfile.TemporaryDirectory() as dst:
            p = Path(src)
            (p / "target.txt").write_text("package service")
            (p / "service.go").symlink_to(p / "target.txt")
            with self.assertRaises(ValueError):
                prepare(src, dst)

    def test_bounded_output(self):
        code, status, output, _ = bounded_run([sys.executable, "-c", "print('x'*10000)"], output_limit=100)
        self.assertEqual(status, "output_limit")
        self.assertLessEqual(len(output), 100)

    def test_timeout(self):
        _, status, _, _ = bounded_run([sys.executable, "-c", "import time; time.sleep(2)"], timeout=0.1)
        self.assertEqual(status, "timeout")


if __name__ == "__main__":
    unittest.main()
