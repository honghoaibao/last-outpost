# LAST OUTPOST — AUDIO DIRECTION & SOUND BIBLE
## Version 1.0

**Trạng thái:** Design / Production Specification
**Phạm vi:** Audio Identity, SFX, Ambience, Music, Tactical Feedback, Accessibility, Production Handoff
**Vai trò tài liệu:** Source of truth cho định hướng và tiêu chuẩn audio của LAST OUTPOST.
**Không bao gồm:** Engine implementation, middleware setup, code, DSP implementation chi tiết.

---

# 0. DOCUMENT PURPOSE

Tài liệu này khóa cách **LAST OUTPOST nghe như thế nào**, audio phục vụ gameplay ra sao, asset audio cần được sản xuất ở mức nào, và tiêu chí để audio được xem là đạt production quality.

Audio của LAST OUTPOST không được tồn tại như một lớp trang trí tách rời gameplay.

Nó phải phục vụ đồng thời 4 mục tiêu:

1. **Readability** — người chơi hiểu chuyện gì vừa xảy ra.
2. **Tactical Feedback** — người chơi nhận biết nguy hiểm, timing và trạng thái mà không cần nhìn liên tục vào UI.
3. **Atmosphere** — thế giới hậu tận thế có cảm giác sống, có lịch sử và có hy vọng.
4. **Personality** — Tower, Enemy, Commander, Biome và Outpost có bản sắc âm thanh riêng.

---

# 1. SOURCE OF TRUTH & DESIGN STATUS

## 1.1. Các quyết định nền đã được chốt từ tài liệu hiện tại

Các nguyên tắc sau được kế thừa trực tiếp từ design hiện có:

- Visual identity: **Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy**.
- Thế giới là **friendly post-apocalypse**, không grimdark, không horror-first.
- Battle dài khoảng **5–15 phút**.
- Enemy roster theo mission là **fixed**, không tự sinh thêm species ngoài roster đã định.
- Mọi threat lớn cần telegraph rõ.
- Audio là một phần của telegraph.
- Major Hazard có chu kỳ **CLEAR → TELEGRAPH → WARNING → ACTIVE → PEAK → RECOVERY**.
- Gameplay audio phải giúp trả lời 3 câu:
  1. Có gì vừa xảy ra?
  2. Nó có nguy hiểm không?
  3. Tôi có cần làm gì không?
- Vertical Slice tối thiểu phải có Outpost ambience, combat loop, Boss music, 6 enemy audio identities, 6 tower firing/impact groups, UI clicks, warning, victory, defeat và upgrade.
- Khi swarm rất lớn, không được phát một audio event độc lập cho từng unit; phải ưu tiên pooled impacts, layered ambience và threat-specific cues.
- Không có critical gameplay information nào được truyền **chỉ bằng audio**.

## 1.2. Phạm vi được bổ sung trong tài liệu này

Các nội dung về:

- hierarchy của audio;
- sound identity của từng category;
- biome ambience;
- dynamic music structure;
- audio priority;
- asset naming/versioning;
- production checklist;
- QA và accessibility;
- budget/rule cho swarm và mobile;

là **quy chuẩn Audio mới được đề xuất và khóa trong v1.0 này**, trừ khi tài liệu tương lai thay thế rõ ràng.

---

# 2. AUDIO IDENTITY

## 2.1. One-line identity

> **Warm, tactile, readable survival soundscape — nơi máy móc cũ, thiên nhiên đang hồi phục và những mối đe dọa mới cùng tồn tại trong một thế giới hậu tận thế đầy màu sắc.**

## 2.2. 5 Audio Pillars

### A. Tactical First
Âm thanh quan trọng phải dễ nhận biết trước khi đẹp.

Nếu một sound design “ấn tượng” nhưng khiến người chơi khó nghe warning quan trọng, thiết kế đó fail.

### B. Chunky & Tactile

Tower, projectile, hit, repair, upgrade và object interaction cần có cảm giác:

- có khối lượng;
- có vật liệu;
- có điểm impact rõ;
- không mỏng hoặc quá “UI clicky”.

### C. Friendly Apocalypse

Post-apocalyptic không đồng nghĩa với:

- nền âm u;
- tiếng súng military realism liên tục;
- horror drone;
- gore sound;
- jumpscare.

Âm thanh có thể mang cảm giác cũ, hỏng, bụi bặm, nhưng vẫn có warmth và personality.

### D. Expressive Personality

Commander và Enemy cần nghe **khác nhau về tính cách**, không chỉ khác pitch.

### E. Controlled Spectacle

Boss và major event được phép lớn và ấn tượng, nhưng phải giữ khoảng trống cho tactical cues.

---

# 3. AUDIO HIERARCHY

Khi nhiều âm thanh xảy ra cùng lúc, ưu tiên mặc định:

| Priority | Category | Mục tiêu |
|---|---|---|
| P0 | Critical gameplay | Immediate reaction |
| P1 | Major tactical | Decision / awareness |
| P2 | Core combat | Feel / confirmation |
| P3 | Environment | Atmosphere |
| P4 | Cosmetic / flavor | Personality |

## 3.1. P0 — Critical Gameplay

Ví dụ:

- Core critical;
- Boss attack telegraph;
- Major Hazard warning/peak;
- EMP charge;
- Overheat critical;
- critical logistics failure;
- game-defining objective failure.

P0 không được bị nhấn chìm bởi ambience, music hoặc spam impact.

## 3.2. P1 — Major Tactical

Ví dụ:

- Boss arrival;
- special enemy ability;
- Burrower surfacing;
- Corpse density threshold;
- important resource state;
- tower malfunction/cooldown-ready cue;
- mission objective state change.

## 3.3. P2 — Core Combat

Ví dụ:

- firing;
- projectile impact;
- hit confirm;
- enemy death;
- reload/charge/recovery;
- ordinary ability use.

## 3.4. P3/P4

Ambience, incidental props, cosmetic interaction và flavor sound luôn nhường chỗ cho gameplay.

---

# 4. MIXING PHILOSOPHY

Audio phải tạo cảm giác có nhiều lớp nhưng không biến thành “wall of noise”.

## 4.1. Không gian âm thanh theo 4 lớp

### Layer 1 — World

- wind;
- machinery;
- insects;
- water;
- distant structures;
- environmental movement.

### Layer 2 — Threat

- enemy presence;
- telegraph;
- boss;
- hazard;
- special event.

### Layer 3 — Player Agency

- Tower fire;
- ability activation;
- interaction;
- repair;
- upgrade;
- tactical confirmation.

### Layer 4 — Music

- exploration / preparation;
- combat intensity;
- boss;
- recovery / victory.

Trong tình huống conflict, Layer 2 và critical Layer 3 có quyền ưu tiên hơn Layer 1 và Layer 4.

---

# 5. OUTPOST AUDIO IDENTITY

Outpost phải là nơi người chơi cảm thấy:

> **“Mình đang xây dựng lại sự sống.”**

## 5.1. Tone

- calm;
- warm;
- hopeful;
- lightly mechanical;
- handmade;
- lived-in;
- recovering.

Không dùng soundtrack hoặc ambience khiến Outpost nghe như:

- bunker kinh dị;
- căn cứ quân sự hiện đại;
- thành phố cyberpunk;
- khu nhà máy công nghiệp nặng toàn thời gian.

## 5.2. Outpost ambience layers

### Base Layer

- wind nhẹ;
- distant world tone;
- soft mechanical hum;
- wooden/metal structure movement rất nhẹ.

### Living Layer

- hoạt động sửa chữa;
- công cụ;
- bước chân nhẹ;
- chuyển động vật liệu;
- occasional human activity.

### Progression Layer

Khi Outpost phát triển, ambience có thể dần thêm:

- nhiều machinery hơn;
- nhiều hoạt động cư dân hơn;
- supply movement;
- subtle electrical systems;
- garden/agriculture/natural life nếu có trong art direction.

Mục tiêu: người chơi **nghe được sự trưởng thành của Outpost**.

## 5.3. Outpost state variants

Tối thiểu:

- Early / sparse;
- Established;
- Advanced.

Không yêu cầu thay toàn bộ soundtrack; ưu tiên additive layers.

---

# 6. COMBAT AUDIO IDENTITY

Combat phải nghe:

> **tactile + energetic + readable + adventurous**

chứ không phải:

> military simulator + horror + noise wall.

## 6.1. Core combat sound vocabulary

Combat SFX cần có 6 nhóm vật liệu/feedback nền:

1. Kinetic — impact cơ học.
2. Fire — heat, ignition, flare.
3. Acid — sizzle, corrosion, wet chemical impact.
4. Electric — arc, pulse, crackle.
5. Freeze — crystalline, brittle, cold resonance.
6. Explosive — low-end burst + transient impact.

Các nhóm này phải đủ khác nhau để người chơi nhận biết damage family bằng tai.

---

# 7. TOWER AUDIO

Tower được định nghĩa bởi:

> **Base + Barrel + Module**

Audio phải phản ánh modularity này.

## 7.1. Tower sound layers

Mỗi Tower có thể gồm:

- idle identity;
- targeting/charge;
- firing;
- projectile/launch;
- impact;
- reload/cooldown/recovery;
- damage state;
- destruction state;
- module signature.

Không yêu cầu mỗi Base/Barrel/Module phải có sound riêng độc lập trong mọi trường hợp.

Mục tiêu production là tạo cảm giác:

> **các bộ phận phối hợp tạo thành một machine personality.**

## 7.2. Tower sound categories

### Kinetic Tower

- mechanical snap;
- compressed force;
- chunky impact.

### Fire Tower

- ignition;
- pressurized heat;
- sustained burn character.

### Electric Tower

- charge whine;
- arc;
- electrical discharge.

### Explosive Tower

- heavy launch;
- pronounced impact;
- debris accent.

### Control / Utility Tower

- pulse;
- field activation;
- restrained tonal layer.

## 7.3. Module audio

Module không được biến thành một sound effect nhỏ phát ra mỗi frame.

Thay vào đó, Module cần một **signature moment** tại một hoặc nhiều điểm:

- activation;
- special effect;
- major state change;
- upgrade;
- overdrive.

---

# 8. ENEMY AUDIO BIBLE

Enemy audio cần đạt 3 tầng:

1. **Presence** — “thứ gì đó thuộc species này đang ở gần”.
2. **Intent** — “nó sắp làm gì”.
3. **Impact** — “nó vừa làm điều đó”.

## 8.1. Enemy sound principles

- Không để mọi enemy cùng dùng scream/growl generic.
- Signature sound phải nhận ra được ở volume vừa phải.
- Special attack cần cue rõ.
- Death sound không gore-first.
- Swarm units dùng family layers thay vì phát đầy đủ một-shot cho từng unit.

## 8.2. Signature threat cues

### EMP threat

- rising electronic charge;
- electrical instability;
- short release/impact.

### Burrower

- subterranean rumble;
- dirt/rock displacement;
- emergence cue.

### Corpse-related threat

- low organic movement;
- density threshold cue;
- transition cue khi corpse field đạt ngưỡng.

### Air threat

- distant aerial presence;
- fast pass;
- drop/impact cue.

### Buffer / Support threat

- restrained signal-like cue;
- distinguishable from direct damage units.

---

# 9. COMMANDER AUDIO

Commander cần có personality nhưng không được превращ thành hero voice-over game RPG.

## 9.1. Commander audio gồm

- movement/interaction flavor;
- ability activation;
- damage/downed state nếu gameplay cần;
- tactical confirmation;
- special signature.

## 9.2. Voice philosophy

Voice lines là **optional flavor layer**, không phải nguồn thông tin duy nhất.

Không yêu cầu full spoken dialogue cho mọi UI action.

Nếu Commander có voice:

- ngắn;
- expressive;
- dễ localization;
- không quá theatrical;
- không cướp attention khỏi tactical feedback.

---

# 10. BOSS AUDIO

Boss là audio spotlight nhưng phải phục vụ mechanic.

## 10.1. Boss audio phải báo được

- arrival;
- current phase;
- major attack;
- vulnerable window nếu gameplay cần;
- phase transition;
- defeat.

## 10.2. Boss music

Boss music nên có:

- recognisable motif;
- tension increase;
- phase variation;
- clear ending/release.

Không cần viết full cinematic score cho mọi Boss ở early production.

## 10.3. Boss sound signature

Mỗi Boss tối thiểu cần:

- 1 signature presence;
- 1 arrival cue;
- 1 signature attack cue;
- 1 phase transition cue;
- 1 defeat cue.

---

# 11. MAJOR HAZARD AUDIO

Major Hazard dùng chu kỳ:

**CLEAR → TELEGRAPH → WARNING → ACTIVE → PEAK → RECOVERY**

Mỗi stage phải có audio language riêng.

## 11.1. Telegraph

- subtle;
- nhận biết được;
- không gây panic.

## 11.2. Warning

- tăng urgency;
- ngắn;
- repetition có kiểm soát.

## 11.3. Active

- ambience/loop đặc trưng hazard.

## 11.4. Peak

- strongest cue;
- phải dễ nhận biết trong combat.

## 11.5. Recovery

- release;
- return-to-normal indicator.

Không spam warning sound liên tục.

---

# 12. CORE CRITICAL / RESOURCE / LOGISTICS AUDIO

## 12.1. Core critical

Đây là P0 cue.

Yêu cầu:

- unmistakable;
- ngắn nhưng đủ nhận biết;
- có escalation nếu trạng thái tiếp tục.

Không dùng cùng exact sound với một warning thông thường.

## 12.2. Overheat

Có tối thiểu 3 trạng thái:

- approaching;
- critical;
- recovery.

Audio escalation phải rõ nhưng không gây fatigue.

## 12.3. Resource alert

Resource alert không được tạo cảm giác “mọi thứ đều khẩn cấp”.

Chỉ alert khi:

- resource ảnh hưởng trực tiếp tới tactical decision;
- logistics bị đe dọa;
- một action không thể thực hiện vì resource state.

---

# 13. UI SOUND BIBLE

UI audio phải:

- soft;
- responsive;
- readable;
- có personality;
- không biến app/game thành click simulator.

## 13.1. UI base vocabulary

- hover/focus nếu phù hợp với PC;
- select;
- confirm;
- cancel;
- back;
- tab change;
- reward;
- unlock;
- upgrade;
- warning;
- error;
- purchase/transaction confirmation.

## 13.2. Upgrade sound

Upgrade phải có cảm giác:

> **Progression achieved**

không cần quá dài.

Ưu tiên tactile mechanical + bright tonal confirmation.

## 13.3. Reward sound

Reward phải phân biệt được theo tier nhưng không tạo casino-like escalation.

---

# 14. BIOME AMBIENCE

Biomes cần cùng một audio family nhưng có acoustic identity riêng.

## 14.1. Ruined Urban Core

Core identity:

- gió giữa công trình;
- kim loại/cửa/sign chuyển động;
- distant structure hum;
- electrical remnants;
- occasional debris movement.

Mood:

> abandoned but not dead.

## 14.2. Abandoned Industrial Zone

Core identity:

- heavy machinery resonance;
- pipes;
- valves;
- metal creaks;
- pressure;
- electrical hum.

Mood:

> dormant machine world.

## 14.3. Rocky Quarry

Core identity:

- wind;
- open space;
- stone movement;
- metallic tools/machinery remnants;
- distant echoes.

Mood:

> exposed, dry, spacious.

## 14.4. Toxic Wetland

Core identity:

- water movement;
- bubbles;
- wet surfaces;
- insects/creatures;
- soft chemical texture.

Không dùng horror-centric insect swarm hoặc gore audio.

Mood:

> strange, alive, dangerous.

## 14.5. Frozen Wasteland

Core identity:

- wind;
- ice movement;
- brittle structures;
- snow/ground texture;
- distant emptiness.

Mood:

> cold, exposed, quiet, beautiful.

---

# 15. MUSIC DIRECTION

## 15.1. Musical identity

Music phải hỗ trợ:

- adventure;
- rebuilding;
- resilience;
- tactical tension;
- curiosity;
- moments of relief.

Tránh:

- perpetual sadness;
- heroic military march;
- horror drone;
- generic fantasy orchestral RPG;
- overly childish cartoon scoring.

## 15.2. Core palette

Định hướng nhạc có thể kết hợp:

- organic/acoustic textures;
- restrained percussion;
- warm pads;
- subtle mechanical/electronic textures;
- melodic motifs đơn giản.

Không khóa instrument list cứng trong v1.0; khóa **mood + function** trước.

## 15.3. Music states

Tối thiểu:

1. Outpost / Preparation
2. Combat
3. High Pressure
4. Boss
5. Victory / Recovery

Không cần một track hoàn toàn mới cho mỗi state.

Ưu tiên thematic continuity.

---

# 16. DYNAMIC MUSIC

Dynamic music phải tạo cảm giác trận đấu đang thay đổi mà không gây “đập nhạc” quá thường xuyên.

## 16.1. Recommended intensity model

### State A — Calm / Preparation

- sparse;
- warm;
- curiosity.

### State B — Engaged Combat

- stronger pulse;
- higher rhythmic activity.

### State C — High Pressure

- denser percussion;
- increased urgency;
- vẫn giữ melody identity.

### State D — Boss

- distinct theme;
- strong rhythmic identity;
- phase accents.

### State E — Recovery

- energy release;
- return to calm.

## 16.2. Dynamic music rules

- Không thay đổi state vì một event rất nhỏ.
- Không reset track chỉ vì wave mới.
- Transition phải ưu tiên musical continuity.
- P0 tactical cue luôn được ưu tiên hơn music.

---

# 17. COMBAT MUSIC VS SFX BALANCE

Một lỗi production cần tránh:

> music nghe hay nhưng combat feedback biến mất.

Trong combat:

- tactical SFX có priority cao hơn music;
- music không được phủ toàn bộ transient của attack;
- Boss music được phép lớn hơn combat music nhưng vẫn phải để telegraph đi qua.

---

# 18. SPATIAL AUDIO DIRECTION

Không khóa implementation engine.

Về design, audio cần phân biệt được:

- near;
- mid;
- far;
- global/system.

## 18.1. Near

Thông tin có ảnh hưởng trực tiếp tới vị trí hiện tại.

Ví dụ:

- hit gần Tower;
- Burrower emerging;
- explosion gần objective.

## 18.2. Mid

Threat hoặc event đang ở khu vực lân cận.

## 18.3. Far

World presence hoặc distant threat.

## 18.4. Global

- mission victory;
- defeat;
- major objective state;
- UI/system.

Không làm spatialization quá mạnh đến mức player bỏ lỡ gameplay information.

---

# 19. SWARM / HIGH-DENSITY AUDIO RULES

Game có long-term target với swarm rất lớn.

Không được thiết kế audio dựa trên giả định:

> 1 unit = 1 full SFX instance.

## 19.1. Dùng grouped audio cho

- mass movement;
- repeated minor hits;
- repeated deaths;
- crowd pressure;
- environmental chaos.

## 19.2. Single-instance priority cho

- Boss;
- elite/special threat;
- Major Hazard;
- EMP;
- Core critical;
- critical objective state.

## 19.3. Goal

Peak combat phải nghe **lớn và sống**, nhưng không thành:

> “một nghìn file âm thanh cùng phát”.

---

# 20. AUDIO FATIGUE CONTROL

Không lặp quá dày các cue có attention cao.

Đặc biệt với:

- warning;
- error;
- reward;
- hit;
- repetitive enemy attack.

Production rule:

> Một cue quan trọng phải đủ distinct; một cue lặp thường xuyên phải đủ nhẹ để không làm người chơi mệt.

Variation được phép dùng để giảm repetition, nhưng không được làm mất recognition.

---

# 21. ACCESSIBILITY

## 21.1. Không phụ thuộc hoàn toàn vào nghe

Mọi critical audio cue phải có một hoặc nhiều visual alternatives phù hợp:

- icon;
- shape;
- animation;
- screen-space warning;
- UI state;
- VFX.

## 21.2. Settings tối thiểu

- Master Volume;
- Music Volume;
- SFX Volume;
- UI Volume;
- Ambience Volume nếu UX cần;
- Vibration toggle.

## 21.3. Reduced audio burden

Nếu có accessibility mode trong tương lai, ưu tiên:

- giảm low-priority ambience;
- giữ P0/P1 clarity;
- không tắt critical visual feedback.

## 21.4. Hearing-impaired support

Không được thiết kế mechanic kiểu:

> “chỉ nghe sound này mới biết phải né”.

Telegraph luôn có visual counterpart.

---

# 22. PC + MOBILE AUDIO UX

## Mobile

- compact mix;
- high readability;
- low fatigue;
- speaker playback phải vẫn nhận biết được P0/P1 cue.

## PC

Có thể tận dụng:

- wider dynamic range;
- headphone detail;
- more spatial nuance.

Nhưng gameplay information không được phụ thuộc vào headphone-grade detail.

---

# 23. VOICE / DIALOGUE AUDIO

LAST OUTPOST không phải story-heavy RPG.

Vì vậy voice production ưu tiên:

1. tactical flavor nếu cần;
2. Commander personality;
3. key narrative moments;
4. event flavor.

Không yêu cầu voice acting full campaign ở giai đoạn đầu.

## Dialogue audio rules

- ngắn;
- context-driven;
- không lặp quá thường xuyên;
- không chen lên P0 tactical cues;
- có subtitle/localization plan nếu voice được sản xuất.

---

# 24. AUDIO EVENT TAXONOMY

Các nhóm production chính:

### UI

`UI_SELECT`, `UI_CONFIRM`, `UI_CANCEL`, `UI_UPGRADE`, `UI_REWARD`, `UI_WARNING`

### Combat

`COMBAT_FIRE`, `COMBAT_IMPACT`, `COMBAT_HIT`, `COMBAT_DEATH`, `COMBAT_ABILITY`

### Threat

`THREAT_TELEGRAPH`, `THREAT_WARNING`, `THREAT_SPECIAL`, `THREAT_BOSS`

### Hazard

`HAZARD_TELEGRAPH`, `HAZARD_WARNING`, `HAZARD_ACTIVE`, `HAZARD_PEAK`, `HAZARD_RECOVERY`

### Objective

`OBJECTIVE_START`, `OBJECTIVE_PROGRESS`, `OBJECTIVE_SUCCESS`, `OBJECTIVE_FAIL`, `CORE_CRITICAL`

### World

`WORLD_AMBIENCE`, `WORLD_PROP`, `WORLD_WEATHER`, `WORLD_BIOME`

### Progression

`PROGRESSION_UNLOCK`, `PROGRESSION_UPGRADE`, `PROGRESSION_MASTERY`

---

# 25. NAMING & VERSIONING

Audio asset naming phải dễ search, dễ batch review và dễ thay thế.

## 25.1. Naming pattern

Đề xuất:

`LO_<DOMAIN>_<CATEGORY>_<SUBJECT>_<STATE>_<VARIANT>`

Ví dụ:

`LO_COMBAT_TOWER_FIRE_BASIC_01`

`LO_THREAT_EMP_CHARGE_01`

`LO_HAZARD_TOXIC_WARNING_02`

`LO_UI_UPGRADE_CONFIRM_01`

`LO_WORLD_BIOME_FROZEN_WIND_LOOP_01`

Đây là **production convention mới trong v1.0**, không phải engine requirement.

## 25.2. Versioning

Version được quản lý ở cấp content package, không bake version vào filename trừ trường hợp pipeline bắt buộc.

Ví dụ concept:

- asset ID ổn định;
- revision note tách riêng;
- replacement phải giữ semantic role.

---

# 26. AUDIO PRODUCTION SPEC — MỖI ASSET CẦN CÓ

Mỗi gameplay audio asset/collection cần metadata tối thiểu:

```text
Audio ID:
Category:
Gameplay Purpose:
Priority:
Source Context:
State:
Duration Class:
Variation Count:
Spatial Class:
Related VFX:
Related Animation:
Related UI:
Accessibility Alternative:
Biome/Scope:
Production Priority:
QA Status:
Revision:
```

---

# 27. AUDIO HANDOFF PER GAMEPLAY EVENT

Mỗi event quan trọng phải có:

```text
Event:
Gameplay Purpose:
Trigger Condition:
Audio Cue:
Cue Priority:
Expected Player Reaction:
Related Visual Cue:
Related VFX:
Camera/Screen Effect:
Accessibility Alternative:
Cooldown / Repetition Rule:
QA Notes:
```

Ví dụ:

```text
Event: EMP Stalker Charge
Gameplay Purpose: Báo special attack sắp xảy ra.
Trigger Condition: Enemy bắt đầu charge.
Audio Cue: Rising electrical charge.
Cue Priority: P0/P1.
Expected Player Reaction: Chuẩn bị counter / reposition.
Related Visual Cue: Pulse buildup.
Related VFX: Electrical buildup.
Accessibility Alternative: Clear icon + danger-area warning.
Cooldown / Repetition Rule: Không spam nếu nhiều EMP cùng charge trong cùng thời điểm.
QA Notes: Không được bị che bởi mass combat SFX.
```

---

# 28. VERTICAL SLICE AUDIO PACKAGE

Vertical Slice phải chứng minh được **audio language**, không chỉ có placeholder sound.

## Minimum locked package

- Outpost ambience;
- Combat loop;
- Boss music;
- 6 enemy audio identities;
- 6 tower firing/impact groups;
- UI clicks;
- Warning;
- Victory;
- Defeat;
- Upgrade.

## Khuyến nghị bổ sung cho slice

- Core critical;
- EMP charge;
- Burrower emergence;
- Hazard telegraph;
- Overheat warning;
- Boss phase transition;
- one biome ambience variant;
- one Commander signature.

Các mục bổ sung là **production recommendation**, không thay thế minimum đã chốt.

---

# 29. PRODUCTION PRIORITY

## P0 — Critical gameplay

- Core critical;
- Boss cues;
- Major Hazard cues;
- major telegraphs;
- victory/defeat;
- essential UI feedback.

## P1 — Core combat

- Tower fire/impact;
- Enemy identities;
- major abilities;
- upgrade;
- objective feedback.

## P2 — World / atmosphere

- biome ambience;
- Outpost ambience;
- weather;
- props.

## P3 — Flavor

- incidental reactions;
- cosmetic audio;
- optional voice flavor;
- premium cosmetic sound themes.

---

# 30. AUDIO QUALITY BAR

Một audio asset đạt chuẩn khi:

### Readability
- [ ] Nghe được vai trò.
- [ ] Không bị nhầm với cue cùng priority.
- [ ] Hoạt động trong mobile speaker mix.

### Gameplay
- [ ] Người chơi hiểu event.
- [ ] Người chơi nhận biết mức nguy hiểm.
- [ ] Người chơi biết có cần phản ứng hay không.

### Style
- [ ] Đúng stylized cartoon-adventure.
- [ ] Không harsh military realism.
- [ ] Không horror-first.
- [ ] Không generic fantasy RPG.

### Mix
- [ ] Không che P0/P1 cues.
- [ ] Không gây fatigue khi lặp.
- [ ] Không biến peak combat thành noise wall.

### Production
- [ ] Naming đúng.
- [ ] Metadata đủ.
- [ ] Related VFX/animation/UI đã xác định.
- [ ] Version/revision rõ.

---

# 31. PLAYTEST AUDIO TEST PLAN

## Test A — Normal Combat

Kiểm tra:

- firing rõ;
- hit rõ;
- enemy identity rõ;
- music không lấn SFX.

## Test B — Peak Combat

Kiểm tra:

- P0 vẫn nghe rõ;
- grouped combat sound không thành noise wall;
- Boss/hazard không bị mất;
- ambience tự động nhường priority.

## Test C — Eyes-off Micro Test

Tester nghe một đoạn ngắn mà hạn chế nhìn UI.

Mục tiêu không phải chơi “blind”, mà kiểm tra:

> Có nhận ra critical event bằng audio không?

## Test D — Silent Audio Test

Tắt audio.

Kiểm tra:

- critical information vẫn có visual communication;
- game không phá accessibility.

## Test E — Low Volume Test

Giảm volume xuống mức thấp.

P0/P1 phải còn tương đối phân biệt được trên thiết bị mục tiêu.

---

# 32. AUDIO FAILURE STATES

Audio fail nếu:

### Failure A — Pretty but Unreadable

Âm thanh đẹp nhưng player không hiểu event.

### Failure B — Everything Sounds Urgent

Quá nhiều warning làm tất cả mất ý nghĩa.

### Failure C — Generic Noise

Tower, Enemy và Hazard đều có cùng kiểu impact/alert.

### Failure D — Mobile Loss

Audio chỉ hoạt động tốt với headphones nhưng mất information trên phone speaker.

### Failure E — Loop Fatigue

Ambience/music lặp nhanh và gây khó chịu.

### Failure F — Audio-only Mechanic

Một mechanic quan trọng chỉ có thể nhận biết bằng tai.

---

# 33. AUDIO / VFX / UI RELATIONSHIP

Audio không được thiết kế độc lập hoàn toàn với visual feedback.

Mỗi important gameplay event nên có một mapping:

```text
Gameplay Event
   ↓
Animation / State
   ↓
Visual Telegraph
   ↓
Audio Cue
   ↓
VFX
   ↓
Optional Camera / Haptics
```

Không yêu cầu mọi event phải có đủ tất cả lớp.

Nhưng P0/P1 events phải có một bộ feedback đủ rõ để người chơi hiểu được trạng thái.

---

# 34. HAPTIC RELATIONSHIP

Vibration/haptics là lớp hỗ trợ, không phải source of truth.

Ưu tiên haptics cho:

- major hit;
- major explosion;
- Core critical;
- Boss impact;
- important upgrade;
- major reward.

Không rung mạnh cho mọi hit nhỏ.

---

# 35. LIVE OPS / COSMETIC AUDIO

Audio cosmetic được phép mở rộng identity nhưng không được làm ảnh hưởng gameplay readability.

Có thể dùng cho:

- Tower cosmetic themes;
- UI themes;
- event music packs;
- profile/audio flavor;
- special cosmetic VFX/SFX.

Không thêm sound effect cosmetic làm che mất P0/P1 cues.

---

# 36. SCOPE CONTROL

Không mặc định sản xuất:

- full voice-over campaign;
- unique music track cho từng mission;
- unique ambience cho từng map nhỏ;
- một bộ SFX hoàn toàn riêng cho mọi unit trong swarm;
- cinematic audio phức tạp cho mọi story beat.

Audio scope phải tăng theo gameplay value.

Câu hỏi kiểm tra:

> “Âm thanh này giúp player hiểu, quyết định hoặc cảm nhận điều gì?”

Nếu không có câu trả lời rõ, asset đó là optional.

---

# 37. DEFINITION OF DONE

## Audio System Direction

- [ ] Audio identity được khóa.
- [ ] Priority hierarchy được xác định.
- [ ] Tactical feedback rules rõ.
- [ ] Accessibility rules rõ.

## Outpost

- [ ] Ambience identity.
- [ ] At least 3 progression states được định nghĩa.

## Combat

- [ ] Core combat vocabulary.
- [ ] Tower SFX direction.
- [ ] Enemy SFX direction.
- [ ] Boss/Hazard cues.

## Music

- [ ] Outpost/prep direction.
- [ ] Combat loop direction.
- [ ] High-pressure direction.
- [ ] Boss direction.
- [ ] Victory/recovery direction.

## Production

- [ ] Naming convention.
- [ ] Metadata schema.
- [ ] Handoff template.
- [ ] QA checklist.
- [ ] Vertical Slice minimum.

---

# 38. NON-NEGOTIABLE AUDIO RULES

1. **Audio phải hỗ trợ tactical readability.**
2. **Không critical information nào chỉ được truyền bằng audio.**
3. **P0/P1 luôn được ưu tiên hơn ambience/music.**
4. **Swarm không được tạo audio spam theo từng unit.**
5. **Audio phải thuộc visual family của Friendly Post-Apocalypse.**
6. **Không biến game thành military simulator, horror game hoặc generic fantasy RPG bằng sound design.**
7. **Boss được phép lớn nhưng không được che telegraph.**
8. **Warning có hierarchy; không phải mọi warning đều là emergency.**
9. **Outpost phải nghe như một nơi đang sống và đang được xây dựng lại.**
10. **Production scope phải tăng theo gameplay value, không theo số lượng asset.**

---

# 39. FINAL AUDIO PRINCIPLE

> **LAST OUTPOST should sound alive enough to invite the player in, tactile enough to make every action feel physical, and clear enough that important tactical information can be understood without staring at the UI.**

Audio không phải lớp phủ lên gameplay.

Audio là một phần của gameplay readability, world identity và cảm giác progression.

---

# 40. HANDOFF STATUS

**Status:** Ready for cross-reference with:

- Core Combat Gameplay
- System & Gameplay
- Content Specification
- Production Content Bible
- Vertical Slice Specification
- Visual Identity / Art Direction
- Playtest & Balance
- Content Production Pack
- Claude Handoff

**Next planned document:**

> **LAST OUTPOST — ASSET PRODUCTION SPECIFICATION v1.0**

Mục tiêu là biến Visual Reference + Audio Direction + Gameplay requirements thành production specification cho Character, Commander, Enemy, Tower, Base, Barrel, Module, Boss, Building, Outpost, Environment, Prop, UI Asset, VFX, Animation, Portrait và Cosmetic.
