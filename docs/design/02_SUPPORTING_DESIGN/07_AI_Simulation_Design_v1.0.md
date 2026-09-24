# LAST OUTPOST — AI & SIMULATION DESIGN v1.0

> Tài liệu thiết kế AI & Simulation cho hệ thống chiến đấu của Last Outpost.
>
> Vai trò: đặc tả cách quái, Boss, Wave Director, pathfinding và battle simulation vận hành ở mức gameplay/system. Các giá trị số là baseline/prototype, cần playtest trước khi khóa.

---

## 0. Mục tiêu thiết kế

AI của Last Outpost phải tạo cảm giác quái đang **ra quyết định theo mục tiêu và tình huống**, thay vì chỉ đi thẳng tới căn cứ.

Mục tiêu chính:

1. Dễ đọc đối với người chơi mobile.
2. Có counter rõ ràng với Tower/build.
3. Hỗ trợ roster cố định theo từng màn.
4. Hỗ trợ swarm lớn mà vẫn kiểm soát CPU/bộ nhớ.
5. Hỗ trợ Boss có hành vi riêng.
6. Không dùng random thuần túy để tạo độ khó.

Nguyên tắc:

> PLAYER READABILITY > AI COMPLEXITY.

AI phức tạp bên trong nhưng biểu hiện ra ngoài phải dễ hiểu.

---

## I. MÔ HÌNH AI 4 LỚP

Mỗi enemy sử dụng 4 lớp chính:

### 1. Intent
Mục tiêu hiện tại của enemy.

Ví dụ:
- Reach Core
- Attack Tower
- Attack Logistics
- Support Ally
- Escape Hazard
- Burrow
- Execute Ability

### 2. Navigation
Cách enemy tiếp cận mục tiêu.

### 3. Action
Hành động khi đủ điều kiện:
- Move
- Attack
- Cast
- Charge
- Retreat
- Reposition

### 4. Reaction
Phản ứng với thay đổi trận đấu:
- bị slow
- mất shield
- bị EMP
- đường đi bị block
- Tower bị phá
- Boss phase đổi

---

## II. AI STATE MACHINE

State cơ bản:

```text
SPAWN
  ↓
ASSESS
  ↓
MOVE ───────→ REPOSITION
  ↓                ↑
ENGAGE ─────→ ABILITY
  ↓                ↓
ATTACK ←───────────
  ↓
DEATH
```

State mở rộng:

- Spawn
- Assess
- Move
- Search Target
- Engage
- Attack
- Cast Ability
- Reposition
- Retreat
- Burrow
- Unburrow
- Stagger
- Death

State transition ưu tiên:

1. Forced state / disable
2. Emergency objective
3. Ability condition
4. Combat target
5. Route movement

---

## III. TARGET SELECTION

Enemy không nên mặc định luôn đánh Core.

Target score đề xuất:

```text
Score =
ObjectiveWeight
+ DistanceWeight
+ VulnerabilityWeight
+ ThreatWeight
+ SpecialInterestWeight
- PathCost
```

### Target classes

- Base Core
- Wall / Gate
- Tower
- Logistics Hub
- Ammo Route
- Power Node
- Commander
- Civilian / Internal Asset
- Drone

Mỗi enemy chỉ được phép sử dụng các class phù hợp với archetype của nó.

---

## IV. PATHFINDING

### 1. Ground
Dùng navigation graph/cost map thay vì mỗi enemy chạy A* độc lập liên tục.

### 2. Flying
Không bị ràng buộc bởi ground path, nhưng có attack corridor và obstacle avoidance riêng.

### 3. Burrow
Sử dụng underground graph simplification; chỉ cập nhật điểm nổi lên hợp lệ.

### 4. Dynamic Terrain
Khi tường/bẫy/đường ray thay đổi:

- đánh dấu vùng path bị ảnh hưởng;
- cập nhật navigation patch;
- tái sử dụng graph cũ ở vùng không thay đổi.

Không rebuild toàn bản đồ cho mỗi thay đổi nhỏ.

---

## V. SWARM SYSTEM

Mục tiêu hỗ trợ 1000+ enemies mà không chạy logic đầy đủ cho 1000 entity ở mọi frame.

### 1. Agent LOD

**LOD 0 — Critical**
- gần camera;
- đang chiến đấu;
- Boss/Elite;
- full decision update.

**LOD 1 — Active**
- trong battle area;
- decision update thưa hơn.

**LOD 2 — Swarm**
- nhóm xa camera;
- dùng shared movement/formation logic.

**LOD 3 — Abstracted**
- quá xa gameplay focus;
- mô phỏng theo group count/stat.

### 2. Group Steering
Crawler có thể dùng:

- shared path corridor;
- separation;
- cohesion;
- obstacle avoidance;
- local target selection.

Không để từng con tự tính đường hoàn toàn độc lập.

---

## VI. ENEMY ARCHETYPE BEHAVIOR

### 1. Crawler
- ưu tiên Core;
- có thể leo low wall;
- swarm;
- tăng áp lực khi tập trung.

### 2. Acid Bloater
- tránh đi quá sát đồng loại nếu explosion risk cao;
- ưu tiên Tower/structure giá trị cao khi đã vào range;
- death explosion là phần thưởng/rủi ro cho người chơi.

### 3. Iron-Hide Behemoth
- ưu tiên obstacle cứng;
- charge khi có lane phù hợp;
- không bị dụ bởi target nhỏ nếu objective vẫn còn.

### 4. EMP Stalker
- tìm cluster của electronic Tower;
- ưu tiên vị trí đạt hiệu quả EMP cao;
- sau khi cast có thể đổi objective.

### 5. Tunnel Burrower
- chọn burrow entry;
- di chuyển underground;
- chọn emergence point theo target value;
- telegraph rõ trước khi trồi lên.

### 6. Corpse Weaver
- tránh giao tranh trực tiếp;
- ưu tiên corpse field;
- tạo corpse bridge theo mục tiêu lane;
- nếu bị ép có thể retreat.

### 7. Scavenger Harpy
- ưu tiên Drone / exposed logistics;
- bay theo attack corridor;
- rút ra sau khi hoàn thành grab/attack.

### 8. Spore Spreader
- ưu tiên vị trí vùng có nhiều Tower;
- deploy cloud;
- ưu tiên sống sót thay vì lao vào Core.

### 9. Riot Mech Mutate
- giữ mặt shield về phía damage source;
- reposition khi bị flank;
- ưu tiên lane có Tower chống lưng nhiều nhất.

### 10. Siren Screamer
- tìm vị trí buff tối đa số lượng ally;
- cast khi đủ ally trong radius;
- tránh đứng đầu lane.

### 11. Colossus Titan
- phase-based Boss AI;
- phá hoặc ném cấu trúc;
- thay đổi target theo battlefield state;
- có telegraph cho mọi major action.

### 12. Hive Mother
- giữ khoảng cách;
- tạo spawn points;
- ưu tiên sản sinh Crawler khi áp lực thấp;
- đổi hành vi khi HP/phase xuống ngưỡng.

---

## VII. WAVE DIRECTOR

Wave Director không chỉ spawn enemy theo timer.

Nó quản lý:

- Threat Budget
- pacing
- composition
- recovery window
- escalation
- special event timing
- boss entry

### Threat Budget

Mỗi mission có ngân sách threat theo wave.

Ví dụ prototype:

| Enemy | Threat Cost |
|---|---:|
| Crawler | 1 |
| Acid Bloater | 5 |
| Behemoth | 12 |
| EMP Stalker | 10 |
| Burrower | 9 |
| Corpse Weaver | 8 |
| Harpy | 7 |
| Spore Spreader | 8 |
| Riot Mech | 11 |
| Siren | 9 |

Con số là baseline để balance, không phải giá trị cuối.

### Wave Director rules

- Không spawn bất kỳ enemy ngoài fixed roster của mission.
- Không vượt budget wave.
- Không stack quá nhiều mechanics mới cùng lúc.
- Có recovery window sau spike lớn.
- Boss phải có pre-boss read/telegraph.

---

## VIII. DIFFICULTY DIRECTOR

Độ khó nên tăng chủ yếu bằng **composition và decision pressure**, không phải chỉ tăng HP.

Ưu tiên:

1. Enemy composition
2. Timing
3. Lane pressure
4. Ability combinations
5. Hazard
6. Stat multiplier

Stat multiplier chỉ là lớp cuối.

---

## IX. BOSS AI FRAMEWORK

Boss có 3–5 phase tùy loại.

Mỗi phase gồm:

- entrance telegraph;
- behavior package;
- ability set;
- weakness window;
- transition cue;
- recovery/celebration.

Boss không được đổi phase bất ngờ mà không có feedback.

### Boss design rule

Mỗi Boss phải có:

- 1 signature mechanic;
- 1 readable weakness;
- 1 escalation;
- 1 punishable mistake;
- 1 clear victory moment.

---

## X. HAZARD & ENVIRONMENT SIMULATION

Các hazard cần có owner/system riêng:

- Acid Rain
- Fire
- Toxic Spore
- EMP
- Earthquake
- Flood/Water
- Extreme Heat/Cold

Không cho mỗi enemy tự xử lý physics độc lập nếu cùng một hazard.

Dùng shared hazard field/volume để tính tác động theo vùng.

---

## XI. COMBAT SIMULATION TICK

Đề xuất tách simulation khỏi render.

### Simulation

- Fixed-step hoặc deterministic-friendly tick.
- AI update theo tier.
- Damage/status resolution trong simulation layer.

### Render

- interpolation;
- VFX;
- animation;
- camera;
- UI feedback.

Render FPS không được quyết định logic chiến đấu.

---

## XII. UPDATE BUDGET

Không phải entity nào cũng được update cùng tần suất.

Ví dụ prototype:

| Tier | AI update |
|---|---|
| Critical/Boss | mỗi tick |
| Active combat | 5–10 Hz |
| Swarm | 2–5 Hz |
| Abstracted | theo event/group |

Mỗi frame có CPU budget rõ ràng cho:

- pathfinding;
- target search;
- combat;
- status;
- spawning.

Nếu vượt budget:

> giảm update frequency trước khi giảm gameplay fidelity.

---

## XIII. OBJECT POOLING & MEMORY

Pool cho:

- Enemy entities
- Projectile
- VFX
- Damage numbers
- Status FX
- Corpse markers
- Drones

Hạn chế:

- runtime allocation lớn;
- instantiate/destroy liên tục;
- texture churn.

---

## XIV. DETERMINISM & REPLAY

Battle simulation nên có seed.

Seed quyết định:

- wave variation hợp lệ;
- spawn variation;
- một số hazard timing;
- loot roll trong phạm vi cho phép.

Không dùng seed để phá fixed roster hoặc objective rules.

Seed có thể dùng cho:

- Challenge mode;
- replay debug;
- bug reproduction;
- daily challenge.

---

## XV. TELEGRAPH & PLAYER READABILITY

Mọi major AI action phải có tối thiểu một tín hiệu:

- animation;
- sound;
- icon;
- ground marker;
- screen cue.

Ví dụ Burrower:

```text
Ground vibration
      ↓
Warning marker
      ↓
Short anticipation
      ↓
Burrower emergence
```

Mục tiêu là người chơi có thể nói:

> “Mình đã nhìn thấy nó và có cơ hội phản ứng.”

---

## XVI. FAILURE & RECOVERY

AI không được cố tình chain-lock người chơi vô hạn.

Ví dụ:

EMP → stun → another stun → no control → defeat

không nên trở thành pattern phổ biến.

Director phải có recovery rule:

- giảm pressure;
- mở lane an toàn;
- cho player setup window;
- hạn chế CC overlap.

---

## XVII. TUTORIAL AI

Enemy AI trong tutorial bị giới hạn hành vi.

Ví dụ:

Mission 1:
- Crawler chỉ đi lane cơ bản.

Mission 2:
- thêm obstacle interaction.

Mission 3:
- thêm special target.

Sau đó mới giới thiệu:
- EMP;
- Burrow;
- Corpse Bridge;
- flying;
- Boss phase.

---

## XVIII. DEBUG & GM TOOLS

Prototype phải có debug overlay cho:

- current state;
- target;
- path;
- threat score;
- wave budget;
- AI update tier;
- damage source;
- status list;
- navigation node;
- boss phase.

Có command để:

- spawn enemy;
- skip wave;
- force boss phase;
- kill target;
- trigger hazard;
- pause simulation;
- set seed.

---

## XIX. AI TELEMETRY

Thu thập tối thiểu:

- time-to-target;
- target switches;
- path recalculations;
- average AI update cost;
- enemy survival time;
- boss phase duration;
- CC uptime;
- EMP hit count;
- Burrower emergence success;
- Corpse Bridge events;
- deaths by cause.

Dùng để tìm bottleneck gameplay và performance.

---

## XX. DEFINITION OF DONE

AI & Simulation v1.0 đạt yêu cầu khi:

- [ ] Fixed roster được tôn trọng.
- [ ] Enemy có hành vi khác nhau rõ ràng.
- [ ] Target selection không tạo lỗi vô lý.
- [ ] Pathfinding xử lý dynamic obstacles.
- [ ] Swarm lớn không gây spike CPU nghiêm trọng.
- [ ] Boss có phase + telegraph.
- [ ] Wave Director kiểm soát pacing.
- [ ] Simulation tách khỏi render.
- [ ] Seed tái tạo được scenario cần debug.
- [ ] Debug overlay đủ để tìm lỗi AI.
- [ ] Không có CC chain-lock phổ biến.
- [ ] Combat vẫn rõ ràng trên mobile.

---

## XXI. PRIORITY

### P0 — Prototype Core
- State machine
- Ground navigation
- Target selection
- Crawler
- Behemoth
- EMP Stalker
- Wave Director
- Fixed-step simulation

### P1 — Gameplay Expansion
- Burrower
- Corpse Weaver
- Harpy
- Spore Spreader
- Siren
- Dynamic navigation
- Hazard fields

### P2 — Boss & Scale
- Colossus
- Hive Mother
- Swarm LOD
- Group steering
- Advanced performance tooling

### P3 — Live/Advanced
- replay/seeded challenge
- advanced simulation telemetry
- AI tuning automation
- large-scale optimization

---

## XXII. CORE RULES SUMMARY

> **Fixed roster creates readability.**
>
> **Composition creates difficulty.**
>
> **AI behavior creates identity.**
>
> **Wave Director creates pacing.**
>
> **Telegraph creates fairness.**
>
> **Simulation separation creates performance.**
>
> **Seeds create reproducibility.**

---
