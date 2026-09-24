import sys
import tempfile
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

import check_version as cv  # noqa: E402

REPO_ROOT = Path(__file__).resolve().parents[2]
GOOD = """BUILD_LABEL=2026.09 beta-1
VERSION_NAME=2026.09-beta.1
VERSION_CODE=2026090101
RELEASE_CHANNEL=beta
"""


class CheckVersionTests(unittest.TestCase):
    def setUp(self):
        self._tmp = tempfile.TemporaryDirectory()
        self.root = Path(self._tmp.name)
        self.props(GOOD)
        (self.root / "CHANGELOG.md").write_text("## [2026.09-beta.1] - 2026-09-24\n", encoding="utf-8")
        notes = self.root / "docs" / "releases" / "2026.09-beta.1"
        notes.mkdir(parents=True)
        (notes / "RELEASE_NOTES.md").write_text("notes\n", encoding="utf-8")

    def tearDown(self):
        self._tmp.cleanup()

    def props(self, text):
        (self.root / "version.properties").write_text(text, encoding="utf-8")

    def test_good_version_passes(self):
        self.assertEqual(cv.check(self.root), [])

    def test_tag_must_match(self):
        self.assertEqual(cv.check(self.root, "v2026.09-beta.1"), [])
        self.assertTrue(cv.check(self.root, "v2026.09-beta.2"))

    def test_bad_name_format(self):
        self.props(GOOD.replace("2026.09-beta.1", "2026.9-beta1"))
        self.assertTrue(any("không đúng dạng" in e for e in cv.check(self.root)))

    def test_version_code_must_follow_formula(self):
        self.props(GOOD.replace("2026090101", "2026090201"))
        self.assertTrue(any("VERSION_CODE" in e for e in cv.check(self.root)))

    def test_label_and_channel_must_match_name(self):
        self.props(GOOD.replace("BUILD_LABEL=2026.09 beta-1", "BUILD_LABEL=2026.09 beta-2"))
        self.assertTrue(any("BUILD_LABEL" in e for e in cv.check(self.root)))
        self.props(GOOD.replace("RELEASE_CHANNEL=beta", "RELEASE_CHANNEL=rc"))
        self.assertTrue(any("RELEASE_CHANNEL" in e for e in cv.check(self.root)))

    def test_missing_changelog_entry_and_release_notes(self):
        (self.root / "CHANGELOG.md").write_text("# nothing\n", encoding="utf-8")
        (self.root / "docs" / "releases" / "2026.09-beta.1" / "RELEASE_NOTES.md").unlink()
        errors = cv.check(self.root)
        self.assertTrue(any("CHANGELOG" in e for e in errors))
        self.assertTrue(any("RELEASE_NOTES" in e for e in errors))


class RepositoryVersionTests(unittest.TestCase):
    def test_repo_version_is_consistent(self):
        self.assertEqual(cv.check(REPO_ROOT), [])


if __name__ == "__main__":
    unittest.main()
