# Beta 01 — Trạng thái triển khai

Cập nhật: 2026-09-24 · Phiên bản: `2026.09-beta.1` · Nguồn hạng mục: `design/03_BETA_SCOPE/LAST_OUTPOST_BETA_01_DESIGN_SCOPE_v1.0.md` §2.
Ký hiệu: ✅ có và có test · 🟡 khung/một phần · ⬜ chưa bắt đầu.

## Nền móng (hạ tầng)
| Hạng mục | Trạng thái | Ghi chú |
|---|---|---|
| Repo + tài liệu (`docs/`) + lịch sử | ✅ | `docs/design` khoá bằng checksum |
| Versioning (`version.properties`) | ✅ | `tools/check_version.py` |
| CI (`ci.yml`) + release (`release-beta.yml`) | 🟡 | actionlint sạch; **chưa chạy trên GitHub** — xem TD-01 |
| Content pipeline + validator | ✅ | 34 test Python |
| `:core` mô phỏng (tick, phase, pipeline, command, roster guard, ledger) | ✅ | 19 test Kotlin |
| App shell (Compose) | 🟡 | chưa build bằng Gradle — TD-01 |

## P0 — phải có trong Beta 01
| Nhóm | Hạng mục | Trạng thái | Ghi chú |
|---|---|---|---|
| Combat | Mission state flow | 🟡 | `BattlePhase` + `ResultState` |
| Combat | Build / Tower modular Base+Barrel+Module | ⬜ | mới có `Command.BuildTower`; chờ OQ-DESIGN-02 |
| Combat | Fixed enemy roster | 🟡 | luật được cưỡng chế (`FixedRoster`, validator); 12 loài ở Draft, chưa có chỉ số (OQ-CONTENT-02) |
| Combat | Wave Director | 🟡 | roster guard + spawn intent; chưa Threat Budget/escalation |
| Combat | Logistics, Ammo, Power, Water, Heat | ⬜ | |
| Combat | Hazards theo mission | ⬜ | |
| Combat | Commander core | ⬜ | chờ OQ-DESIGN-01 |
| Combat | Boss framework + ≥ 1 Boss | ⬜ | 2 boss ở Draft |
| Combat | Victory/Defeat/Result | 🟡 | `endCombat(result)`; chưa Battle Report |
| Outpost | Stage 1→3, Command Center, Workshop/Test Range, Research/Tech Tree, Expedition Board, Intel Archive, Specialist, Blueprint+Fragment, Mastery | ⬜ | |
| World | World Map node, Recon/Intel, Expedition route/risk, incident pool | ⬜ | chờ OQ-DESIGN-06 |
| Social | Guild (HQ, Shared Tech/Storage, Projects, Operations, Async Support) | ⬜ | cần backend (OQ-TECH-03) |
| Social | PvP Raid/Challenge Base | ⬜ | chờ OQ-DESIGN-04 + backend |
| Platform | Save / cloud / recovery | 🟡 | `Ledger` in-memory idempotent; chưa lưu bền/cloud |
| Platform | Entitlement Plus/Pro | ⬜ | |
| Platform | Analytics core | 🟡 | `BattleWorld.eventLog` là điểm móc; chưa pipeline upload |

## P1 / P2
P1 (Co-op Defense prototype, Guild Defense roles…) bị gate sau P0 ổn định. P2 không thuộc Beta.

## Thứ tự đề xuất tiếp theo (chưa được duyệt)
Theo Technical Architecture §XXXIII và Production Roadmap Mốc 1–2: **Entity Registry → Tower system → Enemy system → Projectile/Damage →
Mission Objective → Basic Renderer → Input command layer**, rồi Status/Targeting/Pathfinding/Logistics.
Trước Tower system cần OQ-DESIGN-02; trước Basic Renderer cần OQ-TECH-01.
