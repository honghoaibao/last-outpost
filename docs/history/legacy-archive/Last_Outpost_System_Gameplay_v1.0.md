# LAST OUTPOST — SYSTEM & GAMEPLAY DESIGN v1.0

> **Vai trò tài liệu:** tầng thiết kế vận hành nằm dưới GDD `Last_Outpost_Toi_Uu_v1.2`.
>
> **Mục tiêu:** biến các cơ chế đã chốt thành luật gameplay, state machine, data model, combat loop, progression loop và UX flow có thể dùng làm nền cho prototype, balancing và implementation.
>
> **Lưu ý:** Những con số cụ thể trong tài liệu này là **đề xuất thiết kế ban đầu** để playtest, không phải giá trị đã khóa. Các nguyên tắc/phạm vi đã được xác định trong GDD được giữ nguyên.

---

# 0. PHẠM VI & NGUYÊN TẮC

## 0.1. Thứ tự ưu tiên thiết kế

1. **Clarity** — người chơi luôn hiểu chuyện gì đang xảy ra.
2. **Agency** — người chơi có quyết định đáng kể, không chỉ phản ứng.
3. **Counterplay** — mỗi vấn đề có ít nhất một cách xử lý chủ động.
4. **Readable Chaos** — có thể rất nhiều quái nhưng tín hiệu chiến thuật vẫn rõ.
5. **Mobile First** — thao tác bằng ngón tay, ít thao tác thừa, không phụ thuộc chuột/bàn phím.
6. **Persistent Growth** — Trụ Sở và progression dài hạn không reset.

## 0.2. Core fantasy

Người chơi phải cảm thấy mình đang **xây một tiền đồn sống sót giữa một thế giới đang sụp đổ**, chứ không đơn thuần đặt tower rồi chờ quái chết.

## 0.3. Gameplay pillars

**BUILD → PREPARE → SURVIVE → ADAPT**

- **BUILD:** tạo cấu trúc phòng thủ.
- **PREPARE:** chuẩn bị tài nguyên, logistics, loadout và Tướng.
- **SURVIVE:** chống wave, quái đặc biệt, boss và tối đa một thiên tai lớn.
- **ADAPT:** thay đổi ưu tiên, vị trí, module hoặc cách dùng môi trường khi tình hình thay đổi.

---

# I. GAME STATE MODEL

## 1.1. Các trạng thái cấp trận

```text
LOBBY
  ↓
BRIEFING
  ↓
PREPARE
  ↓
WAVE_ACTIVE
  ↙      ↘
PAUSED   HAZARD
  ↓        ↓
WAVE_RESULT
  ↓
MISSION_RESULT
  ↓
REWARD / PROGRESSION
  ↓
OUTPOST
```

### LOBBY

Chọn màn, xem progression, chọn loadout.

### BRIEFING

Hiển thị:
- biome;
- roster quái;
- boss nếu có;
- mục tiêu chính;
- mục tiêu phụ;
- modifier của màn;
- gợi ý intel đã biết.

### PREPARE

Người chơi có thể:
- đặt công trình;
- chọn tower/module;
- bố trí logistics;
- chọn Tướng;
- kiểm tra resource budget;
- xoay lại layout.

### WAVE_ACTIVE

Combat đang chạy. Các thay đổi build phải tuân thủ luật của màn.

### HAZARD

Một thiên tai lớn đang hoạt động. Không phải state tách khỏi combat hoàn toàn; đây là mode ưu tiên cảnh báo và logic hazard.

### WAVE_RESULT

Khoảng nghỉ ngắn để:
- thu thập reward tạm;
- sửa chữa;
- điều chỉnh hạn chế;
- chuẩn bị wave tiếp theo.

### MISSION_RESULT

Tính:
- Victory/Defeat;
- stars/score;
- objectives;
- rewards;
- mastery;
- unlock/progression.

---

# II. PLAYER INPUT & MOBILE CONTROL

## 2.1. Input cơ bản

### Tap
- Chọn tower.
- Chọn quái.
- Chọn công trình.
- Kích hoạt skill.

### Hold
- Hiển thị range.
- Hiển thị preview đặt công trình.
- Hiển thị thông tin nâng cao.

### Drag
- Kéo công trình vào vị trí hợp lệ.
- Kéo đường ray segment.
- Điều chỉnh thứ tự target nếu hệ thống cho phép.

### Pinch
- Zoom in/out.

### Two-finger drag
- Pan camera.

### Long press
- Mở quick context menu.

## 2.2. Nguyên tắc thao tác

- **Không yêu cầu pixel-perfect placement.**
- Mọi vùng tương tác nên lớn hơn sprite hiển thị.
- Nút skill chủ động nằm cùng vùng tay phải.
- Resource bar luôn nhìn thấy trong combat.
- Khi đặt building, hiển thị ngay lỗi vì sao không hợp lệ.

---

# III. BATTLEFIELD DATA MODEL

## 3.1. Battlefield gồm

```text
Battlefield
├── Terrain Layer
├── Height / Z Layer
├── Buildable Nodes
├── Path Graph
├── Hazard Zones
├── Resource Nodes
├── Salvage Objects
├── Enemy Spawn Points
├── Base Core
└── Outpost Structures
```

## 3.2. Buildable Nodes

Mỗi vị trí xây có các thuộc tính:

- `mountType`
- `occupied`
- `allowedTags`
- `height`
- `stability`
- `coverage`
- `specialInteraction`

### Ví dụ

```text
Node #A17
mountType = WALL
height = Z1
allowedTags = [MEDIUM, UTILITY]
stability = 80
specialInteraction = CLIFF_ANCHOR
```

## 3.3. Path Graph

Quái không pathfind trên toàn bộ bản đồ mỗi frame. Thay vào đó:

- map được chia thành node/segment;
- barricade thay đổi connectivity;
- path được tái tính khi topology thay đổi đáng kể;
- enemy agent bám path graph hiện tại.

Mục tiêu: giữ gameplay linh hoạt nhưng tránh chi phí pathfinding vô hạn khi có hàng nghìn quái.

---

# IV. BUILD SYSTEM

## 4.1. Build flow

```text
Open Build Menu
 → Choose Category
 → Choose Blueprint
 → Preview
 → Validate Node
 → Place
 → Pay Resource
 → Construct
 → Active
```

## 4.2. Validation

Công trình chỉ được đặt khi:

- node hợp lệ;
- không chồng công trình cấm;
- đủ Scrap;
- đáp ứng Tech requirement;
- đáp ứng mount requirement;
- không phá các lane bắt buộc của màn nếu màn khóa lane.

## 4.3. Construction

Hai kiểu:

### Instant Combat Placement
Dùng cho tower/defense cơ bản trong combat khi màn cho phép.

### Build Time Placement
Dùng cho công trình lớn hoặc upgrade quan trọng.

Đề xuất: không dùng build timer dài cho tower combat; timer dài chỉ dành cho progression/Outpost meta.

---

# V. MODULAR TOWER SYSTEM

## 5.1. Data structure

```text
Tower
├── Base
├── Barrel
├── Module[0..N]
├── Level
├── Heat
├── Ammo
├── PowerState
├── TargetState
└── StatusEffects
```

## 5.2. Stat pipeline

```text
Base Stats
   ↓
Barrel Modifier
   ↓
Module Modifier
   ↓
Tech Modifier
   ↓
Battle Modifier
   ↓
Season/Hazard Modifier
   ↓
Final Runtime Stats
```

## 5.3. Không cộng stack vô hạn

Modifier được chia thành:

- additive;
- multiplicative;
- override;
- trade-off.

Các bonus cùng nhóm nên có soft cap.

## 5.4. Tower archetypes

Thay vì hàng chục tower có vai trò trùng nhau, tower nên thuộc 6 archetype:

1. **Single Target**
2. **Anti Swarm**
3. **Armor Break**
4. **Control**
5. **Support/Logistics**
6. **Special/Environmental**

Một tower có thể có secondary role nhưng chỉ có một primary role.

---

# VI. COMBAT RESOLUTION

## 6.1. Targeting pipeline

Mỗi tower chạy:

```text
Acquire Target
 → Filter Valid Target
 → Rank Targets
 → Lock Target
 → Fire
 → Resolve Hit
 → Apply Effect
 → Update Heat/Ammo/Power
```

## 6.2. Target priority

Mỗi tower có một mặc định:

- First
- Nearest
- Strongest
- Lowest HP
- Special Threat
- Custom Priority

Custom Priority chỉ cho phép một số rule đơn giản để không biến UI thành script editor.

## 6.3. Damage model

Đề xuất:

```text
Final Damage
= Base Damage
× Barrel Modifier
× Module Modifier
× Target Interaction
× Temporary Modifier
```

Sau đó mới áp dụng:

```text
Damage After Armor = max(1, Raw Damage - Effective Armor)
```

Đối với damage type đặc biệt, dùng tag interaction thay vì hàng chục stat ẩn.

## 6.4. Damage tags

- Kinetic
- Fire
- Acid
- Electric
- Freeze
- Explosive
- Physical Control

Một enemy chỉ nên có vài resistance/weakness rõ ràng.

---

# VII. ENEMY AI

## 7.1. Enemy state machine

```text
SPAWN
 ↓
MOVE
 ↙   ↘
COMBAT  SPECIAL_ACTION
 ↘   ↙
RECOVER
 ↓
DEAD
```

## 7.2. Special threat priority

Enemy không phải lúc nào cũng target Base.

Ví dụ:

- Acid Bloater → nearest high-value structure.
- EMP Stalker → điện / utility cluster.
- Corpse Weaver → corpse field.
- Harpy → drone/supply object.
- Burrower → strategic interior node.

## 7.3. Enemy intent telegraph

Trước special attack phải có tín hiệu:

- animation;
- icon;
- audio cue;
- màu vùng nguy hiểm;
- short warning.

Mục tiêu: người chơi có thể **phản ứng**, không chết vì hidden rule.

---

# VIII. WAVE DIRECTOR

## 8.1. Wave structure

Mỗi màn có:

```text
Wave 01
  ├── Intro
  ├── Core Spawn
  ├── Support Spawn
  └── End

Wave 02...
```

## 8.2. Điểm quan trọng

Wave Director chỉ được dùng enemy từ **roster cố định của màn**.

Nó có thể thay đổi:

- số lượng;
- delay;
- lane;
- spawn spacing;
- role mix;
- elite chance nếu màn cho phép;
- boss timing.

Không được tự ý thêm species mới.

## 8.3. Threat budget

Mỗi wave có một ngân sách đe dọa.

```text
Wave Threat Budget
=
Base Budget × Wave Multiplier × Difficulty Modifier
```

Mỗi enemy có `Threat Cost`.

Ví dụ đề xuất:

| Enemy | Threat Cost |
|---|---:|
| Crawler | 1 |
| Acid Bloater | 3 |
| Behemoth | 8 |
| EMP Stalker | 7 |
| Burrower | 8 |
| Boss | 50+ |

Con số chỉ để prototype; cần balance bằng playtest.

## 8.4. Pressure curve

Không tăng difficulty chỉ bằng HP.

Ưu tiên tăng theo thứ tự:

1. số lượng;
2. phối hợp enemy;
3. lane pressure;
4. special timing;
5. hazard;
6. elite modifier;
7. HP/damage.

---

# IX. BATTLE PACING

## 9.1. Nhịp 5 pha

### Phase A — Read
Người chơi đọc tình huống.

### Phase B — Pressure
Wave bắt đầu tăng áp lực.

### Phase C — Twist
Xuất hiện quái đặc biệt hoặc thiên tai.

### Phase D — Recovery
Có cửa sổ để sửa chữa/tái tổ chức.

### Phase E — Climax
Boss hoặc wave cuối.

Mỗi trận không nên liên tục căng ở mức 100%; cần có nhịp thở.

## 9.2. Emergency rule

Nếu đồng thời xảy ra quá nhiều threat event, director phải ưu tiên:

```text
Critical threat
> Major hazard
> Minor hazard
> Flavor event
```

Chỉ 1 critical notification và tối đa 1 secondary alert có trạng thái đỏ cùng lúc.

---

# X. RESOURCE SYSTEM

## 10.1. Scrap

Dùng cho:
- xây dựng;
- repair;
- một số tactical action.

Nguồn:
- enemy drops;
- salvage;
- objective;
- post-battle reward.

## 10.2. Ammo

Là resource combat.

Tower dùng ammo theo fire action.

Ammo được:
- sản xuất trong Outpost;
- vận chuyển;
- nhận từ reward.

## 10.3. Power

Power dùng cho:
- electronic tower;
- rail;
- drone;
- một số utility.

Có `generation` và `consumption`.

## 10.4. Water

Water có hai consumer chính:
- sinh hoạt căn cứ;
- cooling.

Không để tower và cư dân tranh trực tiếp cùng một thanh trong mỗi giây. Dùng priority allocation:

```text
Life Support
 > Critical Cooling
 > Optional Cooling
 > Utility
```

## 10.5. Biomass

Tài nguyên meta.

Không phải combat resource mặc định.

Dùng cho:
- Tech Tree;
- research;
- một số unlock.

---

# XI. LOGISTICS SYSTEM

## 11.1. Topology

```text
Storage
   ↓
Supply Hub
   ↓
Route
   ↓
Tower Cluster
```

## 11.2. Route health

Mỗi route có:

- capacity;
- travel time;
- danger;
- uptime.

`Effective Supply = Base Capacity × Uptime × Route Modifier`

## 11.3. Disruption

Route có thể bị:
- destroyed;
- blocked;
- EMP disabled;
- frozen;
- overloaded.

Không nên có quá nhiều failure types cùng lúc ở early game.

## 11.4. Redundancy

Người chơi có thể xây route dự phòng.

Trade-off:
- tốn Scrap/Power;
- tăng resilience.

Đây là một quyết định chiến thuật tốt hơn một buff thẳng.

---

# XII. HEAT SYSTEM

## 12.1. Heat update

```text
New Heat
= Old Heat
+ Shot Heat
- Cooling
```

Cooling phụ thuộc:
- Base;
- Module;
- Water;
- Environment;
- Tướng.

## 12.2. Heat bands

| Heat | State |
|---|---|
| 0–70% | Normal |
| 70–90% | Warm |
| 90–100% | Critical |
| 100% | Jam/Overheat |

## 12.3. Overheat consequence

Đề xuất default:
- fire disabled trong thời gian ngắn;
- heat lockout;
- không tự gây damage trực tiếp trừ module/hazard đặc biệt.

---

# XIII. CORPSE SYSTEM

## 13.1. Corpse accumulation

Không mô phỏng collision của từng xác.

Mỗi khu vực có `CorpseDensity`.

```text
CorpseDensity
+= corpse contribution
-= cleanup
```

## 13.2. Thresholds

- 0–30: no effect.
- 30–70: movement obstruction.
- 70–100: Corpse Bridge candidate.
- 100: Bridge active.

## 13.3. Counters

- Incinerator.
- Cleanup worker.
- Fire hazard.
- Environmental destruction.

Corpse Weaver làm `CorpseDensity` tăng nhanh nhưng không trực tiếp tăng HP cho corpse.

---

# XIV. HAZARD SYSTEM

## 14.1. Hazard budget

Một trận chỉ có một `Major Hazard` active.

Mỗi hazard có:

- warning stage;
- active duration;
- peak;
- recovery;
- counterplay.

## 14.2. Hazard lifecycle

```text
CLEAR
 ↓
TELEGRAPH
 ↓
WARNING
 ↓
ACTIVE
 ↓
PEAK
 ↓
RECOVERY
 ↓
CLEAR
```

## 14.3. Counterplay requirement

Mỗi hazard phải có ít nhất:

- một cách chuẩn bị trước;
- một hành động trong trận;
- một cách tận dụng nếu có thể.

---

# XV. TACTICAL ABILITIES

## 15.1. Nguồn charge

Tactical charge tăng qua:
- combat activity;
- objective progress;
- controlled risk;
- boss damage.

Không tăng vô hạn ngoài trận.

## 15.2. Các nhóm skill

- Offense.
- Control.
- Repair.
- Logistics.
- Survival.

## 15.3. Quy tắc

Không quá 3–4 tactical slots trong combat chuẩn.

Skill phải:
- rõ;
- có cooldown/charge;
- có counterplay;
- tạo decision moment.

---

# XVI. HERO SYSTEM

## 16.1. Hero runtime data

```text
Hero
├── Class
├── Level
├── Equipment
├── Passive
├── Skill 1
├── Skill 2
├── Ultimate
└── Mastery
```

## 16.2. Engineer

**Vai trò:** stabilization.

- Repair.
- Heat management.
- Temporary fortification.

## 16.3. Guardian

**Vai trò:** front-line defense.

- intercept.
- stun/control.
- protect high-value structure.

## 16.4. Bio Specialist

**Vai trò:** resource/combat support.

- Biomass interaction.
- survival buff.
- status control.

## 16.5. Hero death

Hero bị hạ:
- không mất vĩnh viễn;
- có revival/recovery state;
- tạo penalty ngắn trong trận hoặc sau trận tùy difficulty.

Mục tiêu là tạo rủi ro, không tạo rage quit.

---

# XVII. BASE DEFENSE & CORE

## 17.1. Base Core

Base Core là objective chính của trận.

Thuộc tính:
- HP;
- Shield nếu mở khóa;
- emergency state.

## 17.2. Critical damage state

Khi Core xuống ngưỡng thấp:

- UI chuyển emergency;
- một số support system có thể bị hạn chế;
- người chơi được telegraph rõ ràng.

Không nên tạo instant death bất ngờ.

## 17.3. Victory condition

Mặc định:

```text
Survive Final Wave
AND
Core Alive
```

Một số mission có thể thêm điều kiện riêng.

## 17.4. Defeat condition

Mặc định:

```text
Core Destroyed
```

Các defeat phụ chỉ là objective penalty trừ khi mission nói rõ.

---

# XVIII. MISSION DESIGN FRAMEWORK

## 18.1. Mỗi màn phải có

1. **Fantasy** — nơi này là đâu?
2. **Primary Problem** — người chơi phải giải vấn đề gì?
3. **Enemy Roster** — species nào được phép xuất hiện?
4. **Counter Lesson** — người chơi học gì?
5. **Twist** — điều bất ngờ nào?
6. **Climax** — wave/boss cuối.
7. **Reward Reason** — vì sao nên chơi màn này?

## 18.2. 3 loại mission

### Standard
Tập trung progression.

### Specialist
Thiết kế quanh một cơ chế đặc biệt.

### Challenge
Có modifier làm thay đổi cách build.

## 18.3. Mission length

Đề xuất prototype:

- Early: 5–8 phút.
- Mid: 8–12 phút.
- Late: 10–15 phút.
- Boss: 12–18 phút.

Không khóa cứng; playtest quyết định.

---

# XIX. DIFFICULTY SYSTEM

## 19.1. Difficulty không chỉ = HP

Scaling ưu tiên:

1. enemy composition;
2. lane count;
3. special frequency;
4. hazard timing;
5. resource pressure;
6. enemy stat.

## 19.2. Difficulty tiers

Đề xuất:

- Normal.
- Hard.
- Nightmare.

### Normal
Học cơ chế.

### Hard
Buộc build hiệu quả và hiểu counter.

### Nightmare
Tối ưu logistics, module, hazard timing và resource management.

---

# XX. MISSION STAR / SCORE

## 20.1. Three-star framework

### 1 Star
Survive.

### 2 Star
Survive + objective phụ đạt mức cơ bản.

### 3 Star
Survive + challenge condition.

Stars mở thưởng progression, nhưng không được khóa toàn bộ campaign.

## 20.2. Score components

- survival;
- remaining Core HP;
- resource efficiency;
- objectives;
- tactical mastery.

Tránh reward chỉ dựa vào damage vì sẽ khuyến khích build brute force.

---

# XXI. OUTPOST META LOOP

## 21.1. Persistent loop

```text
PLAY MISSION
 ↓
EARN RESOURCES
 ↓
UPGRADE OUTPOST
 ↓
RESEARCH TECH
 ↓
BUILD NEW OPTIONS
 ↓
TRY HARDER MISSION
 ↓
REPEAT
```

## 21.2. Outpost buildings

Các nhóm chính:

- Command.
- Industry.
- Logistics.
- Research.
- Survival.
- Defense.
- Social/Customization.

## 21.3. Construction dependency

Không nên dùng một cây phụ thuộc duy nhất.

Ví dụ:

```text
HQ Stage 2
 → Industry OR Research
```

cả hai có thể đạt về sau, chỉ khác thứ tự ưu tiên.

---

# XXII. TECH TREE RULES

## 22.1. Node types

- Unlock.
- Sidegrade.
- Conversion.
- Efficiency.
- New interaction.

## 22.2. Ưu tiên

Một node tốt nhất nên mở:

> **một cách chơi mới**

thay vì:

> **+5% tất cả chỉ số**.

## 22.3. Respec

Cho phép respec một phần Tech Tree sau khi đạt một mốc progression.

Mục tiêu:
- khuyến khích thử build;
- giảm fear of mistake.

---

# XXIII. MASTERY SYSTEM

## 23.1. Mastery XP

Nhận từ việc dùng đúng hệ thống.

Ví dụ Tower:
- số trận;
- damage;
- kill đặc trưng;
- hoàn thành challenge.

## 23.2. Mastery rewards

Ưu tiên:
- title;
- badge;
- skin;
- VFX;
- profile item.

Không biến Mastery thành stat inflation vô hạn.

---

# XXIV. BLUEPRINT SYSTEM

## 24.1. Blueprint lifecycle

```text
Fragment
 ↓
Threshold reached
 ↓
Craft Blueprint
 ↓
Unlock
 ↓
Upgrade / Mastery
```

## 24.2. Rarity

Đề xuất 4 bậc:

- Common.
- Advanced.
- Prototype.
- Legendary.

Rarity nên đại diện cho **độ khác biệt cơ chế**, không chỉ DPS cao hơn.

---

# XXV. ECONOMY RULES

## 25.1. Currency classes

### Soft
- Scrap.
- Biomass.

### Operational
- Ammo.
- Power.
- Water.

### Premium
- Rad-Gems hoặc tên premium currency được chốt sau.

## 25.2. Economy sinks

Soft currency sink:
- repair;
- craft;
- research;
- expedition preparation.

Premium sink:
- cosmetic;
- Plus/Pro;
- seasonal pass;
- expansion;
- optional convenience.

## 25.3. Inflation control

Mọi nguồn currency mới phải có sink tương ứng.

---

# XXVI. PLUS / PRO SYSTEM LOGIC

## 26.1. Entitlement check

```text
Free
Plus
Pro
```

Server/profile phải lưu entitlement độc lập với save gameplay.

## 26.2. PvE modifiers

Modifier phải đi qua một lớp riêng:

```text
Base Stat
 → Gameplay Modifier
 → Entitlement Modifier
 → Mode Cap
 → Final Stat
```

## 26.3. PvP normalization

Trong mode competitive:

```text
Final PvP Stat
=
Base Gameplay Stat
+ Allowed Competitive Bonus
```

Các modifier bị cấm sẽ bị loại tại bước mode validation.

---

# XXVII. SAVE / PERSISTENCE

## 27.1. Persistent data

Lưu lâu dài:
- account progression;
- Outpost;
- Tech;
- Blueprint;
- Heroes;
- Mastery;
- Cosmetics;
- quest progress;
- achievements.

## 27.2. Match data

Chỉ lưu:
- current mission state;
- wave;
- combat resources;
- structures;
- temporary modifiers.

## 27.3. Crash recovery

Nếu app bị đóng giữa trận:

- Single-player: cho phép resume nếu save checkpoint hợp lệ.
- Online/PvP: không cho resume state theo cách có thể exploit.

---

# XXVIII. REPLAYABILITY

## 28.1. Cùng một màn có thể chơi lại vì

- challenge;
- stars;
- mastery;
- build experimentation;
- high score;
- resource farming;
- event modifier;
- difficulty.

## 28.2. Randomization có kiểm soát

Không random mọi thứ.

Có thể random:
- lane variation;
- spawn timing;
- salvage node;
- hazard timing trong khoảng được phép.

Không random:
- enemy species ngoài roster;
- core mission identity;
- primary objective.

---

# XXIX. TELEMETRY & BALANCE DATA

## 29.1. Nên ghi nhận

- win rate/mode;
- average completion time;
- wave where players fail;
- tower pick rate;
- module pick rate;
- hero pick rate;
- resource shortage frequency;
- overheat frequency;
- enemy lethality;
- abandon rate;
- retry rate;
- Plus/Pro conversion;
- rewarded-ad opt-in rate.

## 29.2. Quan trọng nhất

Nếu một tower có pick rate thấp, **không mặc định buff damage**.

Kiểm tra lần lượt:

1. Có dễ hiểu không?
2. Counter của nó có đủ rõ không?
3. Nó có bị một tower khác thay thế hoàn toàn không?
4. Cost có hợp lý không?
5. UX đặt/điều khiển có tệ không?

---

# XXX. PERFORMANCE & SIMULATION RULES

## 30.1. Enemy pooling

Dùng object pooling cho:
- enemy;
- projectile;
- hit effect;
- debris;
- temporary VFX.

## 30.2. Simulation tiers

### Tier 0 — Full
Enemy gần camera/đang combat trực tiếp.

### Tier 1 — Reduced
Enemy ngoài vùng quan sát.

### Tier 2 — Aggregate
Swarm xa camera được mô phỏng theo nhóm khi có thể.

## 30.3. Visual vs gameplay entity

Không đồng nhất:

> 1000 sprites = 1000 full AI agents.

Đồ họa đông không được kéo theo simulation quá nặng.

---

# XXXI. UI / INFORMATION HIERARCHY

## 31.1. Combat HUD

Ưu tiên:

**Top:**
- Wave.
- Core HP.
- Threat alert.

**Top/Side:**
- Scrap.
- Ammo.
- Power.
- Water.

**Bottom:**
- Build menu.
- Hero.
- Tactical.
- Pause.

## 31.2. Alert priority

### Red
Có thể làm mất trận nếu không xử lý.

### Orange
Nguy hiểm nhưng còn thời gian.

### Blue/White
Thông tin.

Không dùng mọi cảnh báo thành màu đỏ.

---

# XXXII. AUDIO FEEDBACK

## 32.1. Gameplay audio phải trả lời 3 câu

1. Có gì vừa xảy ra?
2. Nó có nguy hiểm không?
3. Tôi có cần làm gì không?

## 32.2. Signature sounds

- EMP charge.
- Burrower digging.
- Corpse density threshold.
- Core critical.
- Overheat warning.
- Boss arrival.
- Hazard telegraph.

Mục tiêu là có thể phản ứng với nhiều thông tin **mà không cần nhìn liên tục vào UI**.

---

# XXXIII. TUTORIAL / ONBOARDING

## 33.1. Dạy theo hành động

Không dạy toàn bộ hệ thống bằng popup.

### Tutorial Mission 1
Build + target + survive.

### Mission 2
Ammo + logistics.

### Mission 3
Module + tower combo.

### Mission 4
Heat + water.

### Mission 5
Special enemy.

### Mission 6
Hero + tactical.

### Mission 7
Hazard.

Sau đó mở meta systems từng bước.

## 33.2. Rule

**Một tutorial beat = một cơ chế mới.**

Không giới thiệu 5 cơ chế cùng một lúc.

---

# XXXIV. EXAMPLE GAMEPLAY SCENARIO

## Mission: Abandoned Factory

### Roster
- Crawler.
- Acid Bloater.
- Behemoth.

### Primary lesson
Crowd control + armor break.

### Prepare
Người chơi mang:
- Anti Swarm tower.
- Acid/armor-break tower.
- Long range.
- Engineer.

### Wave 1
Crawler nhẹ.

Mục tiêu: học targeting.

### Wave 2
Crawler + Acid Bloater.

Mục tiêu: ưu tiên Bloater.

### Wave 3
Crawler + Behemoth.

Mục tiêu: phá giáp.

### Wave 4
Mixed pressure.

Một thiên tai nhỏ có thể telegraph.

### Final
Behemoth heavy push + Crawler swarm.

### Post-battle
Battle Report chỉ ra:
- Crawler clear tốt.
- Behemoth xử lý chậm.
- Ammo hiệu quả 82%.

Người chơi có thể:
- nâng armor-break;
- đổi module;
- đổi loadout;
- thử lại để 3-star.

---

# XXXV. END-TO-END SESSION

```text
Open Game
 ↓
Review Outpost
 ↓
Claim completed Expedition
 ↓
Upgrade Tech / Building
 ↓
Select Mission
 ↓
Read Enemy Intel
 ↓
Select Loadout
 ↓
Briefing
 ↓
Build
 ↓
Wave Combat
 ↓
React to Threats
 ↓
Victory / Defeat
 ↓
Battle Report
 ↓
Rewards
 ↓
Mastery / Blueprint / Tech
 ↓
Outpost Expansion
 ↓
Next Mission
```

Mục tiêu là người chơi luôn có **một quyết định tiếp theo rõ ràng**, nhưng không bị ép bằng năng lượng/stamina.

---

# XXXVI. IMPLEMENTATION PRIORITY

## P0 — Prototype bắt buộc

1. Battlefield.
2. Build placement.
3. 3 modular towers.
4. 3 enemy archetypes.
5. Wave Director.
6. Core HP.
7. Ammo.
8. Basic logistics.
9. Basic hero.
10. Victory/Defeat.

## P1 — Core depth

1. Full modular system.
2. Heat.
3. Power.
4. Water.
5. 6–8 enemy species.
6. Mission objectives.
7. Battle Report.
8. Enemy Intel.
9. Tech Tree.

## P2 — Meta

1. Outpost Evolution.
2. Citizens.
3. Morale.
4. Expedition.
5. Mastery.
6. Blueprint Crafting.
7. World Events.
8. Seasons.

## P3 — Live / Social

1. Guild.
2. Guild Defense.
3. PvP Raid.
4. Plus/Pro.
5. Seasonal Pass.
6. Cosmetic economy.
7. Expansion.

---

# XXXVII. DEFINITION OF DONE — CORE GAMEPLAY

Prototype được xem là đạt khi:

- Người mới có thể hiểu cách chơi sau vài mission mà không cần wiki.
- Một mission có thể thua vì **quyết định sai**, không chỉ vì stat thấp.
- Mỗi enemy trong roster có ít nhất một counter rõ.
- Modular Tower tạo ra build khác nhau thật sự.
- Logistics có ý nghĩa nhưng không thành micromanagement.
- Heat/Water tạo quyết định chứ không chỉ phạt.
- Thiên tai làm thay đổi cách chơi.
- Trụ Sở tạo ra cảm giác tiến bộ lâu dài.
- Người chơi có lý do replay mission.
- UI vẫn đọc được khi swarm đông.
- Không có một build duy nhất thống trị toàn bộ roster.

---

# XXXVIII. NGUYÊN TẮC CÂN BẰNG CUỐI

> **Không balance game bằng cách làm mọi thứ mạnh ngang nhau.**
>
> Balance bằng cách tạo ra:
>
> **trade-off + counter + context.**

Một Tower có thể rất mạnh trong một tình huống nhưng kém ở tình huống khác.

Một Module có thể tăng fire rate nhưng tăng heat.

Một logistics route có thể nhanh nhưng dễ bị phá.

Một Hero có thể mạnh ở tuyến trước nhưng yếu khi bị EMP.

Một mùa có thể gây bất lợi nhưng đồng thời mở ra một chiến thuật đặc biệt.

Đó là nền tảng để Last Outpost có **chiều sâu thật sự thay vì chỉ có nhiều chỉ số**.
