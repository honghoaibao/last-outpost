# LAST OUTPOST — ASSET PRODUCTION SPECIFICATION v1.0

> **Loại tài liệu:** Production / Reference / Specification  
> **Version:** v1.0  
> **Status:** PRODUCTION BASELINE  
> **Scope:** Art, Animation, VFX, UI Asset, Audio handoff metadata  
> **Engine-specific implementation:** Không khóa  
> **Core gameplay changes:** Không có

---

## 0. MỤC ĐÍCH

Tài liệu này chuyển Visual Direction của **LAST OUTPOST** thành một bộ quy chuẩn sản xuất asset có thể dùng xuyên suốt production.

Visual Reference trả lời:

> **“Asset nhìn như thế nào?”**

Asset Production Specification trả lời:

> **“Asset phải được sản xuất, kiểm tra, version-control và handoff như thế nào?”**

Tài liệu áp dụng cho:

- Character
- Commander
- Enemy
- Tower
- Base
- Barrel
- Module
- Boss
- Building
- Outpost
- Environment
- Prop
- UI Icon
- UI Panel
- VFX
- Animation
- Portrait
- Cosmetic

Mục tiêu production:

1. Asset mới phải giữ cùng visual language.
2. Asset gameplay phải đọc được rõ trên mobile.
3. Asset phải có đủ state để gameplay/UI/VFX/audio phối hợp.
4. Artist, animator, VFX, UI/UX, audio, QA và Claude có cùng một cách đọc asset.
5. Có thể mở rộng content mà không tạo style drift hoặc asset chaos.

---

# I. SOURCE OF TRUTH & AUTHORITY

## 1.1. Thứ tự ưu tiên

Khi sản xuất asset, ưu tiên tài liệu theo thứ tự:

1. **LATEST LOCKED DESIGN / CURRENT DECISIONS**
2. **Last_Outpost_Toi_Uu_v1.2**
3. **System / Core Combat / Content / Production Content Bible**
4. **Visual Identity & Art Direction**
5. **Visual Reference Pack**
6. **Asset Production Specification này**
7. Historical / legacy documents

Nếu có conflict:

- Không tự ý sửa gameplay.
- Không tự ý đổi terminology.
- Ghi conflict trong asset review.
- Chỉ thay production treatment nếu việc đó không làm thay đổi design.

## 1.2. Asset Production Specification này không được phép override

Tài liệu này **không được dùng để tự ý thay đổi**:

- Tower / Barrel / Module design.
- Enemy roster.
- Fixed Enemy Roster per Mission.
- Mission duration.
- Outpost progression.
- Commander identity.
- Economy.
- Monetization.
- Core loop BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN.

---

# II. NON-NEGOTIABLE VISUAL FOUNDATION

## 2.1. Visual Identity

> **Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy**

Các từ khóa bắt buộc:

`stylized` · `2.5D` · `isometric` · `cartoon` · `chunky` · `expressive` · `friendly apocalypse` · `scrap-tech` · `tactical readability`

Các hướng cần tránh:

- photorealistic;
- gritty military simulator;
- grimdark;
- horror-first;
- fantasy RPG;
- full chibi;
- generic mobile strategy/card UI;
- visual overload;
- excessive glassmorphism.

## 2.2. Visual hierarchy

Mọi asset gameplay được đánh giá theo thứ tự:

> **Silhouette → Shape → Color → Detail**

Detail không được dùng để cứu một silhouette yếu.

## 2.3. Tactical clarity

Gameplay-critical asset phải đọc được trong battle khi:

- camera ở mức gameplay thông thường;
- nhiều enemy cùng lúc;
- nhiều projectile/VFX;
- weather/hazard hoạt động;
- mobile screen nhỏ.

Asset không được trở nên đẹp hơn nhưng khó nhận diện hơn.

---

# III. PRODUCTION ASSET PRINCIPLES

## 3.1. Readability before decoration

Gameplay asset ưu tiên chức năng nhận diện trước decoration.

## 3.2. Strong silhouette

Mỗi asset quan trọng phải có một hình khối nhận diện được khi giảm chi tiết.

## 3.3. Controlled asymmetry

Asymmetry được khuyến khích để tạo personality nhưng phải có chủ đích.

Không tạo asymmetry chỉ để làm asset “lạ”.

## 3.4. Exaggerated function

Chức năng cần được phóng đại vừa đủ:

- Tower điện → phần phát điện/pulse dễ nhận biết.
- EMP Stalker → bộ phận phát xung dễ đọc.
- Acid Bloater → acid sac rõ.
- Harpy → wing silhouette rõ.
- Engineer → tool/backpack rõ.

## 3.5. Reuse without sameness

Reuse được phép ở:

- material family;
- hardware language;
- mounting language;
- color treatment;
- animation grammar;
- VFX grammar.

Không được reuse tới mức:

> **“cùng model, đổi màu = asset mới.”**

---

# IV. MASTER SCALE SYSTEM

> **Lưu ý:** Tài liệu hiện tại không khóa một đơn vị engine/pixel tuyệt đối. Vì vậy spec dùng **relative production scale** thay vì pixel hoặc world-unit cụ thể.

## 4.1. Reference Unit

**Character Height = 1.0 Reference Height (RH)**

RH dùng làm điểm chuẩn để so sánh:

- Commander;
- ground enemy;
- props;
- Tower;
- building;
- VFX footprint;
- portrait crop.

Đây là **production reference**, không phải gameplay balance stat.

## 4.2. Relative scale families

| Asset Family | Relative Guidance | Mục đích |
|---|---:|---|
| Small Prop | ~0.1–0.5 RH | đọc được nhưng không tranh spotlight |
| Character / Commander | 1.0 RH | baseline |
| Small Ground Enemy | ~0.4–0.9 RH | swarm/readability |
| Heavy Enemy | ~1.2–2.5 RH | threat hierarchy |
| Boss | ≥ 3 RH cảm nhận | boss-grade silhouette |
| Tower | ~1–2.5 RH tổng thể | đủ mạnh để đọc trong battle |
| Small Building | ~2–4 RH | gameplay structure |
| Major Building | ~4–8 RH | Outpost landmark |

**Đây là production baseline proposal mới**, không thay đổi combat stat hay collision.

Lý do: tài liệu hiện tại đã khóa visual hierarchy nhưng chưa có scale contract đủ cụ thể. Dải tương đối giúp artist giữ tỷ lệ mà không khóa engine.

### Cost / Risk

- Cost: thấp.
- Risk: thấp nếu chỉ dùng làm art reference.
- Risk tăng nếu biến RH thành gameplay collision hoặc stat.

**DoD:** RH chỉ phục vụ visual consistency, không được diễn giải thành balance value.

---

# V. PERSPECTIVE & PRESENTATION CONTRACT

## 5.1. Camera foundation

- 2.5D isometric.
- Khoảng nhìn ~45° theo Visual Direction.
- Ground plane rõ.
- Verticality thể hiện qua overlap, contact shadow, highlight và animation.

## 5.2. Asset presentation angle

Mỗi asset production phải có tối thiểu:

1. **Gameplay view** — đúng góc battle.
2. **Reference/inspection view** — đủ rõ để artist kiểm tra shape.
3. **State sheet** — nếu asset có state thay đổi.

## 5.3. Camera-read test

Asset pass khi:

- silhouette vẫn nhận ra ở gameplay size;
- chức năng chính không bị environment che;
- không cần zoom sát mới hiểu đó là gì;
- không phụ thuộc texture nhỏ.

---

# VI. ASSET LIFECYCLE

Mọi asset production đi qua:

```text
Brief
→ Concept
→ Shape Review
→ Approved Design
→ Production Asset
→ Animation / VFX / Audio Integration
→ Gameplay Readability Test
→ QA Review
→ Approved
→ Live / Production
```

## 6.1. Asset status

| Status | Ý nghĩa |
|---|---|
| IDEA | ý tưởng chưa production |
| BRIEF | đã có yêu cầu |
| CONCEPT | đang concept |
| ART APPROVED | design art đã được duyệt |
| IN PRODUCTION | đang dựng |
| ANIMATING | animation đang làm |
| VFX PASS | VFX đang hoàn thiện |
| AUDIO HANDOFF | đủ metadata để audio làm |
| PLAYTEST | đang kiểm tra trong game |
| QA | kiểm tra cuối |
| APPROVED | production-ready |
| LIVE | đang sử dụng |
| LEGACY | giữ để tương thích/lịch sử |
| RETIRED | không còn dùng |

---

# VII. ASSET IDENTITY & NAMING

## 7.1. Naming baseline

> Đây là **production convention đề xuất mới** nhằm tránh thất lạc asset khi số lượng content tăng.

Mẫu:

```text
LO_<CATEGORY>_<ID>_<SHORTNAME>_v###
```

Ví dụ:

```text
LO_CMD_ENG_ENGINEER_v001
LO_ENM_CRAWLER_v001
LO_TWR_SENTRYCANNON_v001
LO_BAR_GLUE_v001
LO_MOD_ARMORBREAK_v001
LO_BOS_COLOSSUS_v001
LO_ENV_RUINEDCITY_ROAD_v001
LO_UI_ICON_AMMO_v001
LO_VFX_EMP_PULSE_v001
```

## 7.2. Category codes

| Code | Category |
|---|---|
| CMD | Commander |
| ENM | Enemy |
| TWR | Tower |
| BAR | Barrel |
| MOD | Module |
| BOS | Boss |
| BLD | Building |
| OUT | Outpost |
| ENV | Environment |
| PRP | Prop |
| ICO | UI Icon |
| UIP | UI Panel |
| VFX | Visual Effect |
| ANM | Animation Set |
| POR | Portrait |
| COS | Cosmetic |
| FXM | Shared Effect Material/Family |

## 7.3. Versioning rules

- `v001` = first production revision.
- Increase version khi asset design hoặc presentation thay đổi có chủ đích.
- Không đổi version chỉ vì export lại file không thay đổi nội dung.
- Nếu thay đổi ảnh hưởng gameplay readability, phải ghi changelog.
- Nếu chỉ sửa typo metadata, có thể cập nhật metadata revision riêng.

---

# VIII. MASTER ASSET CARD

Mọi asset gameplay quan trọng cần một Asset Card.

```text
ASSET ID:
DISPLAY NAME:
CATEGORY:
STATUS:
OWNER ROLE:
DESIGN SOURCE:
VISUAL SOURCE:
GAMEPLAY ROLE:
PRIMARY SILHOUETTE:
RELATIVE SCALE:
PERSPECTIVE:
COLORS / SEMANTIC COLOR:
MATERIAL FAMILY:
STATES:
ANIMATION SET:
VFX SET:
SFX SET:
PORTRAIT REQUIRED:
UI ICON REQUIRED:
COSMETIC VARIANTS:
MOBILE READABILITY:
PRIORITY:
DEPENDENCIES:
HANDOFF STATUS:
KNOWN ISSUES:
CHANGELOG:
```

---

# IX. CHARACTER PRODUCTION SPECIFICATION

## 9.1. Character scope

Character bao gồm:

- Commander;
- civilian/resident;
- special NPC nếu sau này cần;
- temporary gameplay character nếu được design chấp thuận.

## 9.2. Character design rules

Bắt buộc:

- stylized anatomy;
- expressive face;
- clear silhouette;
- practical post-apocalyptic clothing;
- role-readable equipment;
- không full chibi;
- không photoreal face.

## 9.3. Required views

Tối thiểu:

- gameplay 3/4 or battle view;
- front reference;
- back reference nếu equipment quan trọng;
- expression sheet cho Commander;
- equipment callout.

Không yêu cầu turntable đầy đủ nếu không cần cho production.

## 9.4. Character scale

Commander là **1.0 RH** baseline.

Civilian nên dùng cùng family proportion để Outpost không bị lệch thế giới.

Enemy scale có thể lệch mạnh hơn để phục vụ threat readability.

## 9.5. Character states

Tối thiểu đối với Commander:

- Idle;
- Walk/Run;
- Combat Ready;
- Basic Attack;
- Skill;
- Hit;
- Repair/Interact;
- Down/Defeat;
- Victory/Success.

## 9.6. Equipment readability

Trang bị gắn với role phải đọc được mà không cần text.

Ví dụ:

**Engineer**
- backpack;
- wrench/tool;
- cooling equipment.

**Guardian**
- armor;
- firearm;
- barricade tool.

**Biologist**
- field kit;
- sample containers;
- protective equipment.

## 9.7. Character DoD

- [ ] silhouette rõ ở gameplay size;
- [ ] role nhận ra nhanh;
- [ ] outfit đúng visual family;
- [ ] animation không phá proportions;
- [ ] combat pose rõ;
- [ ] face/expression đúng tone;
- [ ] không thành full chibi;
- [ ] không cần texture detail để nhận diện.

---

# X. COMMANDER PRODUCTION SPECIFICATION

Commander có yêu cầu cao hơn character thường vì là gameplay identity + personality.

## 10.1. Commander must-read

Trong vài giây phải phân biệt được:

- Engineer;
- Guardian;
- Biologist.

## 10.2. Commander signature

Mỗi Commander cần ít nhất 3 signature:

1. Silhouette signature.
2. Equipment signature.
3. Animation/behavior signature.

## 10.3. Skill presentation

Skill phải có:

- anticipation;
- activation cue;
- action;
- impact;
- recovery;
- VFX;
- SFX cue.

Không tạo skill effect quá rộng nếu làm mất battlefield readability.

## 10.4. Commander portrait

Portrait phải giữ:

- facial identity;
- color identity;
- role cue;
- readable crop.

Portrait không nên chỉ là screenshot gameplay.

## 10.5. Commander cosmetic

Cosmetic có thể thay đổi:

- outfit;
- accessory;
- color treatment;
- themed equipment;
- VFX presentation nếu được phép.

Không được làm mất class readability.

---

# XI. ENEMY PRODUCTION SPECIFICATION

## 11.1. Enemy identity contract

Mỗi species phải khác nhau ở tối thiểu:

- silhouette;
- locomotion;
- attack posture;
- threat cue;
- material/biological language.

## 11.2. Roster integrity

12 species là roster đã được design.

Asset production không được tự tạo species mới để giải quyết một mission nếu không có design approval.

## 11.3. Enemy states

Tùy species, bộ state tối thiểu gồm:

- Idle/Move;
- Attack preparation;
- Attack;
- Hit;
- Special ability telegraph;
- Disabled/Stunned nếu applicable;
- Death/Despawn.

## 11.4. Threat telegraph

Mọi enemy gây threat đặc biệt phải có visual cue đủ rõ.

Ví dụ:

| Enemy | Required Readability Cue |
|---|---|
| Crawler | swarm movement |
| Acid Bloater | acid sac / toxic state |
| Iron-Hide Behemoth | armor / weight |
| EMP Stalker | pulse preparation |
| Tunnel Burrower | terrain disturbance |
| Corpse Weaver | support behavior |
| Scavenger Harpy | aerial shadow / dive |
| Spore Spreader | spore release |
| Riot Mech Mutate | shield/front defense |
| Siren Screamer | scream preparation |
| Colossus Titan | boss-scale telegraph |
| Hive Mother | spawn/hive cue |

## 11.5. Enemy death

Không gore.

Được phép:

- stylized burst;
- dissolve;
- smoke;
- debris;
- parts release;
- acid splash phù hợp species.

---

# XII. TOWER PRODUCTION SPECIFICATION

## 12.1. Tower structure

Mọi Tower dùng:

> **BASE + BARREL + MODULE**

Tower phải duy trì cảm giác modular mechanical scrap-tech.

## 12.2. Base requirements

Base cần truyền tải:

- mounting logic;
- stability;
- mechanical identity;
- cooling/power/utility cues khi cần.

## 12.3. Barrel requirements

Barrel cần giúp nhận ra:

- damage family;
- projectile family;
- range role;
- attack direction.

## 12.4. Module requirements

Module cần có visual distinction đủ để người chơi nhận ra build khác.

Module tag hiện tại:

- Damage;
- Control;
- Utility;
- Logistics.

Không dùng visual difference chỉ bằng màu nếu module có gameplay role khác nhau.

## 12.5. Tower states

Tối thiểu:

- Preview;
- Built;
- Idle/Ready;
- Targeting;
- Firing;
- Cooldown;
- Resource shortage nếu applicable;
- Disabled nếu applicable;
- Damaged;
- Upgrade/Modification;
- Destroyed/Removed nếu gameplay có.

## 12.6. Tower animation

Core grammar:

> anticipation → fire/activation → impact → recovery

Vũ khí nặng có recoil rõ nhưng không làm camera/UI khó đọc.

## 12.7. Tower readability test

Người chơi phải có khả năng nhận ra:

- Tower đang làm gì;
- có đang hoạt động không;
- đang thiếu gì;
- hướng tấn công;
- trạng thái nguy hiểm.

---

# XIII. BASE / BUILDING PRODUCTION SPECIFICATION

## 13.1. Base / structure philosophy

Player structures phải:

- chunky;
- practical;
- modular;
- scrap-tech;
- có dấu hiệu repair/rebuild;
- không fantasy castle.

## 13.2. Structural tiers

Buildings nên có visual progression tương ứng progression Outpost.

### Stage 1
Temporary / improvised.

### Stage 2
Fortified / protective.

### Stage 3
Industrial / productive.

### Stage 4
Advanced / integrated.

### Stage 5
Mature / large-scale bastion.

## 13.3. Building states

Tùy building:

- planned;
- under construction;
- operational;
- resource-starved;
- damaged;
- upgraded;
- max/current stage.

Không bắt buộc mỗi state phải là model riêng nếu shader/overlay/VFX đủ đọc.

---

# XIV. BARREL PRODUCTION SPECIFICATION

## 14.1. Identity

Barrel là một phần của Tower, không phải một vũ khí độc lập về mặt visual language.

## 14.2. Required design information

Mỗi Barrel phải có:

- firing direction;
- barrel type;
- mechanical action;
- muzzle identity;
- recoil expectation;
- projectile family;
- heat/energy cue nếu liên quan.

## 14.3. Animation requirements

- idle mechanical motion nếu cần;
- firing recoil;
- overheat/strain cue nếu design yêu cầu;
- recovery.

## 14.4. VFX linkage

Barrel phải map được tới:

- muzzle flash;
- projectile/trail;
- impact;
- special activation.

---

# XV. MODULE PRODUCTION SPECIFICATION

## 15.1. Module identity

Module phải biểu đạt “vai trò thêm vào Tower”.

## 15.2. Module visual grammar

Ưu tiên:

- physical attachment;
- cable/pipe/coil/container/plate/tool;
- compact silhouette;
- clear center of visual interest.

Tránh module fantasy orb trừ khi gameplay/design source yêu cầu.

## 15.3. Module states

- Available;
- Selected;
- Equipped;
- Active;
- Disabled;
- Upgraded/Refined nếu applicable.

## 15.4. Module DoD

- [ ] Nhìn ra phần module tách biệt.
- [ ] Không phá Tower silhouette.
- [ ] Vai trò có cue.
- [ ] Attachment point rõ.
- [ ] Không tạo silhouette quá lộn xộn.

---

# XVI. BOSS PRODUCTION SPECIFICATION

Boss là visual anchor của mission.

## 16.1. Boss must-read

Người chơi phải biết ngay:

> **“Đây không phải enemy bình thường.”**

## 16.2. Boss silhouette

Boss cần:

- scale lớn;
- silhouette mạnh;
- clear weak-point zones nếu design có;
- entrance identity;
- phase/behavior identity nếu có.

## 16.3. Boss states

- Entrance;
- Idle/Threat;
- Phase 1 / baseline;
- Special attack telegraph;
- Attack;
- Phase change nếu có;
- Stagger/critical nếu có;
- Defeat;
- Reward/Victory presentation.

## 16.4. Boss VFX/audio contract

Boss cần asset matrix riêng cho:

- entrance VFX;
- ability VFX;
- telegraph;
- impact;
- phase transition;
- defeat.

Audio identity phải khớp với Audio Bible.

---

# XVII. ENVIRONMENT PRODUCTION SPECIFICATION

## 17.1. Environment layers

Mỗi biome environment gồm:

```text
Landmark
→ Gameplay Terrain
→ Structural Set
→ Major Props
→ Small Props
→ Vegetation / Debris
→ Weather / Atmosphere
```

## 17.2. Biome identity

Mỗi biome phải có:

- palette;
- material family;
- major prop family;
- terrain identity;
- ambient cues;
- hazard cues khi applicable.

## 17.3. Readability hierarchy

### Tier 1
Gameplay terrain.

### Tier 2
Landmark / tactical structures.

### Tier 3
Decorative props.

Decorative props không được làm enemy/tower khó nhìn.

## 17.4. Environment variation

Variation ưu tiên:

- layout;
- silhouette;
- prop grouping;
- damage state;
- vegetation density;
- lighting context.

Không phụ thuộc recolor toàn map để tạo biome mới.

---

# XVIII. PROP PRODUCTION SPECIFICATION

## 18.1. Prop categories

### Gameplay Prop
Có tác động hoặc đọc được trong gameplay.

### Narrative Prop
Kể chuyện thế giới.

### Scale Prop
Giúp đọc kích thước môi trường.

### Decorative Prop
Tạo density / atmosphere.

## 18.2. Prop rules

Mỗi prop phải có:

- purpose;
- scale class;
- material;
- biome compatibility;
- placement rule nếu có.

## 18.3. Prop reuse

Có thể tái sử dụng prop qua biome nếu:

- context hợp lý;
- material treatment phù hợp;
- không làm biome mất identity.

---

# XIX. OUTPOST PRODUCTION SPECIFICATION

Outpost là biểu tượng progression nên có quality bar cao hơn environment thông thường.

## 19.1. Stage progression

```text
Stage 1 — Survivor Camp
Stage 2 — Fortified Outpost
Stage 3 — Industrial Outpost
Stage 4 — Advanced Fortress
Stage 5 — Last Bastion
```

## 19.2. Visual progression rules

Mỗi stage phải tăng cảm nhận:

- structural permanence;
- infrastructure;
- production capability;
- defense capability;
- civilian activity;
- technology maturity.

## 19.3. Do not fake progression

Không chỉ tăng:

- saturation;
- size;
- particle density.

Progression phải được nhìn thấy qua:

- building class;
- infrastructure;
- layout density;
- active machines;
- settlement activity.

## 19.4. Outpost hero shot

Mỗi stage cần một presentation có thể dùng cho:

- progression screen;
- showcase;
- marketing capture;
- design review.

Không thay đổi gameplay camera chỉ để làm hero art.

---

# XX. UI ICON PRODUCTION SPECIFICATION

## 20.1. Icon philosophy

> **Bold silhouette + simplified internal detail + controlled industrial treatment.**

## 20.2. Icon families

- Resource;
- Tower;
- Enemy;
- Status;
- Commander;
- Mission;
- Economy;
- Upgrade;
- Social.

## 20.3. Icon requirements

Mỗi icon cần:

- 1 primary silhouette;
- 1 clear semantic cue;
- limited internal details;
- consistent visual weight;
- readability at smallest intended size.

## 20.4. Semantic colors

Giữ thống nhất:

- Success → fresh green;
- Warning → amber;
- Danger → coral/red;
- Power → electric cyan;
- Water → blue/cyan;
- Ammo → warm yellow/orange;
- Premium → violet/radiant accent.

## 20.5. Icon DoD

- [ ] recognized without label when appropriate;
- [ ] same visual family;
- [ ] no unnecessary outline variation;
- [ ] no texture noise;
- [ ] no ambiguous silhouette;
- [ ] retains meaning in grayscale where feasible.

---

# XXI. UI PANEL PRODUCTION SPECIFICATION

## 21.1. UI surface language

- navy/slate base;
- mild depth;
- controlled transparency;
- mild industrial rounding;
- clear grouping.

Không biến mọi thành phần thành card nổi riêng biệt.

## 21.2. Panel priority

### P0
- combat HUD;
- mission briefing;
- loadout;
- result;
- critical alerts.

### P1
- Outpost management;
- progression;
- Tower/Module information.

### P2
- shop/cosmetic;
- secondary profile presentation.

## 21.3. Panel state

- default;
- focused;
- selected;
- disabled;
- warning;
- critical;
- success;
- loading nếu cần.

## 21.4. UI animation

Baseline:

- open → subtle scale/fade/slide;
- close → fast fade/slide;
- reward → pop → shine → settle;
- upgrade → highlight → pulse → completed state.

Không bounce quá cute.

---

# XXII. VFX PRODUCTION SPECIFICATION

## 22.1. VFX North Star

> **Fast to read, satisfying to feel, controlled to watch.**

## 22.2. VFX hierarchy

### P0 — Gameplay critical
- enemy telegraph;
- hazard;
- Core danger;
- Tower disable;
- EMP;
- boss attack;
- objective completion/failure.

### P1 — Combat feedback
- projectile;
- impact;
- muzzle flash;
- hit reaction;
- activation.

### P2 — Presentation
- upgrade;
- reward;
- ambient;
- cosmetic.

## 22.3. Projectile VFX

Required when relevant:

- muzzle flash;
- projectile body/trail;
- impact;
- optional lingering effect.

Projectile không cần quá nhiều particles để tạo cảm giác mạnh.

## 22.4. Explosion recipe

Baseline visual rhythm:

```text
Flash
→ Core
→ Expansion
→ Debris / Smoke
→ Recovery
```

## 22.5. Hazard VFX

Mỗi major hazard cần:

```text
Warning
→ Pre-impact
→ Active
→ Recovery
```

Ví dụ:

**EMP**

`signal pulse → electronic flicker → disable ring → recovery`

**Acid Rain**

`warning → sky shift → rain → impact highlight → damage state`

**Earthquake**

`crack → micro-shake → terrain effect → recovery`

## 22.6. VFX limits

VFX không được:

- che Tower/enemy identity;
- che Core state;
- che lane;
- tạo saturation cao toàn màn;
- làm mọi asset phát sáng cùng lúc.

---

# XXIII. ANIMATION PRODUCTION SPECIFICATION

## 23.1. Animation grammar

Core grammar:

> **Anticipation → Action → Impact → Recovery**

## 23.2. Animation priorities

### Character
1. Idle personality.
2. Move.
3. Combat.
4. Skill.
5. Hit.
6. Repair/Interact.
7. Victory/Defeat.

### Tower
1. Idle/ready.
2. Target.
3. Fire.
4. Recoil.
5. Recovery.
6. Special state.

### Enemy
1. Move.
2. Attack prep.
3. Attack.
4. Hit.
5. Special.
6. Death.

## 23.3. Mobile animation rules

- silhouette không vỡ ở motion blur;
- anticipation đủ dài để hiểu nhưng không trì hoãn gameplay;
- impact rõ nhưng không camera-shake quá mạnh;
- loop idle không gây mỏi mắt;
- critical animations ưu tiên readability over spectacle.

## 23.4. Animation DoD

- [ ] timing rõ;
- [ ] silhouette ổn định;
- [ ] không xuyên visual nghiêm trọng;
- [ ] personality đúng;
- [ ] gameplay cue rõ;
- [ ] không quá “floaty”.

---

# XXIV. PORTRAIT PRODUCTION SPECIFICATION

## 24.1. Portrait purpose

Portrait phục vụ:

- Commander selection;
- dialogue;
- mission briefing;
- result;
- profile;
- progression.

## 24.2. Portrait rules

- face is hero;
- role cue visible;
- expression specific to context;
- background simple;
- lighting consistent với character family.

## 24.3. Portrait variants

Tối thiểu khi cần:

- neutral;
- focused;
- concern/warning;
- positive/success;
- special emotion.

Không yêu cầu tạo mọi expression cho mọi character nếu không có UI/story use case.

---

# XXV. COSMETIC PRODUCTION SPECIFICATION

## 25.1. Cosmetic categories

Được định hướng trong design hiện tại:

- Tower Skin;
- Commander Skin;
- Explosion/Projectile VFX;
- Weather VFX;
- UI/Profile cosmetics;
- seasonal/event cosmetics.

## 25.2. Cosmetic principle

Cosmetic phải:

- giữ gameplay readability;
- không gây nhầm enemy/tower;
- không thay đổi chức năng core;
- phù hợp visual identity;
- có thể có personality mạnh hơn asset base.

## 25.3. Cosmetic contrast budget

Cosmetic được phép nổi bật hơn base asset, nhưng không được vượt hierarchy của:

- critical enemy;
- danger;
- objective.

## 25.4. Monetization boundary

Asset production không được biến cosmetic thành disguised stat advantage.

Nếu cosmetic có VFX/audio thay đổi, phải kiểm tra readability và accessibility.

---

# XXVI. STATE / VARIANT MATRIX

Mỗi asset brief phải xác định rõ state cần production.

| Asset | Base | Active | Warning | Damage | Disabled | Upgrade | Death | Cosmetic |
|---|---|---|---|---|---|---|---|---|
| Commander | ✓ | ✓ | contextual | contextual | contextual | ✓ | ✓ | ✓ |
| Enemy | ✓ | ✓ | ✓ | ✓ | contextual | N/A | ✓ | optional |
| Tower | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | contextual | ✓ |
| Building | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | contextual | ✓ |
| Boss | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | optional |
| UI Icon | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | N/A | optional |
| VFX | ✓ | ✓ | ✓ | contextual | contextual | ✓ | ✓ | ✓ |

**contextual** = chỉ sản xuất nếu gameplay/UI thực sự sử dụng.

---

# XXVII. VFX + SFX HANDOFF CONTRACT

Asset không hoàn tất chỉ khi model đẹp.

## 27.1. VFX metadata

Mỗi effect cần:

```text
Effect ID:
Trigger:
Start cue:
Peak cue:
End cue:
World-space / screen-space intent:
Gameplay priority:
Expected footprint:
Readable from gameplay camera:
Can stack with:
Must not obscure:
```

## 27.2. SFX metadata

Audio handoff cần:

```text
SFX ID:
Trigger:
Source asset:
Category:
Priority:
One-shot / Loop:
Spatial intent:
Gameplay critical?:
Must cut through ambience?:
Related warning/telegraph:
Variants required:
```

Audio specifics follow **AUDIO DIRECTION & SOUND BIBLE v1.0**.

---

# XXVIII. ASSET HIERARCHY & DETAIL BUDGET

## 28.1. Detail tiers

### D1 — Primary silhouette
Thứ bắt buộc.

### D2 — Functional detail
Giúp đọc chức năng.

### D3 — Personality detail
Tạo character.

### D4 — Micro detail
Chỉ thêm khi không ảnh hưởng readability.

Không production asset nào được phép bỏ D1–D2 để chạy thẳng vào D4.

## 28.2. Mobile detail rule

Khi cần giảm complexity:

1. giữ silhouette;
2. giữ function cue;
3. giữ semantic color;
4. giữ signature detail;
5. bỏ micro detail trước.

---

# XXIX. QUALITY CHECKLIST — ART

## Silhouette

- [ ] Asset nhận ra ở gameplay size.
- [ ] Không phụ thuộc texture.
- [ ] Nhìn từ góc gameplay vẫn đúng identity.

## Shape

- [ ] Chunky nhưng không méo.
- [ ] Asymmetry có chủ đích.
- [ ] Không giống generic asset của game khác.

## Color

- [ ] Đúng visual family.
- [ ] Semantic color chính xác.
- [ ] Không oversaturate toàn asset.

## Material

- [ ] Metal/concrete/organic có distinction.
- [ ] Roughness/shine không phá readability.
- [ ] Scrap-tech nhất quán.

## Mobile

- [ ] Không mất detail quan trọng.
- [ ] Không trở thành blob.
- [ ] Không quá noisy.

---

# XXX. QUALITY CHECKLIST — ANIMATION

- [ ] Anticipation rõ.
- [ ] Action readable.
- [ ] Impact có cảm giác.
- [ ] Recovery không quá dài.
- [ ] Loop không gây fatigue.
- [ ] Signature motion rõ.
- [ ] Không làm mất silhouette.

---

# XXXI. QUALITY CHECKLIST — VFX

- [ ] Có mục đích gameplay/presentation rõ.
- [ ] Telegraph đọc được.
- [ ] Không che critical information.
- [ ] Không excessive bloom.
- [ ] Không particle spam.
- [ ] Có start/active/end rhythm.
- [ ] Có fallback/readability treatment nếu accessibility yêu cầu.

---

# XXXII. QUALITY CHECKLIST — UI ASSET

- [ ] Hierarchy rõ.
- [ ] Hit/selected/disabled state rõ.
- [ ] Icon family nhất quán.
- [ ] Typography phù hợp.
- [ ] Semantic colors đúng.
- [ ] Không card overload.
- [ ] Touch target không bị artwork làm khó hiểu.

---

# XXXIII. QA / PLAYTEST ASSET PASS

Asset cần được kiểm tra trong context thật, không chỉ trong viewer.

## 33.1. Battle test

Test ít nhất:

- low density;
- normal combat;
- high enemy density;
- hazard active;
- boss encounter;
- low-resource state;
- low brightness/night context nếu applicable.

## 33.2. Outpost test

Test:

- early Outpost;
- mid Outpost;
- late Outpost;
- nhiều building cùng lúc;
- decoration density;
- resident activity nếu có.

## 33.3. UI test

Test:

- smallest practical screen size;
- high information density;
- selected state;
- warning state;
- critical state;
- localization expansion khi content thay đổi.

---

# XXXIV. PRIORITY SYSTEM

## P0 — Required for core gameplay

- Commander base set.
- Core Tower set.
- Core Barrel/Module set.
- Initial enemy roster required by vertical slice.
- Base Outpost.
- Core biome environment.
- Combat VFX.
- Core UI.
- Critical icons.

## P1 — Required for full campaign quality

- Full enemy roster.
- Additional Tower/Module content.
- Boss asset set.
- Multiple biome prop sets.
- Outpost progression stages.
- Weather/hazard presentation.

## P2 — Expansion / polish

- Cosmetic sets.
- Advanced VFX.
- Seasonal visual packs.
- Secondary Outpost customization.

## P3 — Optional experimental

- Rare presentation effects.
- Marketing-only hero assets.
- Experimental cosmetic spectacle.

Không được đẩy P2/P3 lên trước P0/P1 vì lý do “đẹp hơn”.

---

# XXXV. PRODUCTION HANDOFF

## 35.1. Artist → Game/Implementation handoff

Asset package phải có:

1. Asset ID.
2. Approved reference.
3. State list.
4. Relative scale.
5. Pivot/origin intent nếu cần.
6. Animation list.
7. VFX links.
8. SFX links.
9. UI icon/portrait links.
10. Known limitations.

## 35.2. Artist → QA handoff

QA cần biết:

- what to recognize;
- what state is expected;
- what should never be hidden;
- which color is semantic;
- what visual behavior is intentional.

## 35.3. Design → Artist handoff

Design brief phải luôn nói:

> **WHY this asset exists**

trước khi nói asset cần “trông ngầu”.

---

# XXXVI. CHANGE CONTROL

## 36.1. Không tự ý sửa asset chỉ vì preference cá nhân

Ví dụ không đủ lý do:

- “đổi màu cho đẹp hơn”;
- “thêm detail cho ngầu”;
- “cho realistic hơn”;
- “thêm effect vì đang trống”.

## 36.2. Change request hợp lệ

- readability issue;
- gameplay confusion;
- visual conflict;
- accessibility issue;
- source-of-truth conflict;
- performance/production constraint;
- content expansion đã được duyệt.

## 36.3. Change note

Mỗi thay đổi lớn phải ghi:

```text
WHY:
WHAT:
AFFECTED ASSETS:
GAMEPLAY IMPACT:
VISUAL IMPACT:
AUDIO/VFX IMPACT:
RISK:
APPROVED BY:
DATE:
```

---

# XXXVII. DEFINITION OF DONE — GENERIC ASSET

Một asset được coi là **PRODUCTION READY** khi:

- [ ] Có Asset ID.
- [ ] Có approved brief/reference.
- [ ] Đúng visual identity.
- [ ] Đúng relative scale.
- [ ] Đúng perspective.
- [ ] Silhouette pass.
- [ ] Functional readability pass.
- [ ] State matrix hoàn tất.
- [ ] Animation requirements hoàn tất hoặc explicitly N/A.
- [ ] VFX requirements hoàn tất hoặc explicitly N/A.
- [ ] SFX handoff metadata hoàn tất hoặc explicitly N/A.
- [ ] UI icon/portrait đã xác định.
- [ ] Mobile readability pass.
- [ ] QA notes hoàn tất.
- [ ] Version/changelog rõ.

---

# XXXVIII. DEFINITION OF DONE — GAMEPLAY CRITICAL ASSET

Ngoài DoD generic, gameplay-critical asset phải pass:

- [ ] Readable giữa crowd.
- [ ] Không bị VFX khác che.
- [ ] Không bị semantic color conflict.
- [ ] Telegraph hoặc state cue rõ nếu applicable.
- [ ] Distinct identity.
- [ ] Tested trong representative mission.

---

# XXXIX. DEFINITION OF DONE — OUTPOST / MAJOR BUILDING

- [ ] Đọc đúng stage progression.
- [ ] Có landmark value.
- [ ] Không phá visual hierarchy.
- [ ] Có activity/life cue phù hợp.
- [ ] Compatible với stage khác.
- [ ] Có hero/reference shot.

---

# XL. DOCUMENT / ASSET DEPENDENCIES

Asset Production Specification phụ thuộc trực tiếp vào:

- **Visual Identity & Art Direction v1.0** — visual north star.
- **Visual Reference Pack v1.0** — concrete reference.
- **System & Gameplay v1.1** — gameplay role.
- **Core Combat Gameplay v1.0** — combat states/cues.
- **Content Specification v1.0** — content role.
- **Production Content Bible v1.0** — production consistency.
- **Vertical Slice Specification v1.0** — minimum representative quality.
- **Toi_Uu v1.2** — latest locked core optimization/current systems.
- **Audio Direction & Sound Bible v1.0** — audio relationship.
- **World & Narrative Bible v1.0** — world context and narrative identity.

---

# XLI. CLAUDE HANDOFF BOUNDARY

Khi Claude nhận một asset specification, Claude phải hiểu rằng tài liệu này quy định:

- **what** asset exists;
- **why** it exists;
- **how it should read visually**;
- **which states are expected**;
- **which supporting assets exist**;
- **what cannot be changed casually**.

Tài liệu này **không** quyết định:

- engine implementation;
- class architecture;
- code structure;
- shader implementation;
- rendering API;
- file loading code.

Những nội dung đó thuộc Technical Architecture / implementation documentation.

---

# XLII. PRODUCTION RED FLAGS

Dừng và review khi gặp một trong các tình huống:

### Red Flag 1 — “Asset đẹp nhưng không đọc được”
→ silhouette/function review.

### Red Flag 2 — “Thêm detail để làm asset khác biệt”
→ shape review trước.

### Red Flag 3 — “Đổi màu là thành enemy mới”
→ reject nếu identity chưa đủ khác.

### Red Flag 4 — “VFX đẹp nhưng che gameplay”
→ VFX priority review.

### Red Flag 5 — “Cosmetic làm khó nhận ra Tower/Enemy”
→ reject hoặc giảm contrast.

### Red Flag 6 — “Boss chỉ là enemy phóng to”
→ boss identity review.

### Red Flag 7 — “Outpost stage mới chỉ nhiều đồ hơn”
→ progression art review.

### Red Flag 8 — “UI panel nào cũng thành card”
→ UI hierarchy review.

### Red Flag 9 — “Asset cần zoom mới hiểu”
→ mobile readability review.

### Red Flag 10 — “Art request vô tình thay đổi gameplay”
→ return to Design source of truth.

---

# XLIII. PRODUCTION CHECKLIST — NEW ASSET

```text
[ ] Design source identified
[ ] Visual source identified
[ ] Asset ID assigned
[ ] Purpose written
[ ] Gameplay role written
[ ] Silhouette approved
[ ] Relative scale assigned
[ ] Perspective assigned
[ ] Material family assigned
[ ] Semantic colors assigned
[ ] State matrix assigned
[ ] Animation list assigned
[ ] VFX list assigned
[ ] SFX handoff assigned
[ ] UI dependencies assigned
[ ] Portrait dependency assigned
[ ] Cosmetic dependency assigned
[ ] Priority assigned
[ ] QA acceptance criteria written
[ ] Changelog ready
```

---

# XLIV. FINAL ASSET NORTH STAR

> **Mỗi asset của LAST OUTPOST phải vừa thuộc cùng một thế giới, vừa có đủ cá tính để người chơi nhận ra nó ngay khi đang bận chiến đấu.**

Asset thành công không phải asset có nhiều detail nhất.

Asset thành công là asset:

> **nhìn một lần hiểu được — nhìn lâu vẫn thấy cá tính — đặt cạnh asset khác vẫn thấy cùng một game.**

---

# XLV. NON-NEGOTIABLES

1. **Stylized 2.5D** là nền tảng.
2. **Cartoon, không full chibi.**
3. **Friendly post-apocalypse**, không grimdark/horror-first.
4. **Silhouette > detail.**
5. **Gameplay readability > spectacle.**
6. **Tower = Base + Barrel + Module.**
7. Enemy species phải có identity riêng.
8. Commander phải có personality + role readability.
9. Outpost phải thể hiện progression thật.
10. VFX không được che gameplay-critical information.
11. Cosmetic không được phá readability.
12. Không tự ý đổi core gameplay hoặc terminology vì nhu cầu art.
13. Production assets phải có trạng thái, naming, version và handoff rõ.
14. Asset quality phải được kiểm tra trong context gameplay thật.

---

# XLVI. VERSION HISTORY

## v1.0 — 2026-09-24

**Initial production baseline.**

Được tạo để lấp khoảng trống giữa:

- Visual Direction;
- Visual Reference;
- Content Production;
- Gameplay Design;

và production handoff.

Không thay đổi core gameplay.

Các phần được đánh dấu là **production convention / proposal** không được hiểu là gameplay decision mới trừ khi được đưa vào Current Decisions / Master Design Index.
