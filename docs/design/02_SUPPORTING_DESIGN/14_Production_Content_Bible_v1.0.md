# LAST OUTPOST — PRODUCTION CONTENT BIBLE v1.0

## 0. MỤC ĐÍCH

Production Content Bible là quy chuẩn cấp production cho mọi content mới của Last Outpost.

Nó đứng trên các template riêng lẻ và trả lời:

- Content nào được phép tồn tại?
- Content mới cần đóng vai trò gì?
- Khi nào một content đủ khác biệt để đáng sản xuất?
- Mỗi loại content phải có quality bar nào?
- Làm sao giữ cùng một visual/gameplay language khi số lượng content tăng lên?
- Làm sao tránh power creep và content bloat?
- Làm sao chuyển từ 5 mission của Vertical Slice lên hàng trăm mission mà không làm game loãng?

Nguyên tắc:

> **Hệ thống tạo ra content; content không được buộc hệ thống phải viết lại cho từng trường hợp.**

---

# I. CONTENT NORTH STAR

Mỗi content mới phải đồng thời đạt 5 mục tiêu:

### 1. Identity
Người chơi biết content này khác gì.

### 2. Gameplay Purpose
Content tạo ra một quyết định, một counter, một nhịp hoặc một mục tiêu rõ ràng.

### 3. Readability
Người chơi hiểu content bằng hình ảnh/âm thanh/tương tác.

### 4. Progression Value
Content có vai trò trong tiến trình hoặc replayability.

### 5. Production Value
Content có thể tái sử dụng technology và pipeline hiện có.

Nếu thiếu nhiều mục trên:

> Không sản xuất content chỉ để tăng số lượng.

---

# II. CONTENT PYRAMID

```text
                 WORLD
                   │
                REGION
                   │
                 BIOME
                   │
                CHAPTER
                   │
                MISSION
                   │
                 WAVE
                   │
              SPAWN GROUP
```

Song song:

```text
ENEMY
TOWER
MODULE
COMMANDER
BOSS
HAZARD
OBJECTIVE
REWARD
EVENT
EXPEDITION
COSMETIC
```

Content cấp dưới không được phá logic của cấp trên.

---

# III. CONTENT ROLE SYSTEM

Mỗi content phải có một **Primary Role**.

### Mission
- Teach
- Pressure
- Counter
- Combine
- Mastery
- Boss
- Challenge

### Enemy
- Swarm
- Tank
- Disruptor
- Support
- Specialist
- Air
- Burrow
- Boss

### Tower
- Single Target
- Crowd Control
- Armor Break
- Support
- Long Range
- Utility
- Emergency / Specialist

### Hazard
- Damage
- Disruption
- Movement
- Visibility
- Terrain
- Resource Pressure

Không dùng nhiều role ngang nhau nếu điều đó làm identity mờ.

---

# IV. CONTENT COMPLEXITY BUDGET

Một mission không được giới thiệu quá nhiều mechanic mới cùng lúc.

### Complexity Levels

**C0 — Familiar**
- cơ chế người chơi đã biết.

**C1 — New Element**
- một mechanic mới.

**C2 — Combination**
- kết hợp mechanic cũ + mới.

**C3 — Mastery**
- nhiều hệ thống cùng tương tác.

Quy tắc:
> Early content ưu tiên C0–C1.
> Mid content ưu tiên C1–C2.
> Late content ưu tiên C2–C3.

---

# V. CONTENT MATURITY

Mỗi content có maturity:

```text
Draft
→ Prototype
→ Validated
→ Production
→ Live
→ Retired
```

### Draft
Ý tưởng.

### Prototype
Có gameplay thử.

### Validated
Đã playtest và có data.

### Production
Đã đủ art/audio/UI/QA.

### Live
Đã phát hành.

### Retired
Không dùng trong progression mới nhưng giữ compatibility nếu cần.

---

# VI. MISSION PRODUCTION BIBLE

## Mission DNA

Mỗi mission phải có:

```text
Mission ID
Theme
Lesson
Primary Role
Enemy Roster
Terrain Identity
Wave Identity
Hazard
Objective
Reward
Expected Failure
Expected Counter
```

### Mission Duration

Bắt buộc:

> **5–15 phút**

Target:
- Early: 5–8
- Mid: 7–12
- Late: 9–15
- Boss: 10–15

Mission không được vượt 15 phút chỉ bằng cách:
- tăng HP;
- tăng damage;
- kéo recovery gap;
- kéo spawn timing.

---

# VII. MISSION DIFFERENTIATION

Hai mission liên tiếp không nên trùng hoàn toàn ở:

- lesson;
- map layout;
- roster;
- objective;
- hazard;
- pacing.

Khuyến nghị:
> thay đổi ít nhất 2–3 yếu tố gameplay có ý nghĩa giữa hai mission.

### Ví dụ

Mission A:
> Swarm + Open Area + Survive

Mission B:
> Swarm + Chokepoint + Protect Supply

Enemy có thể giống nhưng experience khác.

---

# VIII. MISSION FAMILY LIBRARY

Chuẩn hóa các family:

1. Standard Defense
2. Swarm Control
3. Heavy Break
4. Supply Protection
5. Internal Breach
6. Resource Scarcity
7. Hazard Survival
8. Boss Siege
9. Challenge
10. Event Mission

Mỗi family có:
- template;
- expected pacing;
- expected roster;
- expected objective style;
- balance profile.

---

# IX. MISSION CHAPTER CURRICULUM

Chapter nên đi:

```text
Introduce
→ Reinforce
→ Combine
→ Master
```

Ví dụ enemy:

```text
Introduce Crawler
→ Reinforce Crawler
→ Combine Crawler + Acid Bloater
→ Master Crawler + Armor
```

Không introduce 4 mechanic mới trong một mission nếu không có lý do rõ ràng.

---

# X. WAVE PRODUCTION BIBLE

Wave phải có role:

- Introduction
- Pressure
- Recovery
- Counter Check
- Disruption
- Escalation
- Boss Preparation
- Finale

Pacing mẫu:

```text
Intro
→ Pressure
→ Recovery
→ New Threat
→ Pressure
→ Peak
→ Recovery
→ Finale
```

Wave recovery không phải filler.

---

# XI. ENEMY PRODUCTION STANDARD

Mỗi enemy mới phải có:

### Gameplay
- primary role;
- secondary role nếu cần;
- threat cost;
- movement;
- attack;
- special mechanic;
- counter;
- weakness;
- frequency recommendation.

### Readability
- silhouette;
- color/material cue;
- telegraph;
- hit reaction;
- attack cue;
- audio cue.

### Production
- model/sprite;
- animation set;
- VFX;
- SFX;
- localization;
- content definition.

### Quality Bar

Người chơi phải trả lời được:

> “Nó là gì?”
>
> “Nó nguy hiểm ở đâu?”
>
> “Tôi nên xử lý nó thế nào?”

---

# XII. TOWER PRODUCTION STANDARD

Tower phải có identity riêng.

Mỗi Tower:

```text
Base
+
Barrel
+
Module
```

### Base identity
- stability;
- mobility;
- cooling;
- mount.

### Barrel identity
- damage family;
- range;
- firing behavior.

### Module identity
- utility;
- interaction;
- trade-off.

Một Tower không đạt chuẩn nếu:

> Chỉ mạnh hơn Tower cũ mà không tạo decision mới.

---

# XIII. MODULE POWER DESIGN

Module ưu tiên:

- trade-off;
- alternative;
- interaction;
- specialization.

Ví dụ:

```text
Overclock
Fire Rate ↑
Heat ↑

Cooling
Heat ↓
Resource Cost ↑

Utility
Damage ↓
Control ↑
```

Tránh module:
> +damage thuần túy lặp lại.

---

# XIV. BOSS PRODUCTION STANDARD

Boss phải có:

- clear entrance;
- phase identity;
- telegraph;
- counter;
- weak point hoặc response window;
- arena interaction nếu phù hợp;
- recovery window;
- defeat payoff.

Boss không được chỉ là:

> HP × 10.

### Boss Design Formula

```text
Threat
+
Readable Mechanic
+
Counter
+
Escalation
+
Payoff
```

---

# XV. BOSS ROSTER STRATEGY

Boss không nên xuất hiện quá dày.

Khuyến nghị:
- Chapter boss: payoff cho chapter.
- Major boss: tổng hợp nhiều mechanic.
- Event boss: modifier đặc biệt.

Mỗi boss cần có identity gameplay riêng, không chỉ skin mới.

---

# XVI. BIOME PRODUCTION STANDARD

Mỗi biome cần:

### Visual Identity
- palette;
- material;
- landmark;
- props;
- lighting;
- atmosphere.

### Gameplay Identity
Ít nhất một thay đổi đáng kể về:
- terrain;
- movement;
- visibility;
- resource;
- hazard;
- positioning.

Nếu biome chỉ đổi màu:
> chưa đạt.

---

# XVII. BIOME FAMILY

Các hướng nền:

### Ruined City
Concrete, vehicles, vegetation, urban chokepoints.

### Industrial / Mine
Rails, machinery, vertical structures, industrial hazards.

### Radioactive Marsh
Water, roots, fog, visibility/resource pressure.

### Frozen Zone
Ice, snow, frozen routes, cold-specific logistics.

### Desert Wasteland
Open terrain, dust, heat/resource pressure.

Mỗi biome phải giữ chung visual family của Last Outpost.

---

# XVIII. HAZARD PRODUCTION STANDARD

Hazard phải có:

```text
Telegraph
→ Threat
→ Interaction
→ Response
→ Recovery
```

Hazard tốt tạo:
> Risk + Response + Opportunity.

Không dùng random damage làm nội dung duy nhất.

---

# XIX. OBJECTIVE BIBLE

Objective families:

### Survival
Survive all waves.

### Protection
Giữ building/resource/area.

### Elimination
Tiêu diệt target đặc biệt.

### Conservation
Giới hạn resource hoặc building loss.

### Time
Clear trong khoảng thời gian.

### Tactical
Thực hiện một strategy cụ thể.

Secondary objective:
- optional;
- có reward;
- không phá core strategy.

---

# XX. REWARD CONTENT STANDARD

Rewards chia:

### Immediate
- Scrap
- Materials
- basic resources

### Progression
- Blueprint Fragment
- Mastery XP
- unlock progress

### Aspirational
- rare blueprint;
- cosmetic;
- event reward.

Reward phải tạo:

```text
Action
→ Reward
→ Visible Progress
→ Next Goal
```

---

# XXI. EVENT PRODUCTION STANDARD

Event phải có:

- theme;
- modifier;
- unique reward;
- visual identity;
- clear duration.

Event không nên bắt người chơi học một game hoàn toàn khác trong thời gian ngắn.

### Event Loop

```text
Enter
→ Discover
→ Play
→ Earn Event Currency
→ Spend
→ Unlock
→ Complete
```

---

# XXII. SEASONAL CONTENT

Season nên tập trung vào:
- modifiers;
- challenges;
- cosmetics;
- missions;
- story/event layer.

Không reset:
- Outpost;
- permanent progression;
- owned Tower;
- owned Blueprint;
- permanent Mastery.

---

# XXIII. CONTENT REPLAYABILITY

Replayability đến từ:

- alternate objectives;
- challenge modifiers;
- different Tower builds;
- different Module builds;
- mastery;
- weekly seed/challenge;
- score;
- event variation.

Không phụ thuộc chỉ vào:
> “chơi lại y hệt để cày resource.”

---

# XXIV. ANTI-REPETITION MATRIX

Mỗi mission ghi 7 trục:

| Trục | Ví dụ |
|---|---|
| Map | Open / Choke / Multi-route |
| Roster | Swarm / Armor / Disrupt |
| Lesson | Build / Counter / Logistics |
| Wave | Dense / Burst / Delayed |
| Objective | Survive / Protect / Speed |
| Hazard | None / Weather / Terrain |
| Reward | Standard / Blueprint / Mastery |

Hai mission liên tiếp không nên giống nhau trên đa số trục.

---

# XXV. CONTENT POWER CREEP CONTROL

Content mới không nên đơn giản:

> mạnh hơn content cũ.

Thay vào đó:

```text
New Content
=
New Interaction
OR
New Option
OR
New Playstyle
OR
New Challenge
```

Stat increase chỉ là một phần nhỏ và phải có lý do.

---

# XXVI. CONTENT DEPENDENCY RULES

Content mới phải kiểm tra:

- prerequisite;
- player knowledge;
- unlock path;
- reward dependency;
- art dependency;
- localization dependency;
- VFX/audio dependency.

Không đưa mission vào production nếu counter cần thiết chưa tồn tại hoặc chưa được giới thiệu, trừ deliberate challenge.

---

# XXVII. PRODUCTION BUDGET

Mỗi content card ghi:

```text
Design Cost
Art Cost
Animation Cost
VFX Cost
Audio Cost
Engineering Cost
QA Cost
Localization Cost
Maintenance Cost
```

Mục tiêu:
> nhìn thấy cost trước khi commit content.

---

# XXVIII. CONTENT ROI

Content priority không chỉ dựa trên:
> “nghe có hay không”.

Đánh giá:

### Player Value
Có tạo experience mới không?

### Reuse
Có dùng lại system không?

### Production Cost
Tốn bao nhiêu asset/code/test?

### Longevity
Có dùng được trong nhiều mission/event không?

### Monetization Fit
Nếu cosmetic/event, có tạo giá trị mà không phá gameplay không?

---

# XXIX. QUALITY GATES

### Gate 1 — Design
Purpose + role + counter rõ.

### Gate 2 — Prototype
Mechanic hoạt động.

### Gate 3 — Playtest
Player hiểu và có decision.

### Gate 4 — Balance
Difficulty/economy hợp lý.

### Gate 5 — Visual
Style/readability pass.

### Gate 6 — QA
Không blocker.

### Gate 7 — Release
Localization/data/asset/telemetry đầy đủ.

---

# XXX. RELEASE CHECKLIST

## Mission
- [ ] Mission ID
- [ ] Theme
- [ ] Lesson
- [ ] Fixed Roster
- [ ] Wave
- [ ] Objective
- [ ] Hazard
- [ ] Reward
- [ ] Difficulty
- [ ] Playtest
- [ ] Art
- [ ] VFX/SFX
- [ ] Localization
- [QA
- [ ] Telemetry

## Enemy
- [ ] Role
- [ ] Counter
- [ ] Telegraph
- [ ] Silhouette
- [ ] Animation
- [ ] VFX/SFX
- [ ] Balance

## Tower
- [ ] Base
- [ ] Barrel
- [ ] Module compatibility
- [ ] Counterplay
- [ ] Balance
- [ ] Visual
- [ ] VFX/SFX

## Boss
- [ ] Phases
- [ ] Counter
- [ ] Telegraph
- [ ] Arena interaction
- [ ] Recovery
- [ ] Reward
- [ ] QA

## Biome
- [ ] Visual identity
- [ ] Gameplay identity
- [ ] Terrain
- [ ] Props
- [ ] Hazard
- [ ] Enemy fit
- [ ] Lighting/audio

---

# XXXI. CONTENT VERSIONING

Mỗi released asset có:

```text
contentId
contentVersion
schemaVersion
```

Balance patch không đổi contentId.

Historical completion không bị mất chỉ vì content definition được chỉnh sửa.

---

# XXXII. CONTENT CHANGE POLICY

### Safe change
Không thay đổi player-facing strategy đáng kể.

Ví dụ:
- typo fix;
- visual polish;
- minor VFX timing.

### Balance change
Có thể thay đổi:
- damage;
- cost;
- timing;
- threat.

Phải có balance log.

### Behavioral change
Có thể thay đổi:
- AI;
- targeting;
- mechanics.

Phải playtest lại mission liên quan.

### Breaking change
Làm thay đổi save/progression/content compatibility.

Phải có migration/rollout plan.

---

# XXXIII. CONTENT SCALE ROADMAP

## Phase A
Vertical Slice:
- 5 missions.

## Phase B
Core Production:
- 20–40 missions;
- 3–4 biomes;
- full core roster.

## Phase C
Alpha:
- 50–100 missions;
- progression depth;
- daily/weekly.

## Phase D
Launch:
- launch campaign;
- multiple biome families;
- boss progression;
- seasonal foundation.

## Phase E
Live Expansion:
- new biome;
- new enemy;
- new boss;
- new tower/module;
- events.

---

# XXXIV. CONTENT TOOL REQUIREMENTS

Content authoring tool về sau nên hỗ trợ:

- Mission builder;
- Wave builder;
- Roster selector;
- Objective editor;
- Reward editor;
- Difficulty preview;
- Timeline preview;
- Validation;
- Localization preview;
- Dependency viewer;
- Playtest seed;
- Version history.

Mục tiêu:
> Content designer có thể tạo mission mà không cần sửa gameplay code.

---

# XXXV. FINAL CONTENT RULES

1. Mỗi content phải có purpose.
2. Mỗi enemy phải có counter.
3. Mỗi Tower phải có identity.
4. Mỗi mission phải có lesson.
5. Mỗi biome phải có visual + gameplay identity.
6. Mỗi boss phải có phase + telegraph + response.
7. Mỗi event phải có unique modifier/reward/value.
8. Không dùng power creep làm lý do duy nhất để tạo content.
9. Không tăng complexity vô hạn.
10. Content mới phải tái sử dụng system hiện có khi có thể.
11. Fixed Roster của mission là contract.
12. Mission target 5–15 phút là contract.
13. Outpost progression không reset theo season thông thường.
14. Visual identity phải nhất quán.
15. Chỉ scale content sau khi quality gate pass.

---

# XXXVI. CONTENT BIBLE NORTH STAR

> **Không sản xuất nhiều hơn. Sản xuất tốt hơn, khác nhau hơn, và tái sử dụng thông minh hơn.**

Last Outpost nên tạo cảm giác:

> “Mình luôn đang học một thứ mới, nhưng không bao giờ cảm thấy game đổi luật vô lý.”

