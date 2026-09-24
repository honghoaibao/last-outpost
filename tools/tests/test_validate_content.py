import json
import sys
import tempfile
import unittest
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

import validate_content as vc  # noqa: E402

REPO_ROOT = Path(__file__).resolve().parents[2]


def enemy(id_="ENEMY_CRAWLER", **over):
    doc = {
        "id": id_, "schemaVersion": 1, "contentVersion": 1, "status": "Draft",
        "name": "X", "kind": "species", "group": "A", "introStage": "Tutorial", "stats": None,
    }
    doc.update(over)
    return doc


def mission(id_="MISSION_TEST_001", **over):
    doc = {
        "id": id_, "schemaVersion": 1, "contentVersion": 1, "status": "Draft",
        "missionType": "Standard", "roster": ["ENEMY_CRAWLER"], "targetDurationMinutes": None,
        "rewards": None, "unlockAfter": [],
    }
    doc.update(over)
    return doc


class ContentTestCase(unittest.TestCase):
    def setUp(self):
        self._tmp = tempfile.TemporaryDirectory()
        self.root = Path(self._tmp.name)
        (self.root / "enemies").mkdir()
        (self.root / "missions").mkdir()
        self.write("manifest.json", {"packId": "base", "packVersion": "0.1.0", "schemaVersion": 1, "dependencies": []})
        self.write("enemies/ENEMY_CRAWLER.json", enemy())

    def tearDown(self):
        self._tmp.cleanup()

    def write(self, rel, doc):
        path = self.root / rel
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(json.dumps(doc), encoding="utf-8")

    def validate(self, channel="beta"):
        return vc.validate_content(self.root, channel)

    def assertHasError(self, report, fragment):
        self.assertTrue(any(fragment in e for e in report.errors), f"không có lỗi chứa {fragment!r}: {report.errors}")


class ValidateContentTests(ContentTestCase):
    def test_valid_minimal_content_passes_with_draft_warnings(self):
        self.write("missions/MISSION_TEST_001.json", mission())
        report = self.validate()
        self.assertTrue(report.ok, report.errors)
        self.assertTrue(report.warnings)  # Draft thiếu stats/rewards → cảnh báo, không lỗi

    def test_schema_mismatch(self):
        self.write("enemies/ENEMY_CRAWLER.json", enemy(schemaVersion=2))
        self.assertHasError(self.validate(), "schema mismatch")

    def test_manifest_schema_mismatch(self):
        self.write("manifest.json", {"packId": "base", "packVersion": "0.1.0", "schemaVersion": 9, "dependencies": []})
        self.assertHasError(self.validate(), "schema mismatch")

    def test_duplicate_ids(self):
        self.write("enemies/ENEMY_CRAWLER.json", enemy())
        self.write("missions/MISSION_TEST_001.json", mission(id_="MISSION_TEST_001"))
        self.write("enemies/ENEMY_OTHER.json", enemy(id_="ENEMY_CRAWLER"))  # id trùng, tên file lệch
        report = self.validate()
        self.assertHasError(report, "duplicate id")

    def test_roster_must_reference_existing_enemy(self):
        self.write("missions/MISSION_TEST_001.json", mission(roster=["ENEMY_GHOST"]))
        self.assertHasError(self.validate(), "missing reference")

    def test_empty_and_duplicate_roster(self):
        self.write("missions/MISSION_TEST_001.json", mission(roster=[]))
        self.assertHasError(self.validate(), "invalid roster")
        self.write("missions/MISSION_TEST_001.json", mission(roster=["ENEMY_CRAWLER", "ENEMY_CRAWLER"]))
        self.assertHasError(self.validate(), "bị lặp")

    def test_missing_reward_is_error_from_prototype(self):
        self.write("missions/MISSION_TEST_001.json", mission(status="Prototype"))
        self.assertHasError(self.validate(), "missing reward")
        self.write("missions/MISSION_TEST_001.json", mission(status="Prototype", rewards={"SCRAP": 1}))
        self.assertTrue(self.validate().ok)

    def test_missing_stats_is_error_from_prototype(self):
        self.write("enemies/ENEMY_CRAWLER.json", enemy(status="Playtest"))
        self.assertHasError(self.validate(), "thiếu stats")

    def test_unlock_reference_and_cycle(self):
        self.write("missions/MISSION_TEST_001.json", mission("MISSION_TEST_001", unlockAfter=["MISSION_TEST_002"]))
        self.write("missions/MISSION_TEST_002.json", mission("MISSION_TEST_002", unlockAfter=["MISSION_TEST_001"]))
        self.assertHasError(self.validate(), "dependency cycle")
        self.write("missions/MISSION_TEST_002.json", mission("MISSION_TEST_002", unlockAfter=["MISSION_NOPE"]))
        self.assertHasError(self.validate(), "missing reference")

    def test_id_rules(self):
        self.write("enemies/ENEMY_CRAWLER_V2.json", enemy(id_="ENEMY_CRAWLER_V2"))
        self.assertHasError(self.validate(), "không được chứa version")
        self.write("enemies/enemy_bad.json", enemy(id_="enemy_bad"))
        self.assertHasError(self.validate(), "UPPER_SNAKE_CASE")
        self.write("enemies/BOSS_WRONG.json", enemy(id_="BOSS_WRONG"))  # kind=species nhưng tiền tố BOSS_
        self.assertHasError(self.validate(), "phải bắt đầu bằng 'ENEMY_'")

    def test_filename_must_match_id(self):
        self.write("enemies/ENEMY_MISMATCH.json", enemy(id_="ENEMY_OTHER_NAME"))
        self.assertHasError(self.validate(), "khớp id")

    def test_duration_rule_5_to_15_minutes(self):
        self.write("missions/MISSION_TEST_001.json", mission(targetDurationMinutes={"min": 3, "max": 8}))
        self.assertHasError(self.validate(), "nằm ngoài khoảng")
        self.write("missions/MISSION_TEST_001.json", mission(targetDurationMinutes={"min": 9, "max": 20}))
        self.assertHasError(self.validate(), "nằm ngoài khoảng")
        self.write("missions/MISSION_TEST_001.json", mission(targetDurationMinutes={"min": 5, "max": 8}))
        self.assertTrue(self.validate().ok)

    def test_production_channel_rejects_draft(self):
        self.write("missions/MISSION_TEST_001.json", mission())
        self.assertTrue(self.validate("beta").ok)
        self.assertHasError(self.validate("production"), "status < Approved")

    def test_missing_manifest(self):
        (self.root / "manifest.json").unlink()
        self.assertHasError(self.validate(), "manifest.json")


class LegacyGuardTests(ContentTestCase):
    def test_detects_legacy_terms(self):
        (self.root / "bad.json").write_text('{"mode": "territory_war"}\n', encoding="utf-8")
        report = vc.scan_legacy([self.root])
        self.assertHasError(report, "legacy guard")

    def test_does_not_flag_unrelated_words(self):
        (self.root / "ok.json").write_text('{"note": "besieged? no. hexagonal ok"}\n', encoding="utf-8")
        self.assertTrue(vc.scan_legacy([self.root]).ok)


class RepositoryContentTests(unittest.TestCase):
    """Dữ liệu content thật của repo phải luôn hợp lệ ở kênh beta."""

    def test_repo_content_is_valid_for_beta(self):
        report = vc.validate_content(REPO_ROOT / "content", "beta")
        self.assertTrue(report.ok, report.errors)

    def test_repo_has_all_twelve_species(self):
        ids = {p.stem for p in (REPO_ROOT / "content" / "enemies").glob("*.json")}
        self.assertEqual(len(ids), 12)
        self.assertEqual(sum(1 for i in ids if i.startswith("BOSS_")), 2)

    def test_repo_sources_have_no_legacy_terms(self):
        report = vc.scan_legacy([REPO_ROOT / "content", REPO_ROOT / "core" / "src", REPO_ROOT / "app" / "src"])
        self.assertTrue(report.ok, report.errors)


if __name__ == "__main__":
    unittest.main()
