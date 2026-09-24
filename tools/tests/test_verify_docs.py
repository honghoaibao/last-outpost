import sys
import tempfile
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

import verify_docs as vd  # noqa: E402

REPO_ROOT = Path(__file__).resolve().parents[2]


class VerifyDocsTests(unittest.TestCase):
    def setUp(self):
        self._tmp = tempfile.TemporaryDirectory()
        self.dir = Path(self._tmp.name) / "design"
        (self.dir / "01_CANONICAL").mkdir(parents=True)
        (self.dir / "01_CANONICAL" / "a.md").write_text("alpha\n", encoding="utf-8")
        (self.dir / "b.md").write_text("beta\n", encoding="utf-8")
        self.assertEqual(vd.main(["--design-dir", str(self.dir), "--update"]), 0)

    def tearDown(self):
        self._tmp.cleanup()

    def run_verify(self):
        return vd.main(["--design-dir", str(self.dir)])

    def test_clean_state_passes(self):
        self.assertEqual(self.run_verify(), 0)

    def test_modified_file_fails(self):
        (self.dir / "b.md").write_text("beta changed\n", encoding="utf-8")
        self.assertEqual(self.run_verify(), 1)

    def test_added_file_fails(self):
        (self.dir / "c.md").write_text("new\n", encoding="utf-8")
        self.assertEqual(self.run_verify(), 1)

    def test_deleted_file_fails(self):
        (self.dir / "b.md").unlink()
        self.assertEqual(self.run_verify(), 1)

    def test_update_accepts_change(self):
        (self.dir / "b.md").write_text("beta changed\n", encoding="utf-8")
        self.assertEqual(vd.main(["--design-dir", str(self.dir), "--update"]), 0)
        self.assertEqual(self.run_verify(), 0)

    def test_crlf_is_normalized(self):
        (self.dir / "b.md").write_bytes(b"beta\r\n")
        self.assertEqual(self.run_verify(), 0)

    def test_missing_manifest_fails(self):
        (self.dir / vd.MANIFEST_NAME).unlink()
        self.assertEqual(self.run_verify(), 1)


class RepositoryDocsTests(unittest.TestCase):
    def test_repo_design_docs_match_manifest(self):
        self.assertEqual(vd.main(["--design-dir", str(REPO_ROOT / "docs" / "design")]), 0)


if __name__ == "__main__":
    unittest.main()
