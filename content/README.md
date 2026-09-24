# content/ — dữ liệu data-driven

Nguồn: Technical Architecture §VI/§XXV, Content Production Pack §II–III. **Schema v1 là bản nháp.**

```text
content/
├── manifest.json      # packId, packVersion, schemaVersion, dependencies
├── enemies/           # ENEMY_* (species) và BOSS_* — 12 loài toàn game
└── missions/          # MISSION_*
```

## Quy tắc
- **ID**: `UPPER_SNAKE_CASE`, duy nhất, human-readable, **không chứa version**, tên file = `<id>.json`. Tiền tố: `ENEMY_`, `BOSS_`, `MISSION_`.
- **Version** nằm riêng: `schemaVersion` (hiện 1) và `contentVersion` (số nguyên ≥ 1).
- **Status**: `Draft → Internal Review → Prototype → Playtest → Balance → Art Polish → QA → Approved → Released`.
  Từ `Prototype` trở lên bắt buộc có `stats` (enemy) / `rewards` (mission). Kênh `production` chỉ nhận `Approved`/`Released`.
- **Không tự bịa số liệu.** Chưa có nguồn canon → `null` + `Draft`, và ghi OPEN QUESTION.
- **Mission**: `roster` (Fixed Enemy Roster) không rỗng, mọi phần tử phải tồn tại trong `enemies/`;
  `targetDurationMinutes` (nếu có) nằm trong 5–15 phút; `unlockAfter` không được tạo chu trình.

## Kiểm tra
```bash
python3 tools/validate_content.py                 # kênh beta
python3 tools/validate_content.py --channel production --strict
```
