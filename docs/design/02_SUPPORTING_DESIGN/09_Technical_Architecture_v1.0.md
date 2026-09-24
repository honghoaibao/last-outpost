# LAST OUTPOST — TECHNICAL ARCHITECTURE v1.0

## 0. Mục tiêu

Technical Architecture chuyển các thiết kế gameplay thành kiến trúc có thể triển khai và mở rộng.

Mục tiêu:
- 2.5D Isometric.
- Swarm lớn, tối ưu cho 1000+ enemy instances trong cảnh cao tải.
- Combat 5–15 phút/màn.
- Tower modular: Base + Barrel + Module.
- Enemy AI + Wave Director + Boss.
- Persistent Outpost.
- Offline-safe PvE trong phạm vi cho phép.
- Cloud Save và transaction-safe economy.
- Plus / Pro entitlement.
- Content data-driven để tạo hàng trăm mission.
- Versioning, telemetry, debug và profiling từ đầu.

Nguyên tắc:
> Runtime nhanh và deterministic-friendly; content data-driven; account state transaction-safe; presentation tách khỏi gameplay simulation.

---

## I. KIẾN TRÚC CẤP CAO

```text
GAME CLIENT
├── Presentation
│   ├── UI / HUD
│   ├── Camera / Input
│   ├── Animation
│   └── VFX / SFX
├── Application
│   ├── Boot / Session
│   ├── Profile / Outpost
│   ├── Campaign
│   ├── Mission Flow
│   ├── Result / Progression
│   └── Shop / Season / Expedition
├── Gameplay Runtime
│   ├── BattleWorld
│   ├── Combat
│   ├── Tower
│   ├── Enemy / AI
│   ├── Wave Director
│   ├── Objectives
│   ├── Logistics
│   └── Hazards
└── Core Services
    ├── Fixed Tick
    ├── Event Bus
    ├── Object Pool
    ├── Spatial Query
    ├── Save / Cache
    └── Content Registry

BACKEND
├── Account
├── Cloud Save
├── Economy / Inventory
├── Entitlement
├── Season
├── Guild / PvP
└── Telemetry
```

Không ép thành microservices ngay từ đầu. Có thể bắt đầu bằng modular backend rồi tách khi scale yêu cầu.

---

## II. MODULE BOUNDARIES

### Presentation
Chịu trách nhiệm:
- màn hình;
- HUD;
- input presentation;
- animation;
- VFX/SFX;
- accessibility.

Không tự tính:
- damage;
- reward;
- currency;
- progression;
- enemy AI.

### Application
Điều phối:
`Boot → Outpost → Campaign → Briefing → Battle → Result → Progression`.

Không chạy logic AI từng frame.

### Gameplay Runtime
Gồm:
- BattleWorld
- Entity Registry
- Combat
- Tower
- Enemy
- Projectile
- Status
- Heat
- Ammo
- Power
- Water
- Logistics
- Hazard
- Commander
- Wave Director
- Mission Objective

---

## III. BATTLE RUNTIME

### BattleWorld

```text
BattleWorld
├── missionId
├── contentVersion
├── seed
├── elapsedTime
├── currentWave
├── phase
├── entities
├── resources
├── hazards
├── objectives
└── resultState
```

Lifecycle:

```text
Boot
→ Load Content
→ Build World
→ Prepare
→ Combat
→ Calculate Result
→ Commit Result
→ Unload
```

Persistent account không bị mutate trực tiếp trong Combat.

---

## IV. FIXED-TICK SIMULATION

Rendering và simulation tách nhau.

Đề xuất:
- Simulation: fixed tick khoảng 20–30 ticks/s.
- Rendering: 60 FPS target, có fallback theo thiết bị.

Thứ tự một tick:

```text
1. Input Commands
2. Wave / Mission Director
3. Spawn / Despawn
4. Movement
5. Target Acquisition
6. Attack / Projectile
7. Status Effects
8. Damage / Death
9. Corpse / Terrain Effects
10. Logistics
11. Heat / Power / Water
12. Objectives
13. Event sampling
```

Mục tiêu: thứ tự ổn định, dễ debug và replay.

---

## V. ENTITY / DATA MODEL

Không tạo object hierarchy nặng cho mọi enemy nếu không cần.

Entity dùng:
- Entity ID
- Component data
- System processing

### Tower

```text
TowerEntity
├── Transform
├── BaseState
├── BarrelState
├── ModuleState
├── Health
├── Ammo
├── Heat
├── Power
├── Targeting
└── Attack
```

### Enemy

```text
EnemyEntity
├── Transform
├── Health
├── Movement
├── Resistances
├── Status
├── Targeting
├── Behavior
└── ThreatProfile
```

Runtime state không serialize trực tiếp vào persistent account.

---

## VI. DATA-DRIVEN CONTENT

Content không hard-code trong gameplay.

### EnemyDefinition

```text
id
version
archetype
stats
movement
resistances
behaviorProfile
threatCost
telegraphs
rewards
tags
```

### TowerDefinition

```text
id
version
archetype
allowedMounts
baseStats
targeting
barrelRules
moduleRules
effects
```

### MissionDefinition

```text
id
version
biome
map
roster
wavePlan
objectives
hazards
rewards
modifiers
difficulty
```

Nhờ đó có thể thêm mission bằng dữ liệu thay vì tạo class gameplay riêng.

### Content Versioning

Mọi content asset có:
- contentId
- contentVersion
- schemaVersion

Mission đã hoàn thành không mất lịch sử khi definition được balance lại.

---

## VII. 2.5D ISOMETRIC RENDERING

### Layer

```text
Z=0
Ground / traps / terrain / weather

Z=1
Walls / towers / heroes / ground enemies

Z=2
Cables / elevated towers / flying enemies / upper FX
```

Sorting theo:
- world position;
- height layer;
- render priority;
- effect layer.

### Camera

Hỗ trợ:
- pan;
- zoom;
- focus target;
- camera shake có giới hạn;
- combat emphasis.

Readability luôn ưu tiên hơn cinematic effect.

---

## VIII. SWARM PERFORMANCE

Mục tiêu:
> 1000+ enemy instances trong cảnh cao tải mà không yêu cầu 1000 AI full mỗi tick.

### AI LOD

**Tier A — Near / Important**
- full target query;
- full path;
- attack;
- status.

**Tier B — Mid**
- path update thưa hơn;
- reduced queries;
- grouped steering.

**Tier C — Far / Swarm**
- group movement;
- simplified state;
- aggregate logic.

Khi entity bước vào vùng gameplay quan trọng:
> promote lên tier cao hơn.

---

## IX. OBJECT POOLING

Pool các object tạo/hủy liên tục:
- enemy;
- projectile;
- impact;
- damage number;
- VFX;
- debris;
- pickup;
- temporary hazard.

Flow:

```text
Warmup
→ Spawn from Pool
→ Active
→ Reset
→ Return
```

Reset phải xóa target, status, timers, animation, audio/VFX handles và temporary state.

---

## X. SPATIAL QUERY

Không cho mỗi tower quét toàn bộ enemy.

Dùng spatial partition:
- grid;
- spatial hash;
- hoặc cấu trúc tương đương.

```text
GetCandidates(radius)
→ Filter Tag
→ Filter Range
→ Filter Line of Sight
→ Targeting Rule
```

Mục tiêu tránh brute-force `Tower × Enemy`.

---

## XI. PATHFINDING

Hai lớp:

### Global Path
Tuyến đường chính của map.

### Local Avoidance
Tránh:
- đồng đội;
- corpse;
- hazard;
- temporary blockers.

Khi tường hoặc topology thay đổi:
> chỉ rebuild vùng path bị ảnh hưởng, không tính lại toàn bản đồ mỗi frame.

---

## XII. WAVE DIRECTOR

Wave Director độc lập với Enemy AI.

Input:
- Mission Definition
- Threat Budget
- Elapsed Time
- Player State
- Battlefield State

Output:
- spawn group;
- spawn timing;
- lane;
- composition;
- escalation;
- special event.

**Luật bắt buộc:**
> Không được spawn species ngoài fixed roster của mission.

---

## XIII. COMBAT PIPELINE

```text
Acquire Target
→ Validate
→ Fire
→ Projectile / Instant Hit
→ Hit Resolve
→ Damage
→ Status
→ Death
→ Secondary Effects
```

### DamagePacket

```text
source
target
amount
damageType
tags
crit
armorInteraction
elementalInteraction
```

Không hard-code từng enemy vào projectile system.

---

## XIV. STATUS SYSTEM

Status được data-driven:

```text
StatusDefinition
├── id
├── duration
├── stacking
├── tickInterval
├── modifier
├── cleanseRules
└── visualFeedback
```

Các status chính:
- Slow
- Burn
- Acid
- Freeze
- Shock
- Armor Break
- Blind
- Jam
- EMP / disable

Mỗi status phải định nghĩa rõ:
- refresh;
- stack;
- cap;
- replace.

---

## XV. LOGISTICS RUNTIME

Resource trong trận:
- Ammo
- Power
- Water
- Scrap nếu mission dùng.

Entities:
- Ammo Depot
- Drone
- Supply Rail
- Generator
- Cooling Station

Logistics topology chỉ rebuild khi:
- connection thay đổi;
- building bị phá;
- route bị block;
- power topology đổi.

Không recompute full logistics mỗi frame.

---

## XVI. HEAT / POWER / WATER

### Heat

```text
Heat += WeaponHeat
Heat -= CoolingRate
```

States:
`Normal → Hot → Critical → Jam/Danger`.

### Power

Sources:
- Generator
- Solar
- Battery
- Advanced Reactor

Consumers:
- Tower
- Utility
- Cooling
- Automation

### Water

Dùng cho:
- cooling;
- survival support;
- special mechanics.

Thiếu tài nguyên phải có cảnh báo trước penalty lớn.

---

## XVII. HAZARD SYSTEM

Hazard là hệ thống độc lập:

```text
Hazard
├── type
├── area
├── duration
├── intensity
├── telegraph
├── damage
├── modifiers
└── interactions
```

Ví dụ:
- Acid Rain
- EMP Flare
- Earthquake
- Spore Cloud
- Fire Whirlwind

Hazard cần telegraph trước tác động mạnh.

---

## XVIII. COMMANDER SYSTEM

```text
CommanderEntity
├── Transform
├── Health
├── Movement
├── Skills
├── Cooldowns
├── Buffs
└── InteractionContext
```

Skill:

```text
Input
→ Validate
→ Cost / Cooldown
→ Effect
→ VFX/SFX
→ Feedback
```

Commander combat state khác với Commander progression/account data.

---

## XIX. INPUT COMMAND MODEL

Input không mutate runtime trực tiếp.

```text
Player Input
→ Command
→ Simulation Queue
→ System Apply
```

Command ví dụ:
- BuildTower
- MoveCommander
- UseAbility
- Repair
- PrioritizeTarget
- CancelBuild

Lợi ích:
- input buffering;
- replay/debug;
- deterministic-friendly simulation;
- test automation.

---

## XX. SAVE / SYNC BOUNDARY

Client:

```text
Local Repository
├── Local Snapshot
├── Pending Transactions
├── Cached Definitions
└── Settings
```

Sync:

```text
Sync Manager
├── Pull
├── Push
├── Conflict
├── Retry
└── Recovery
```

Battle runtime không được ghi vào account snapshot liên tục.

Reward đi qua transaction layer.

---

## XXI. BACKEND BOUNDARIES

Tối thiểu:
- Account
- Cloud Save
- Economy / Inventory
- Entitlement
- Season
- Guild / PvP
- Telemetry
- Content metadata

Có thể bắt đầu là modular monolith, sau đó tách service khi cần scale.

---

## XXII. PERSISTENT ECONOMY INTEGRATION

Gameplay không tự cộng currency.

```text
Mission Result
→ Reward Calculator
→ Transaction Builder
→ Economy Service
→ Commit
→ Confirm
```

Áp dụng cho:
- Scrap
- Materials
- Biomass
- Blueprint Fragments
- Rad-Gems
- Mastery XP
- Season rewards
- Rewarded Ads
- Shop purchases

Transaction phải có ID và idempotency.

---

## XXIII. PLUS / PRO ENTITLEMENT

Flow:

```text
Store Purchase
→ Verification
→ Backend Entitlement
→ Client Sync
→ Benefit Provider
→ Gameplay / UI
```

Không rải `isPro == true` ở nhiều hệ thống.

Thay vào đó:
`EntitlementService.has(PLUS)` / `has(PRO)`.

Benefit definitions nên data-driven để dễ balance.

---

## XXIV. NETWORK MODEL

### PvE
- local simulation trong phạm vi cho phép;
- server xác thực các transaction/reward quan trọng.

### PvP
Server-authoritative cho:
- match result;
- score;
- rating;
- reward;
- competitive state.

Client không tự quyết định kết quả cạnh tranh.

---

## XXV. CONTENT DELIVERY

```text
Core Client
+
Versioned Content Packs
```

Pack có:
- manifest;
- version;
- checksum;
- dependencies.

Ví dụ:
```text
base_1.0
region_01_1.0
season_01_1.0
event_frozen_1.0
```

Không tải content thiếu dependency.

---

## XXVI. ANALYTICS

Pipeline:

```text
Gameplay / UI
→ Event Collector
→ Local Buffer
→ Batch
→ Upload
```

Offline thì queue cục bộ và upload sau.

Analytics không được block gameplay.

Event chính:
- mission_start/end/fail
- tower_built/destroyed
- enemy_spawn/killed
- objective_complete
- blueprint_unlock
- outpost_upgrade
- purchase
- plus/pro activation
- sync_error
- recovery
- performance sample

---

## XXVII. PERFORMANCE BUDGET

### Rendering
- 60 FPS target.
- 30 FPS fallback tùy thiết bị.

### Simulation
- fixed tick;
- không để pathfinding/AI tạo frame spike lớn.

### Memory
- object pooling;
- lazy loading;
- không duplicate definitions trong save.

### Loading
- preload content cần cho mission;
- không preload toàn bộ campaign.

### Battery
- stop heavy simulation khi app background;
- menu không có unnecessary update loops.

---

## XXVIII. JOB / THREAD MODEL

Offload khi phù hợp:
- path recalculation;
- content parsing;
- save serialization;
- analytics batching.

Không để nhiều thread cùng mutate một gameplay state mà không có ownership rõ ràng.

Nguyên tắc:

> One owner, predictable write path.

---

## XXIX. ERROR HANDLING

### Recoverable
- network timeout;
- sync retry;
- content retry.

### User-facing
- corrupted local save;
- thiếu storage;
- unsupported content version.

### Critical
- economy mismatch;
- impossible progression;
- reconciliation conflict.

Mỗi lỗi cần:
- internal code;
- log context;
- user-safe message;
- recovery path.

---

## XXX. DEBUG TOOLS

### Battle Debug
- pause;
- step simulation tick;
- set wave;
- spawn enemy;
- kill enemy;
- set resources;
- toggle hazard;
- set heat;
- show path;
- show target;
- show AI LOD.

### Economy Debug
- grant/remove test currency;
- unlock blueprint;
- set mastery;
- simulate reward.

### Save Debug
- inspect snapshot;
- force sync;
- compare local/server;
- clear cache;
- export diagnostics;
- simulate conflict.

---

## XXXI. TEST ARCHITECTURE

### Unit
- damage;
- status;
- targeting;
- cost;
- reward;
- upgrade;
- transaction;
- entitlement.

### Simulation
- fixed seed;
- wave order;
- boss phase;
- hazard;
- objective completion.

### Integration
- battle → reward → save;
- offline → sync;
- purchase → entitlement;
- season rollover.

### Performance
Profile ở:
- 100 enemies;
- 500 enemies;
- 1000 enemies;
- nhiều VFX;
- nhiều hazard;
- thiết bị yếu.

---

## XXXII. BUILD / CI

```text
Commit
→ Static Analysis
→ Unit Tests
→ Integration Tests
→ Build
→ Content Validation
→ Smoke Test
→ Artifact
```

Content validation cần bắt:
- duplicate IDs;
- missing reference;
- invalid roster;
- invalid module compatibility;
- missing reward;
- dependency cycle;
- schema mismatch.

---

## XXXIII. IMPLEMENTATION ORDER

### P0 — CORE RUNTIME
1. Application shell
2. BattleWorld
3. Entity Registry
4. Fixed Tick
5. Tower System
6. Enemy System
7. Projectile / Damage
8. Wave Director
9. Mission Objective
10. Basic Renderer
11. Input Command Layer

### P1 — SYSTEM DEPTH
12. Status
13. Targeting
14. Pathfinding
15. Logistics
16. Heat / Power / Water
17. Hazard
18. Commander
19. Object Pooling
20. Spatial Query
21. AI LOD

### P2 — META / SERVICES
22. Outpost
23. Progression
24. Economy
25. Save
26. Cloud Sync
27. Entitlement
28. Season
29. Analytics

### P3 — LIVE / SCALE
30. Guild
31. PvP
32. Content Delivery
33. Advanced Telemetry
34. Admin / Moderation Tools
35. Live Event Infrastructure

---

## XXXIV. NON-NEGOTIABLES

1. Gameplay simulation không phụ thuộc UI.
2. UI không tự thay đổi economy.
3. Match không trực tiếp mutate persistent account.
4. Content phải data-driven.
5. High-churn entity phải pooling.
6. AI phải scale theo khoảng cách và mức quan trọng.
7. Spatial query không brute-force toàn bộ enemy.
8. Reward đi qua transaction layer.
9. Premium entitlement phải verified.
10. PvP phải server-authoritative.
11. Save có versioning/recovery.
12. Season không reset Outpost.
13. Analytics không block gameplay.
14. Debug phải đủ để tái hiện lỗi.
15. Performance được đo từ prototype.

---

## XXXV. DEFINITION OF DONE

- [ ] Module boundaries rõ.
- [ ] Battle lifecycle rõ.
- [ ] Fixed tick và update order.
- [ ] Entity/runtime model.
- [ ] Content schemas.
- [ ] 2.5D layer/sorting.
- [ ] Swarm strategy.
- [ ] Object pooling.
- [ ] Spatial query.
- [ ] Pathfinding strategy.
- [ ] Wave Director boundary.
- [ ] Economy transaction boundary.
- [ ] Save architecture.
- [ ] Backend boundaries.
- [ ] Entitlement flow.
- [ ] Analytics pipeline.
- [ ] Test architecture.
- [ ] CI/content validation.
- [ ] P0 implementation order.

---

# XXXVI. CORE ARCHITECTURAL PRINCIPLE

> **Last Outpost phải có thể thêm content mà không viết lại engine, thêm gameplay mà không phá save, và tăng số lượng enemy mà không biến simulation thành bottleneck.**

Ví dụ:

```text
Thêm Enemy
→ Definition
→ Behavior Profile nếu cần
→ VFX/SFX
→ Mission Roster
```

hoặc:

```text
Thêm Mission
→ Map
→ Fixed Roster
→ Wave
→ Objective
→ Reward
```

mà không cần tạo một hệ thống code riêng cho từng enemy/màn.

Kiến trúc thành công khi **content team có thể tạo content mới bằng dữ liệu**, còn **gameplay programmer tập trung vào hệ thống và behavior dùng lại được**.
