# LAST OUTPOST — PRODUCTION PIPELINE & DEVELOPMENT ROADMAP v1.0

## 0. MỤC TIÊU

Tài liệu này biến toàn bộ Game Design, System Design, Content, Progression, Economy, UX, AI, Save/Data và Technical Architecture thành một kế hoạch sản xuất thực tế.

Mục tiêu:
- Không xây toàn bộ game cùng lúc.
- Khóa core gameplay trước khi mở rộng content.
- Có prototype chơi được càng sớm càng tốt.
- Có Vertical Slice để chứng minh chất lượng.
- Có Alpha để hoàn thiện hệ thống cốt lõi.
- Có Beta để test quy mô lớn, economy và retention.
- Launch với scope kiểm soát được.
- Sau launch mở rộng qua season/event/content pack.

Nguyên tắc:

> **Prove → Stabilize → Scale → Polish → Launch.**

Không được scale content khi core gameplay chưa chứng minh được.

---

# I. CÁC MỐC PHÁT TRIỂN

```text
Concept / GDD
      ↓
Prototype
      ↓
Core Combat Prototype
      ↓
Vertical Slice
      ↓
Pre-Alpha
      ↓
Alpha
      ↓
Beta / Soft Launch
      ↓
Launch
      ↓
Live Operations
```

---

# II. MỐC 0 — PRE-PRODUCTION FOUNDATION

### Mục tiêu

Tạo nền để team có thể bắt đầu code/content mà không phải liên tục đổi kiến trúc.

### Deliverables

- GDD baseline.
- System & Gameplay Design.
- Core Combat Design.
- Content Specification.
- Progression & Outpost.
- Economy.
- UX Flow.
- UI/UX Specification.
- AI & Simulation.
- Save/Data.
- Technical Architecture.
- Repository structure.
- Coding conventions.
- Content naming conventions.
- Versioning rules.
- Bug/Task workflow.

### Gate

Chỉ chuyển sang prototype khi:
- Core loop đã rõ.
- Thời lượng trận 5–15 phút đã được xác định.
- Fixed roster theo mission đã xác định.
- Persistent Outpost đã được xác định.
- Kiến trúc runtime cơ bản đã được khóa.

---

# III. MỐC 1 — PROTOTYPE

## Mục tiêu

Chứng minh:

> **BUILD → PREPARE → SURVIVE → ADAPT**

thực sự vui trước khi làm art lớn.

### Chỉ làm

#### Battlefield
- 1 biome.
- 1 map.
- 1 lane chính + nhánh đơn giản.
- Camera 2.5D cơ bản.

#### Towers
Khoảng 3 tower prototype:
- Crowd Control.
- Single Target.
- Anti Armor.

Base + Barrel + Module ở mức tối thiểu.

#### Enemy
Khoảng 4 species:
- Swarm.
- Ranged/Special.
- Armored.
- Disruptor hoặc Burrower.

#### Combat
- targeting.
- damage.
- status cơ bản.
- projectile.
- death.
- wave.

#### Resources
Chỉ prototype:
- Ammo.
- Power.
- Water.

#### Commander
1 Commander.
1–2 active abilities.

### Không làm

- Guild.
- PvP.
- Shop.
- Season.
- Full Outpost.
- 100+ missions.
- Complex monetization.
- Full cosmetic system.

### Prototype Success Criteria

- Một trận có thể chơi liên tục.
- Người chơi hiểu tại sao thắng/thua.
- Tower khác nhau tạo quyết định.
- Enemy counter tạo thay đổi chiến thuật.
- Có ít nhất một khoảnh khắc “thoát hiểm nhờ quyết định đúng”.
- Combat không phụ thuộc vào UI giả lập.

---

# IV. MỐC 2 — CORE COMBAT PROTOTYPE

## Mục tiêu

Khóa trái tim của game.

### Hoàn thiện

- Tower targeting.
- Damage pipeline.
- Status.
- Armor.
- Heat.
- Ammo.
- Power.
- Water.
- Logistics cơ bản.
- Wave Director.
- Fixed Roster.
- Objectives.
- Boss phase.
- Corpse interaction.
- Hazard interaction.

### Roster

Tất cả 12 species có thể tồn tại trong engine, nhưng prototype gameplay chỉ cần subset.

### Mission Duration

Target:
- 5–15 phút.

Test:
- Early mission: 5–8 phút.
- Mid mission: 7–12 phút.
- Late/Boss: 9–15 phút.

### Gate

Không bước sang Vertical Slice nếu:
- Combat còn phụ thuộc bug để vui.
- Wave chỉ là tăng HP.
- Tower khác nhau nhưng chơi giống nhau.
- Boss chỉ là HP sponge.

---

# V. MỐC 3 — VERTICAL SLICE

## Mục tiêu

Làm một đoạn game gần chất lượng thật.

Vertical Slice đại diện cho trải nghiệm mong muốn khi phát hành.

### Scope đề xuất

**1 biome hoàn chỉnh**

**5–8 missions**

**6–8 enemy species**

**1 Boss**

**6–8 tower/build combinations**

**1 Commander**

**1 Outpost nhỏ**

**1 chapter**

### Có đủ

- Final-ish visual direction.
- Final-ish UI style.
- VFX/SFX cơ bản chất lượng cao.
- Mission Briefing.
- Loadout.
- Combat HUD.
- Result.
- Progression.
- Outpost.
- Basic Economy.
- Save/Load.
- Telemetry.

### Vertical Slice Test

Một người mới có thể:

```text
Mở game
→ Tutorial
→ vào Outpost
→ chọn mission
→ chọn loadout
→ chơi 5–15 phút
→ thắng/thua
→ nhận reward
→ nâng cấp
→ quay lại chơi
```

mà không cần developer giải thích bằng lời.

### Gate

Vertical Slice phải chứng minh:
- game có identity;
- UI đọc được;
- combat có chiều sâu;
- progression có cảm giác tiến bộ;
- art/VFX không phá readability.

---

# VI. MỐC 4 — PRE-ALPHA

## Mục tiêu

Hoàn thiện toàn bộ hệ thống core trước khi scale content.

### Systems

- Full Tower framework.
- Full Enemy framework.
- Commander framework.
- Wave Director.
- Hazard.
- Logistics.
- Outpost.
- Tech Tree.
- Mastery.
- Blueprint.
- Expedition.
- Economy.
- Save/Cloud.
- Analytics.
- Tutorial.
- Mission framework.

### Content

- 3–4 biome.
- 20–40 missions.
- toàn bộ enemy roster.
- nhiều boss encounters.

### Technical

- pooling.
- AI LOD.
- spatial query.
- pathfinding.
- content validation.
- crash reporting.
- performance profiling.

### Gate

- No blocker bugs trong core loop.
- Save/restore ổn định.
- Mission creation chủ yếu bằng data.
- Performance không phụ thuộc brute-force.

---

# VII. MỐC 5 — ALPHA

## Mục tiêu

Game feature-complete cho core PvE.

### Content

Có thể hướng tới:
- 5–6 biome.
- 50–100 campaign missions.
- 12 enemy species.
- nhiều boss.
- full Outpost progression path.

### Systems

- Full progression.
- Full Tech Tree.
- Mastery.
- Blueprint.
- Expedition.
- Daily/Weekly Challenge.
- World Event framework.
- Cosmetic framework.

### Monetization

Bắt đầu test:
- Plus.
- Pro.
- Cosmetic.
- Rewarded Ads.
- Season Pass prototype.

Không đặt monetization làm điều kiện để hoàn thành campaign.

### Balance

Theo dõi:
- win rate.
- clear time.
- death reason.
- tower pick rate.
- enemy threat.
- resource scarcity.
- reward/cost ratio.

---

# VIII. MỐC 6 — BETA / SOFT LAUNCH

## Mục tiêu

Kiểm chứng game với người chơi thật.

### Scope

- Full core PvE.
- Campaign content launch scope.
- Outpost.
- Economy.
- Plus/Pro.
- Cloud Save.
- Analytics.
- Support/Recovery.

### Social

Có thể bật:
- Guild.
- Guild missions.
- simplified PvP/raid.

Không mở territory warfare phức tạp nếu core PvE vẫn chưa ổn định.

### Test tập trung

#### Stability
- crash rate.
- device compatibility.
- save corruption.
- network recovery.

#### Gameplay
- mission completion.
- retry rate.
- abandonment.
- difficulty spikes.

#### Economy
- resource inflation.
- bottlenecks.
- shop conversion.
- Plus/Pro usage.

#### UX
- tutorial completion.
- time to first battle.
- menu confusion.
- failed actions.

---

# IX. MỐC 7 — LAUNCH

## Launch Scope

Không cần toàn bộ ý tưởng trong GDD.

Nên launch với:

### Core
- 1 core combat system hoàn chỉnh.
- 12 enemy roster.
- Tower modular.
- Commander.
- Logistics.
- Hazards.
- Bosses.

### Campaign
- số lượng mission đủ lớn để tạo cảm giác game dài hạn.
- fixed roster per mission.
- nhiều biome.
- chapter structure.

### Persistent
- Outpost.
- Tech Tree.
- Mastery.
- Blueprint.
- Expedition.

### Economy
- core currencies.
- reward loop.
- Shop.
- Plus.
- Pro.

### Live
- Daily/Weekly.
- Seasonal event framework.
- Cosmetics.

### Social
- Guild nhẹ.
- một PvP mode nếu đã ổn định.

---

# X. MỐC 8 — LIVE OPERATIONS

Sau launch:

```text
Major Update
→ New Content
→ Event
→ Balance
→ Bug Fix
→ Telemetry Review
→ Next Update
```

## Content cadence đề xuất

### Hàng ngày
- daily challenge;
- rotating mission/objective;
- rewarded ad opportunities.

### Hàng tuần
- weekly challenge;
- leaderboard/challenge;
- shop rotation.

### Mỗi Season
- new cosmetic set;
- new event;
- new modifiers;
- story/content chapter nếu đủ nguồn lực.

### Major Expansion
- new biome;
- new enemy;
- new boss;
- new tower/module;
- new systems nếu thật sự cần.

---

# XI. FEATURE PRIORITY MATRIX

## P0 — CORE / MUST HAVE

- Battle runtime.
- Tower.
- Enemy.
- Wave.
- Mission.
- Fixed roster.
- Objectives.
- Commander.
- Outpost core.
- Progression.
- Economy.
- Save.
- Performance.
- Tutorial.
- UX core.

## P1 — IMPORTANT

- Mastery.
- Blueprint.
- Expedition.
- Challenges.
- Hazards mở rộng.
- Cosmetics.
- Plus/Pro.
- Analytics nâng cao.
- Cloud recovery.

## P2 — POST-LAUNCH / SCALE

- Guild.
- simplified PvP.
- seasonal systems nâng cao.
- live events nâng cao.
- content packs.

## P3 — OPTIONAL / ONLY IF VALIDATED

- territorial warfare.
- complex diplomacy.
- large-scale guild wars.
- advanced world simulation.
- huge social meta systems.

Nguyên tắc:
> P3 chỉ được làm khi P0/P1 hoạt động tốt và có lý do thực tế.

---

# XII. TEAM / WORKSTREAM STRUCTURE

Có thể chia sản xuất thành các workstream:

### Gameplay Engineering
- combat;
- tower;
- enemy;
- wave;
- commander.

### Systems Engineering
- progression;
- outpost;
- economy;
- save;
- backend.

### AI / Simulation
- pathfinding;
- AI;
- swarm;
- performance.

### Client / UI
- screens;
- HUD;
- interaction;
- accessibility.

### Art / VFX / Audio
- environment;
- tower art;
- enemy art;
- effects;
- audio.

### Content Design
- missions;
- roster;
- waves;
- objectives;
- rewards;
- balance.

### QA / Playtest
- functional;
- balance;
- performance;
- device coverage;
- regression.

---

# XIII. CONTENT PRODUCTION PIPELINE

Mission creation:

```text
Concept
→ Mission Brief
→ Map Layout
→ Fixed Roster
→ Wave Plan
→ Objective
→ Hazard
→ Reward
→ Difficulty Review
→ Playtest
→ Telemetry
→ Adjust
→ Content Validation
→ Release
```

Không publish mission trực tiếp từ editor mà chưa qua validation.

---

# XIV. ART / VFX PIPELINE

```text
Concept
→ Blockout
→ Readability Check
→ Final Asset
→ Animation
→ VFX Hook
→ Performance Test
→ Device Test
→ Integration
```

Ưu tiên:
1. Gameplay readability.
2. silhouette.
3. feedback.
4. polish.

Không hy sinh gameplay clarity để lấy hiệu ứng đẹp.

---

# XV. QA PIPELINE

Mỗi build:

```text
Build
→ Smoke
→ Core Regression
→ Mission Test
→ Economy Test
→ Save Test
→ Performance Test
→ Device Test
→ Release Candidate
```

### Critical regression

- Start battle.
- Build tower.
- Wave spawn.
- Enemy damage.
- Commander skill.
- Mission result.
- Reward.
- Save.
- Reload.
- Progression.
- Outpost upgrade.

---

# XVI. PLAYTEST FRAMEWORK

Playtest không chỉ hỏi:
> “Có vui không?”

Phải đo:

### Combat
- clear time;
- death reason;
- tower usage;
- unused resources;
- emergency usage.

### UX
- time to first meaningful action;
- abandoned screens;
- failed inputs;
- tutorial drop-off.

### Progression
- reward perception;
- upgrade frequency;
- unlock pacing;
- session length.

### Economy
- currency earn/spend;
- resource overflow;
- bottleneck;
- purchase behavior.

---

# XVII. QUALITY GATES

## Prototype Gate
> Core loop fun và hiểu được.

## Core Combat Gate
> Tower / Enemy / Wave tạo chiến thuật.

## Vertical Slice Gate
> Trải nghiệm đại diện cho chất lượng muốn ship.

## Pre-Alpha Gate
> Core systems ổn định.

## Alpha Gate
> Feature-complete PvE.

## Beta Gate
> Stable + balanced + measurable.

## Launch Gate
> Player can complete a satisfying long-term progression loop.

---

# XVIII. RELEASE RISK CONTROL

### Rủi ro 1 — Scope Creep

Giải pháp:
- P0/P1/P2/P3.
- Feature freeze trước launch.
- Không thêm system mới nếu chưa có data chứng minh cần.

### Rủi ro 2 — Content quá chậm

Giải pháp:
- data-driven missions;
- reusable templates;
- fixed roster;
- authoring tools.

### Rủi ro 3 — Performance

Giải pháp:
- pooling;
- AI LOD;
- spatial query;
- profiling từ prototype.

### Rủi ro 4 — Economy phá progression

Giải pháp:
- economy simulator;
- telemetry;
- controlled sinks;
- no uncontrolled rewards.

### Rủi ro 5 — Monetization phá trust

Giải pháp:
- Free complete core.
- Plus/Pro lợi thế nhẹ.
- không bán thẳng thắng lợi.
- PvP normalize các stat nhạy cảm.

### Rủi ro 6 — Save mất progression

Giải pháp:
- transaction;
- snapshot;
- revision;
- recovery;
- cloud sync.

---

# XIX. DEFINITION OF DONE — PRODUCTION

Một milestone chỉ được coi là hoàn tất khi:

- [ ] Feature hoạt động.
- [ ] Có automated test phù hợp.
- [ ] Không có blocker bug.
- [ ] Telemetry cần thiết đã có.
- [ ] Save/load hoạt động.
- [ ] Performance đạt budget.
- [ ] Content validation pass.
- [ ] UX feedback đã kiểm tra.
- [ ] Device test pass trong scope.
- [ ] Documentation cập nhật.

---

# XX. CORE ROADMAP

```text
PRE-PRODUCTION
   ↓
PROTOTYPE
   ↓
CORE COMBAT
   ↓
VERTICAL SLICE
   ↓
PRE-ALPHA
   ↓
ALPHA
   ↓
BETA / SOFT LAUNCH
   ↓
LAUNCH
   ↓
LIVE OPS
```

Điều quan trọng:

> **Không làm 500 màn để chứng minh game hay.**
>
> **Làm một bộ nhỏ nhưng hoàn chỉnh để chứng minh game hay, sau đó mới scale lên hàng trăm màn.**

---

# XXI. FINAL PRODUCTION PRINCIPLES

1. Core loop trước content.
2. Content trước expansion.
3. Prototype trước polish.
4. Vertical Slice trước scale.
5. Performance từ đầu, không chữa cháy cuối dự án.
6. Save safety là nền tảng.
7. Economy phải đo được.
8. Monetization không được phá gameplay.
9. PvP/social chỉ mở rộng sau khi PvE core ổn định.
10. Mọi system mới phải giải quyết một nhu cầu gameplay hoặc player experience rõ ràng.

> **PRODUCTION GOAL: Tạo một phiên bản nhỏ nhưng hoàn chỉnh, chứng minh chất lượng và khả năng mở rộng, rồi phát triển Last Outpost thành một game dài hạn thay vì cố xây toàn bộ ngay từ đầu.**
