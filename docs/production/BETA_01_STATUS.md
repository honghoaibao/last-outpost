# Beta 01 — Trạng thái triển khai

Cập nhật: 2026-10-09 · Phiên bản: `2026.09-beta.1` · Nguồn hạng mục: `design/03_BETA_SCOPE/LAST_OUTPOST_BETA_01_DESIGN_SCOPE_v1.0.md` §2.
Ký hiệu: ✅ có và có test · 🟡 khung/một phần · ⬜ chưa bắt đầu.

## Nền móng (hạ tầng)
| Hạng mục | Trạng thái | Ghi chú |
|---|---|---|
| Repo + tài liệu (`docs/`) + lịch sử | ✅ | `docs/design` khoá bằng checksum |
| Versioning (`version.properties`) | ✅ | `tools/check_version.py` |
| CI (`ci.yml`) + release (`release-beta.yml`) | ✅ | Đã chạy thành công trên GitHub Actions (2026-09-25) |
| Content pipeline + validator | ✅ | 34 test Python |
| `:core` mô phỏng (tick, phase, pipeline, command, roster guard, ledger, entity registry, tower system, enemy system, battlefield, target acquisition, custom priority, combat resolution, movement, ammo/heat, mission objective) | ✅ | 133 test Kotlin |
| App shell (Compose) | ✅ | Build qua Gradle thành công (`assembleDebug`) |

## P0 — phải có trong Beta 01
| Nhóm | Hạng mục | Trạng thái | Ghi chú |
|---|---|---|---|
| Combat | Mission state flow | 🟡 | `BattlePhase` + `ResultState` |
| Combat | Entity Registry | ✅ | `EntityRegistry` — ID dùng chung, bảng Tower + bảng Enemy |
| Combat | Battlefield (World Position + Buildable Node + Path Graph/Lane) | 🟡 | ADR-0005: `Battlefield`/`BuildableNode`/`PathGraph` triển khai đúng schema System Gameplay §III, có `shortestPath`/`pathLength` (Dijkstra, trọng số Euclid) dùng làm navigation graph cho Movement; CHƯA có content pipeline cho map/mission thật (dựng tay bằng Kotlin — OQ-TECH-08, TD-17) |
| Combat | Build / Tower modular Base+Barrel+Module | 🟡 | `TowerSystem` đặt Tower vào Buildable Node thật (ADR-0005), kiểm tra mount compatibility + node đã bị chiếm hay chưa; barrel/module mới kiểm tra tồn tại, CHƯA kiểm tra tương thích với base; roster Tower cụ thể vẫn chờ OQ-DESIGN-02 |
| Combat | Fixed enemy roster | 🟡 | luật được cưỡng chế (`FixedRoster`, validator); 12 loài ở Draft, chưa có chỉ số (OQ-CONTENT-02) |
| Combat | Wave Director | 🟡 | roster guard + spawn intent; Threat Cost baseline **canon** (OQ-DESIGN-09 Answered — AI Sim §VII), nhưng chưa dùng để giới hạn ngân sách wave; chưa escalation/composition/recovery window |
| Combat | Enemy system (spawn intake + entity model) | 🟡 | `EnemySystem` tiêu thụ `spawnQueue` → tạo `EnemyEntity` thật, gắn `currentNodeId` = entry node của lane; spawn tại đúng entry node nếu mission có Battlefield (ADR-0005), fallback `(0,0)` nếu không. CHƯA: AI State transition thật (chỉ dừng ở SPAWN), Status Effects, Resistances (OQ-CONTENT-02) |
| Combat | Movement/Pathfinding | 🟡 | `MovementSystem` (TickStage.MOVEMENT) — GROUND đi theo Path Graph thật (`shortestPath` mỗi tick, snap vào node, dừng + phát `enemy_reached_core` khi tới Core); FLYING bay thẳng tới Core (chưa attack corridor/obstacle avoidance — TD-31); BURROW CHƯA di chuyển (chưa có underground graph — TD-26). `speed` phải truyền từ ngoài (OQ-CONTENT-02, luôn null trong gameplay thật) |
| Combat | Target Acquisition (Tower↔Enemy) | 🟡 | `TowerTargetingSystem` (Acquire→Filter→Rank→Lock, System Gameplay §6.1–6.2) với **Custom Priority canon** (OQ-DESIGN-10 Answered — Filter→PrimaryOrder→TieBreaker, `TargetingRule`) và `EnemyTargetingSystem` (AI Sim §III, chỉ model Objective qua lọc class + Distance thật). `range` của Tower phải truyền từ ngoài (chưa có trong baseStats — TD-20). STRONGEST/LOWEST_HP đúng logic nhưng chưa có Health thật để phân biệt (OQ-CONTENT-02); SPECIAL_THREAT chưa có tiêu chí nguồn (TD-21); **FIRST giờ dùng độ dài đường đi thật trên Path Graph** (TD-24 đã đóng) khi enemy có `currentNodeId`, fallback đường thẳng cho FLYING. Enemy chỉ nhắm được class TOWER — các target class khác chưa có entity thật (TD-23) |
| Combat | Attack/Damage (Fire→Resolve Hit→Apply Effect) | 🟡 | `TowerAttackSystem` (ATTACK_PROJECTILE, hitscan — không mô phỏng travel time, Tech Architecture §V không có ProjectileEntity riêng) + `DamageResolutionSystem` (DAMAGE_DEATH) — Damage Model đúng công thức System Gameplay §6.3 (`DamageFormula`), enemy chết bị xoá + phát `enemy_died`. `baseDamage`/`cooldownTicks` PHẢI truyền từ ngoài (TD-27, chưa có content Barrel); Health/Armor luôn null trong gameplay thật nên phát bắn hiện chưa có tác dụng thật (OQ-CONTENT-02) |
| Combat | Ammo + Heat (phần tiêu thụ/làm mát) | 🟡 | `TowerAttackSystem` chặn bắn khi hết Ammo hoặc Heat 100% (Jam), trừ `ammoPerShot`/cộng `shotHeat` khi bắn; `HeatPowerWaterSystem` (HEAT_POWER_WATER) áp Cooling mỗi tick — đúng công thức `New Heat = Old Heat + Shot Heat − Cooling` (System Gameplay §XII); `HeatModel` phân band Normal/Warm/Critical/Jam đúng bảng §12.2. Ammo/Heat chỉ có hiệu lực khi `TowerEntity.ammo/heat` được set (mặc định `null` = không giới hạn, chưa có content canon — OQ-DESIGN-02); mọi số (`ammoPerShot`/`shotHeat`/cooling) phải truyền từ ngoài |
| Combat | Logistics network (resupply Ammo), Power, Water | ⬜ | Logistics System §XI là mạng Storage→Hub→Route→Cluster riêng (route capacity/danger/uptime, disruption) — chưa làm (TD-34); Power §10.3 và Water §10.4 không có công thức update cụ thể trong tài liệu nên chưa model (TD-35). Ammo hiện chỉ giảm, không tự đầy |
| Combat | Hazards theo mission | ⬜ | |
| Combat | Commander core | ⬜ | chờ OQ-DESIGN-01 |
| Combat | Boss framework + ≥ 1 Boss | ⬜ | 2 boss ở Draft |
| Combat | Mission Objective — Base Core, Victory/Defeat | 🟡 | `BaseCore` (HP, `BattleWorld.core`) + `CoreAssaultSystem` (enemy tới Core đánh theo cooldown, HP **mất dần**, không chết tức thì — System Gameplay §17.2) + `MissionObjectiveSystem` (OBJECTIVES): Defeat = Core Destroyed, Victory = đã phát hết kế hoạch spawn + sân sạch enemy + Core còn sống (§17.3–17.4), Defeat ưu tiên hơn. "Survive Final Wave" là diễn giải (OQ-DESIGN-11). Số liệu Core HP/damage truyền từ ngoài (OQ-CONTENT-02). CHƯA: Shield, ngưỡng emergency, defeat phụ (TD-38); enemy đánh Tower/công trình khác (TD-37); Battle Report/commit phần thưởng |
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
Theo Technical Architecture §XXXIII và Production Roadmap Mốc 1–2: Entity Registry → Battlefield (khung xong,
ADR-0005) → Tower system (khung xong) → Enemy system (khung intake xong) → Target Acquisition (khung xong,
Tower↔Enemy) → Attack/Damage (khung xong, Fire→Resolve Hit→Apply Effect) → Movement/Pathfinding (khung xong,
GROUND theo Path Graph thật) → Ammo/Heat (khung xong) → Mission Objective (khung xong: vòng thắng/thua đã khép) →
**Enemy đánh Tower (TD-37, để enemy thật sự đe doạ phòng tuyến) → Basic Renderer → Input command layer → Battle
Report/commit kết quả**, rồi Logistics network/Power/Water (cần thiết kế rõ hơn), Status Effects/Hazard.
Trước khi có Barrel/Module thật cần OQ-DESIGN-02; trước Basic Renderer cần OQ-TECH-01; trước content map thật
cần OQ-TECH-08; trước Damage model thật (System Gameplay §6.3) cần chỉ số Tower/Enemy canon (OQ-DESIGN-02/OQ-CONTENT-02).
