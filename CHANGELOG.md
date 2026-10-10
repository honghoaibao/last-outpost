# Changelog

Định dạng theo [Keep a Changelog](https://keepachangelog.com/vi/1.1.0/). Phiên bản theo [`docs/decisions/0002-version-scheme.md`](docs/decisions/0002-version-scheme.md).
Changelog của **thiết kế** nằm riêng ở [`docs/history/changelog/`](docs/history/changelog/).

## [2026.09-beta.1] - 2026-09-24

Bản nền móng (foundation) cho Beta 01 — chưa phải bản chơi được.

### Thêm
- Cấu trúc repo: `app/` (Android, Kotlin + Compose), `core/` (mô phỏng thuần Kotlin/JVM), `content/`, `tools/`, `docs/`.
- `:core` — fixed-tick clock (20–30 tick/s), vòng đời `BattleWorld` (8 phase), pipeline 13 stage đúng Tech Architecture §IV,
  hàng đợi Command, `WaveDirector` cưỡng chế **Fixed Enemy Roster**, `Ledger` giao dịch idempotent/nguyên tử,
  `EntityRegistry` (ID entity dùng chung), `battlefield` package — **World Position + Buildable Node + Path
  Graph/Lane** (ADR-0005: `WorldPosition`, `BuildableNode`, `PathGraph`, `Battlefield`) — `TowerSystem` (đặt
  Tower vào Buildable Node, kiểm tra mount compatibility + occupancy), `EnemySystem` (tiêu thụ `spawnQueue` →
  tạo `EnemyEntity` tại đúng entry node của lane), `targeting` package — **Target Acquisition** hai chiều:
  `TowerTargetingSystem` (Acquire→Filter→Rank→Lock, System Gameplay §6.1–6.2) với **Custom Priority canon**
  (`TargetingRule`: Filter→PrimaryOrder→TieBreaker — quyết định Bảo 2026-09-27) và `EnemyTargetingSystem`
  (AI Sim §III, lọc theo target class ưu tiên của archetype + khoảng cách Euclid thật). `EnemyRole` (Core
  Combat Gameplay §VI). 69 unit test.
- `tools/validate_content.py` — allowlist hẹp cho legacy guard ("Boss / Siege" là Role hợp lệ, khác "24h siege" đã cấm).
- `combat` package — **Attack/Damage** (Combat Resolution §6.1/§6.3): `TowerAttackSystem` (ATTACK_PROJECTILE —
  Fire→Resolve Hit, hitscan) và `DamageResolutionSystem` (DAMAGE_DEATH — Apply Effect, xoá enemy khi HP về 0,
  phát `enemy_died`). `DamageFormula` đúng công thức `Final Damage = Base × Barrel × Module × Target
  Interaction × Temporary` rồi `max(1, Raw − Armor)`. `DamageTag` (7 tag, Combat Resolution §6.4);
  `EnemyEntity.resistances` đổi từ `Map<String,Double>` sang `Map<DamageTag,Double>`; thêm `EnemyEntity.armor`.
  16 unit test mới (85 tổng).
- `PathGraph.shortestPath`/`pathLength` (Dijkstra, trọng số Euclid) — navigation graph thật cho enemy GROUND
  (AI & Simulation Design §IV.1). `enemy/MovementSystem.kt` (TickStage.MOVEMENT): GROUND đi theo Path Graph,
  FLYING bay thẳng tới Core (§IV.2), BURROW chưa di chuyển (§IV.3, chưa có underground graph). Enemy dừng +
  phát `enemy_reached_core` khi tới Core. `EnemySystem` giờ gắn `Movement.currentNodeId` khi spawn qua lane.
  `TowerTargetingSystem.FIRST` nâng cấp dùng `Battlefield.pathDistanceToCore` (path-cost thật) thay vì đường
  thẳng khi có thể — đóng TD-24. 20 unit test mới (105 tổng).
- Sửa: entry node của lane (`Battlefield.spawnPositionForLane`/`entryNodeIdForLane`) giờ tra đúng trên
  `PathGraph` (`LaneNode`) thay vì danh sách Buildable Node — cần thiết để Movement path được từ điểm spawn.
- **Ammo + Heat** (System Gameplay §10.2/§XII): `TowerAttackSystem` chặn bắn khi hết Ammo hoặc Heat 100% (Jam),
  trừ `ammoPerShot`/cộng `shotHeat` khi bắn; `combat/HeatPowerWaterSystem` (HEAT_POWER_WATER) áp Cooling mỗi tick
  (`New Heat = Old Heat + Shot Heat − Cooling`); `HeatModel` phân band Normal/Warm/Critical/Jam đúng bảng §12.2.
  Mặc định `TowerEntity.ammo/heat = null` (không giới hạn) nên không ảnh hưởng hành vi cũ. Power/Water và mạng
  Logistics (resupply) chưa làm — xem TD-34/TD-35. 14 unit test mới (119 tổng).
- **Mission Objective** (System Gameplay §XVII): `entity/BaseCore` (HP, `BattleWorld.core`), `mission/CoreAssaultSystem`
  (enemy tới Core đánh theo cooldown, HP mất dần — không instant death) và `mission/MissionObjectiveSystem`
  (OBJECTIVES: Defeat = Core Destroyed, Victory = hết Final Wave + sân sạch + Core còn sống; Defeat ưu tiên). Thêm
  `WaveDirector.isPlanExhausted`, `EnemyAttack` (cooldown) trên `EnemyEntity`. 14 unit test mới (133 tổng),
  gồm test tích hợp spawn → di chuyển → đánh Core → Defeat. "Survive Final Wave" là diễn giải — OQ-DESIGN-11.

### Đã chốt (canon)
- **Threat Cost** (quyết định Bảo, 2026-09-27): AI & Simulation Design §VII là source of truth. System &
  Gameplay Design v1.1 §8.3 là legacy/sample values, không dùng để implementation.
- **Custom Priority của Tower** (quyết định Bảo, 2026-09-27): FILTER (All/Special Threat/Boss-Elite/Air/
  Ground/Swarm) → PRIMARY ORDER (First/Nearest/Strongest/Lowest HP) → TIE BREAKER tuỳ chọn (First/Nearest).
  Không hỗ trợ nested condition, AND/OR chain, threshold, custom scripting.
- `content/` — 12 loài quái (10 species + 2 boss) ở trạng thái Draft (chưa có chỉ số — OPEN), 1 mission placeholder, manifest.
- `tools/` — `validate_content.py` (schema, ID, roster, reference, cycle, reward, kênh production, legacy guard),
  `verify_docs.py` (bảo vệ `docs/design`), `check_version.py`. 34 test Python.
- GitHub Actions: `ci.yml`, `release-beta.yml` (ký release khi có secrets), Dependabot, issue/PR template.
- Hệ thống tài liệu: `docs/design` (nguyên bản), `docs/history`, `docs/decisions` (ADR), `docs/open-questions`,
  `docs/production`, `docs/releases`, `docs/playtest`, `docs/balance`.

### Ghi chú
- Toàn bộ nội dung `docs/design/` giữ nguyên bản từ bundle `LAST_OUTPOST_BETA_01_CLEAN_DESIGN_PRODUCTION_BUNDLE_v1_0.zip`.
- Các giả định kỹ thuật chưa được thiết kế chốt (engine/renderer, applicationId, minSdk, tick rate, backend) được ghi ở
  [`docs/open-questions/OPEN_QUESTIONS.md`](docs/open-questions/OPEN_QUESTIONS.md).
