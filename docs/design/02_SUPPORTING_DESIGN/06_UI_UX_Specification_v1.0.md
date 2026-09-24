# LAST OUTPOST — UI/UX SPECIFICATION v1.0

> **Vai trò tài liệu:** đặc tả giao diện và tương tác, nằm trên `Last_Outpost_Player_Experience_UX_Flow_v1.0`.
>
> **Mục tiêu:** chuyển Player Experience/UX Flow thành các màn hình, component, trạng thái, interaction, animation, feedback và quy tắc responsive có thể giao cho UI/UX implementation.
>
> **Nguyên tắc kế thừa:** giữ nguyên các quyết định đã khóa trong GDD/System/Gameplay/Content/Economy/UX Flow. Các kích thước, khoảng cách, timing và giá trị visual trong tài liệu này là **đề xuất prototype**, cần playtest trước khi khóa.

---

# 0. UI/UX NORTH STAR

## 0.1. Người chơi phải luôn hiểu

1. **Tôi đang ở đâu?**
2. **Tôi đang làm gì?**
3. **Điều gì quan trọng nhất lúc này?**
4. **Tôi có thể hành động ngay ở đâu?**

## 0.2. Visual pillars

### A. Tactical Readability
Thông tin chiến đấu phải đọc được ngay cả khi battlefield có rất nhiều enemy.

### B. Mobile First
Mọi thao tác quan trọng phải thân thiện với màn hình cảm ứng và vùng ngón tay cái.

### C. Layered Information
Thông tin cơ bản nằm ngoài; thông tin chi tiết chỉ xuất hiện khi inspect.

### D. Strong Feedback
Mọi action quan trọng đều có phản hồi trực quan + âm thanh phù hợp.

### E. Persistent World
Outpost phải có cảm giác là một địa điểm đang phát triển, không phải menu phẳng.

### F. Low Friction
Không bắt người chơi mở nhiều màn hình để thực hiện một hành động đơn giản.

---

# I. THIẾT KẾ VISUAL SYSTEM

## 1.1. Phong cách

- 2.5D Isometric.
- Post-apocalyptic nhưng không quá tối đến mức khó đọc.
- Hand-drawn/Pixel-art high detail theo hướng GDD.
- UI hiện đại, sạch, không làm mất cảm giác vật lý của battlefield.
- Rounded panel vừa phải; tránh biến toàn bộ game thành app dashboard.

## 1.2. Màu chức năng

Màu phải biểu thị **trạng thái**, không chỉ để trang trí:

| Semantic | Vai trò |
|---|---|
| Success | Đã hoàn thành / hợp lệ / an toàn |
| Warning | Cần chú ý |
| Critical | Nguy hiểm / sắp thất bại |
| Info | Thông tin trung tính |
| Neutral | UI nền / navigation |

Không phụ thuộc riêng vào màu; trạng thái quan trọng phải có icon/shape/text đi kèm.

## 1.3. Typography

Hierarchy đề xuất:

- Display: Mission title / major result.
- Heading: Section / panel title.
- Body: Description.
- Caption: Secondary information.
- Numeric: Resource/count/stat.

Số liệu combat quan trọng dùng typography dễ đọc, font weight cao hơn body.

## 1.4. Iconography

Icon phải có:
- silhouette rõ;
- trạng thái enabled/disabled;
- selected/unselected;
- không dùng icon duy nhất để truyền thông tin quan trọng.

---

# II. RESPONSIVE & TOUCH SPEC

## 2.1. Touch target

> Giá trị prototype đề xuất: action chính tối thiểu khoảng **48dp**, action combat có thể lớn hơn tùy ngữ cảnh.

## 2.2. Safe zones

Không đặt action quan trọng sát:
- cạnh màn hình;
- vùng gesture navigation;
- vùng dễ bị che bởi tay khi xoay map.

## 2.3. Thumb zones

Combat controls ưu tiên vùng thấp màn hình.
Các hành động ít dùng có thể nằm ở góc trên hoặc radial/menu phụ.

## 2.4. Orientation

Prototype ưu tiên một orientation cố định trong combat để tránh thay đổi layout giữa trận. Nếu hỗ trợ orientation bổ sung trong tương lai, phải có layout riêng thay vì scale cứng.

---

# III. APP SHELL & NAVIGATION

## 3.1. Navigation cấp 1

- Outpost
- Campaign
- Expedition
- Challenges / Events
- Profile / Collection

Shop không bắt buộc có tab riêng.

## 3.2. Persistent top status

Ở các màn ngoài combat có thể hiển thị:
- account/profile;
- premium currency;
- notification indicator;
- quick return về Outpost.

Không hiển thị mọi resource ở mọi màn nếu không liên quan.

## 3.3. Back behavior

Back phải có logic rõ:

`Subscreen → Parent → Root`

Trong combat:
- Back mở pause/confirm;
- không thoát trận ngay.

---

# IV. OUTPOST HOME UI

## 4.1. Mục tiêu

Outpost phải cho cảm giác **đang sống và đang phát triển**.

## 4.2. Layout đề xuất

```text
┌──────────────────────────────────┐
│ Profile     Alerts      Currency │
├──────────────────────────────────┤
│                                  │
│        2.5D OUTPOST              │
│                                  │
│   [Command] [Research]           │
│        [Workshop]                │
│   [Habitat]   [Logistics]        │
│                                  │
├──────────────────────────────────┤
│ NEXT ACTION / CAMPAIGN CTA       │
├──────────────────────────────────┤
│ Outpost | Campaign | More...     │
└──────────────────────────────────┘
```

## 4.3. Building interaction

Tap building:
- short highlight;
- building name;
- current status;
- primary action;
- upgrade/progression preview.

Hold/drag nếu dùng cho build placement hoặc camera navigation.

## 4.4. Next Best Action card

Chỉ nên nổi bật **một action chính**.
Ví dụ:

> `MISSION 18 READY`
> `EMP Stalker detected`
> `[VIEW MISSION]`

Hoặc:

> `RESEARCH COMPLETE`
> `[OPEN RESEARCH]`

---

# V. CAMPAIGN MAP UI

## 5.1. Hierarchy

`Region → Chapter → Mission`

## 5.2. Mission node

Node có các state:

- Locked
- Available
- In Progress
- Completed
- Perfect/High Score
- Boss
- Challenge
- Event

## 5.3. Mission card

Hiển thị:
- tên;
- biome;
- roster summary;
- objective chính;
- reward chính;
- best result.

Không hiển thị quá nhiều stat trước khi người chơi yêu cầu.

---

# VI. MISSION BRIEFING UI

## 6.1. Layout

```text
TITLE
BIOME / THREAT

ENEMY ROSTER
[ ][ ][ ][ ]

PRIMARY OBJECTIVE
SECONDARY CHALLENGE

KNOWN HAZARD

RECOMMENDED PREP

[LOADOUT]           [START]
```

## 6.2. Roster card

Tap enemy card → Enemy Intel.

Enemy Intel phải trả lời:
- Role.
- Threat.
- Signature ability.
- Recommended counter.
- Đã gặp/chưa gặp.

## 6.3. Unknown enemy

Không spoil toàn bộ hành vi trước khi gặp.
Hiển thị cách mở Intel thay vì placeholder vô nghĩa.

---

# VII. LOADOUT UI

## 7.1. Thành phần

- Tower slots.
- Base options.
- Barrel options.
- Module options.
- Commander.
- Tactical slots.
- Saved presets.

## 7.2. Tower composition preview

Khi chọn tower:

`BASE + BARREL + MODULE`

→ preview stat changes và synergy.

Không chỉ hiển thị số tổng; phải cho thấy **thay đổi nào đến từ module nào**.

## 7.3. Build validation

Nếu loadout có vấn đề:

> `Thiếu counter cho heavy armor.`

Nhưng không chặn Start nếu game design cho phép tự thử.

## 7.4. Preset

Có thể:
- Save;
- Rename;
- Duplicate;
- Delete;
- Set as default.

---

# VIII. PREPARE PHASE

## 8.1. Goal

Cho người chơi chuẩn bị trong khoảng ngắn, không tạo cảm giác “đang chỉnh spreadsheet”.

## 8.2. Top status

- Base HP.
- Scrap/budget nếu áp dụng.
- Ammo.
- Power.
- Water.
- Wave preview.

## 8.3. Build placement

### Valid
Highlight hợp lệ.

### Soft Warning
Có thể đặt nhưng có trade-off.

### Invalid
Không thể đặt + lý do.

## 8.4. Tower inspect

Hiển thị:
- Damage.
- Range.
- Fire rate.
- Heat.
- Ammo state.
- Module effects.
- Targeting mode.
- Current condition.

Detailed stats mở bằng expand/inspect.

---

# IX. COMBAT HUD SPECIFICATION

## 9.1. HUD hierarchy

### Layer 1 — Always visible
- Base HP.
- Wave/progress.
- Primary resources.
- Tactical skills.
- Pause.

### Layer 2 — Contextual
- Selected tower.
- Selected enemy.
- Range.
- Target priority.
- Repair/resupply action.

### Layer 3 — Inspect
- Detailed stats.
- Full effect descriptions.
- Logs.

## 9.2. Recommended mobile layout

```text
┌──────────────────────────────────┐
│ BASE HP     WAVE 06/18      PAUSE│
│ Scrap  Ammo  Power  Water        │
├──────────────────────────────────┤
│                                  │
│          BATTLEFIELD             │
│                                  │
│    [selected tower overlay]      │
│                                  │
├──────────────────────────────────┤
│ Commander Skill   Tactical Skill │
│   [1] [2] [3]       [A] [B]      │
└──────────────────────────────────┘
```

## 9.3. Camera

Camera controls:
- pan;
- zoom;
- optional recenter on threat;
- optional recenter on selected entity.

Không auto-pan liên tục.

---

# X. BUILD / TOWER INTERACTION

## 10.1. Placement flow

```text
Tap Build
↓
Select Tower
↓
Drag/Move Ghost
↓
Placement Feedback
↓
Release
↓
Confirm
↓
Construction Feedback
```

## 10.2. Selected Tower radial / contextual menu

Actions ưu tiên:
1. Targeting.
2. Repair.
3. Resupply.
4. Module interaction.
5. Sell/Remove nếu được phép.
6. Inspect.

Không đưa mọi action vào một menu dài.

## 10.3. Targeting modes

Ví dụ:
- First.
- Last.
- Closest.
- Strongest.
- Special priority.

Mode đang chọn phải nhìn thấy ngay.

---

# XI. RESOURCE & LOGISTICS UI

## 11.1. Resource strip

Resource icon + count + trend nếu hữu ích.

Trend chỉ xuất hiện khi người chơi cần biết:
- tăng;
- giảm;
- đang thiếu;
- sắp cạn.

## 11.2. Logistics line

Khi chọn Kho Đạn/Drone/Route:

```text
SUPPLY SOURCE
     ↓
DRONE / ROUTE
     ↓
TOWER
```

Tuyến đang hoạt động có visual pulse nhẹ.
Tuyến bị chặn chuyển sang trạng thái warning.

## 11.3. Actionable shortage

Không chỉ:

> `Ammo: 0`

mà:

> `Ammo depleted`
> `3 towers affected`
> `[OPEN LOGISTICS]`

---

# XII. HEAT / WATER / POWER FEEDBACK

## 12.1. Heat

Thanh nhiệt:
- normal;
- elevated;
- critical;
- jam/overheat state.

Heat phải có visual escalation nhưng không flash liên tục.

## 12.2. Power

Power outage phải hiển thị:
- phạm vi ảnh hưởng;
- hệ thống nào tắt;
- cách khôi phục nếu có.

## 12.3. Water

Water được nhấn mạnh khi vừa có vai trò dân sinh vừa có vai trò cooling.

Không tạo hai hệ thống UI riêng nếu chúng dùng chung một resource.

---

# XIII. ENEMY & THREAT FEEDBACK

## 13.1. Enemy silhouette

Mỗi enemy type cần có silhouette/animation đủ khác để nhận biết nhanh.

## 13.2. Special enemy warning

Sequence:

`Forecast → Spawn → On-field → Ability → Defeat`

Ví dụ EMP Stalker:

> Warning icon
> → distinct audio
> → silhouette highlight
> → EMP radius preview when relevant

## 13.3. Threat marker

Chỉ đánh dấu enemy thật sự cần chú ý.
Không tạo marker cho toàn bộ swarm.

## 13.4. Boss presentation

Boss entrance có:
- camera emphasis ngắn;
- nameplate;
- health bar;
- signature ability indicator.

Không khóa camera quá lâu làm mất quyền điều khiển.

---

# XIV. WAVE UI & PACING

## 14.1. Wave banner

Khi wave bắt đầu:

> `WAVE 07`
> `Crawler × 16`
> `Behemoth × 1`

Hiển thị ngắn rồi tự thu gọn.

## 14.2. Between-wave cue

Countdown ngắn + threat preview.

Người chơi có thể:
- sửa;
- reload/resupply;
- thay targeting;
- dùng tactical prep.

## 14.3. Critical wave

Wave có threat spike có visual treatment riêng, nhưng vẫn phải đọc được battlefield.

---

# XV. ALERT SYSTEM UI

## 15.1. Priority model

**Critical > Important > Informational**

## 15.2. Alert anatomy

Mỗi alert:

`ICON + WHAT + WHY + ACTION`

Ví dụ:

> ⚠ `3 towers thiếu đạn`
> `Supply route bị EMP làm gián đoạn`
> `[VIEW ROUTE]`

## 15.3. Alert stacking

Maximum một alert layer nổi bật tại một thời điểm.
Các alert tương tự được nhóm.

## 15.4. Alert history

Có một panel history tùy chọn để người chơi xem:
- event đã xảy ra;
- event đang active;
- event đã xử lý.

Không dùng history làm nơi chứa thông tin mà người chơi buộc phải đọc.

---

# XVI. TACTICAL ABILITY UI

## 16.1. Ability button

Mỗi button hiển thị:
- icon;
- cooldown/charges;
- unavailable state;
- active state.

## 16.2. Preview

AoE/skill có thể có:
- area preview;
- trajectory;
- affected target preview.

Preview phải biến mất ngay khi cancel.

## 16.3. Emergency skill

Skill cứu nguy cần có visual/audio đủ rõ, nhưng không được che battlefield.

---

# XVII. HERO / COMMANDER UI

## 17.1. Hero card

Hiển thị:
- portrait;
- role;
- current state;
- primary skill;
- secondary skill;
- availability.

## 17.2. In-battle commander state

Commander position có marker nhẹ.
Skill button chỉ xuất hiện khi skill usable.

## 17.3. Commander feedback

Skill thành công → hit/result feedback rõ.
Skill thất bại/không hợp lệ → giải thích nguyên nhân ngắn.

---

# XVIII. MISSION RESULT UI

## 18.1. First result frame

```text
VICTORY / DEFEAT

★★★

Base HP Remaining
Waves Cleared
Primary Objective

[CONTINUE]
```

## 18.2. Expandable details

Sau lớp đầu:
- resource earned;
- blueprint fragments;
- mastery progress;
- challenge progress;
- tactical summary;
- replay metrics.

## 18.3. Defeat screen

Không đổ lỗi chung chung.
Hiển thị:

> `Đợt 14 thất bại`
> `Nguyên nhân chính: tuyến Ammo bị ngắt 27 giây`
> `[VIEW BATTLE SUMMARY]`

---

# XIX. PROGRESSION UI

## 19.1. Outpost progression

Hiển thị:
- current stage;
- next milestone;
- requirements;
- visual preview.

## 19.2. Tech Tree

Node có state:
- Locked.
- Available.
- Researching.
- Completed.
- Optional/Branch.

Tap node:
- benefit;
- cost;
- prerequisites;
- gameplay impact.

## 19.3. Mastery

Progress bar + milestone rewards.
Ưu tiên cosmetic/achievement feedback.

## 19.4. Blueprint

`Fragment progress → Blueprint complete → Craft/Unlock`

Phải luôn cho người chơi biết còn bao nhiêu thiếu.

---

# XX. ECONOMY / SHOP UI

## 20.1. Shop principles

Không dùng dark pattern.
Không tự mở purchase sheet.
Không che nút đóng.
Giá phải hiển thị rõ.

## 20.2. Catalog hierarchy

1. Featured.
2. Cosmetics.
3. Utility/QoL.
4. Plus.
5. Pro.
6. Events/Seasonal.

## 20.3. Plus / Pro presentation

Mỗi plan card phải có:
- giá;
- benefits;
- giới hạn;
- cách hoạt động trong PvE/PvP;
- trạng thái hiện tại.

Không dùng từ ngữ gây cảm giác “thắng tuyệt đối”.

## 20.4. Cosmetic preview

Cho phép:
- preview tower;
- base theme;
- VFX;
- commander skin.

Preview trên battlefield/outpost thực tế tốt hơn ảnh tĩnh.

---

# XXI. ACCESSIBILITY

## 21.1. Color accessibility

Không truyền Critical chỉ bằng đỏ.
Dùng icon + shape + text.

## 21.2. Motion

Có option:
- reduced motion;
- giảm camera shake;
- giảm screen flash.

## 21.3. Audio

Có:
- master;
- music;
- SFX;
- voice/announcer;
- alert volume.

## 21.4. Text

Hỗ trợ tăng kích thước chữ ở các menu phù hợp mà không làm vỡ hierarchy.

---

# XXII. AUDIO/FEEDBACK COUPLING

UI state quan trọng nên liên kết với audio state:

| UI State | Audio | Visual |
|---|---|---|
| Wave start | Short cue | Banner |
| Critical threat | Alert cue | Critical marker |
| Low ammo | Low-resource cue | Resource pulse |
| Overheat | Heat warning | Heat escalation |
| Victory | Completion cue | Result transition |
| Defeat | Failure cue | Result transition |

Audio không được trở thành spam; cùng một alert lặp lại cần cooldown/aggregation.

---

# XXIII. TRANSITIONS & MOTION

## 23.1. Transition classes

### Micro
Tap, hover/select, state switch.

### Context
Open/close panel, inspect, tooltip.

### Major
Enter battle, victory, defeat, outpost evolution.

## 23.2. Motion rules

- Motion phải giải thích thay đổi state.
- Không dùng animation chỉ để “cho đẹp” nếu làm chậm thao tác.
- Critical feedback nhanh và rõ.
- Reward animation có thể celebratory nhưng phải skip được.

## 23.3. Suggested timing

> Các timing dưới đây là prototype values, cần playtest.

- Micro feedback: ~100–180 ms.
- Panel transition: ~180–280 ms.
- Major transition: ~300–600 ms.

---

# XXIV. POPUP / MODAL POLICY

## 24.1. Chỉ dùng modal khi

- confirmation quan trọng;
- irreversible action;
- purchase;
- leaving active combat;
- major reward/unlock nếu cần attention.

## 24.2. Không dùng modal cho

- thông báo thường;
- mỗi resource nhận được;
- mỗi level up nhỏ;
- mọi alert combat.

---

# XXV. EMPTY / ERROR / LOADING STATES

## 25.1. Empty state

Không chỉ hiển thị “Nothing here”.
Phải có:
- trạng thái;
- lý do nếu rõ;
- action tiếp theo.

## 25.2. Error state

Format:

`What happened → What can I do`

## 25.3. Loading

Ưu tiên skeleton/partial content.
Không để spinner vô hạn không có context.

---

# XXVI. SAVE / RECOVERY UX

## 26.1. Autosave indicator

Ngoài combat có thể hiển thị rất nhẹ:
> `Saved`

Không spam.

## 26.2. Reconnect / recovery

Nếu app bị đóng giữa trận:
- resume nếu state hợp lệ;
- otherwise show clear recovery result.

## 26.3. Data conflict

Nếu có cloud/local mismatch:
- không tự âm thầm ghi đè;
- hiển thị lựa chọn có giải thích rõ.

---

# XXVII. NOTIFICATION UX

Notification chỉ dùng cho nội dung đáng quay lại:
- Expedition hoàn tất.
- Research hoàn tất.
- Event sắp kết thúc.
- Reward sắp hết hạn nếu có.

Không spam để kéo người chơi quay lại liên tục.

---

# XXVIII. TUTORIAL UX

## 28.1. Teaching loop

`Show → Let player act → Confirm → Let player repeat → Remove guidance`

## 28.2. Hướng dẫn contextual

Ví dụ khi first time gặp heat:

> `Tower đang tích nhiệt.`
> `Giảm nhiệt hoặc ngừng bắn để tránh Jam.`

Sau khi hiểu, hint không xuất hiện lại thường xuyên.

## 28.3. Tutorial skip

Người chơi đã hoàn thành tutorial profile trước đó có thể skip các đoạn hướng dẫn lặp lại.

---

# XXIX. PLAYER FEEDBACK & TELEMETRY HOOKS

UI event nên có event tracking tương ứng:

- screen_open;
- mission_start;
- mission_abandon;
- tower_place;
- tower_remove;
- module_change;
- alert_shown;
- alert_action;
- mission_result;
- reward_claim;
- shop_view;
- purchase_start;
- purchase_complete;
- progression_unlock.

Không tracking dữ liệu vượt quá nhu cầu sản phẩm.

---

# XXX. UI QUALITY GATES

## 30.1. Core readability

- Mission objective đọc được trong vài giây.
- Current threat nhìn thấy không cần mở menu.
- Resource critical state dễ nhận biết.
- Selected tower có state rõ.
- Enemy special ability có telegraph.

## 30.2. Interaction quality

- Không action quan trọng nào cần nhiều tap không cần thiết.
- Không có invisible button.
- Back behavior nhất quán.
- Touch target đủ lớn.

## 30.3. Combat quality

- HUD không che battlefield quan trọng.
- Alert không che mục tiêu.
- Camera không giật quá mức.
- VFX không làm mất silhouette enemy.

## 30.4. Monetization quality

- Giá rõ.
- Benefits rõ.
- Không misleading.
- Không ép mua để hoàn thành core content.

---

# XXXI. COMPONENT CHECKLIST

Mỗi component UI khi implementation phải có:

- Default.
- Pressed.
- Disabled.
- Selected.
- Focused nếu phù hợp.
- Loading nếu có.
- Success.
- Warning.
- Critical nếu có.
- Error nếu có.

---

# XXXII. UI SCREEN INVENTORY

## Core

1. Splash / First Launch
2. Onboarding
3. Outpost Home
4. Campaign Map
5. Chapter Overview
6. Mission Briefing
7. Loadout
8. Prepare
9. Combat
10. Pause
11. Mission Result

## Progression

12. Command Center
13. Tech Tree
14. Workshop
15. Blueprint Inventory
16. Mastery
17. Commander
18. Collection
19. Expedition

## Live Content

20. Challenges
21. Seasonal Event
22. World Event
23. Guild
24. PvP Raid
25. Leaderboard

## Economy

26. Shop
27. Plus
28. Pro
29. Cosmetic Preview
30. Purchase Confirmation
31. Reward Track / Pass

## Account

32. Profile
33. Settings
34. Audio
35. Accessibility
36. Notifications
37. Save/Cloud Status

---

# XXXIII. IMPLEMENTATION ORDER

## P0 — Prototype

- Outpost Home
- Campaign Map
- Briefing
- Loadout
- Prepare
- Combat HUD
- Tower Inspect
- Enemy Info
- Result

## P1 — Core progression

- Tech Tree
- Workshop
- Blueprint
- Mastery
- Expedition

## P2 — Live systems

- Challenges
- Events
- Guild
- PvP
- Leaderboard

## P3 — Economy / polish

- Shop
- Plus/Pro
- Pass
- Cosmetic Preview
- Advanced motion

---

# XXXIV. DEFINITION OF DONE

UI/UX được coi là đạt prototype khi:

- Người chơi mới có thể vào tutorial và hiểu core loop bằng hành động.
- Có thể đi từ Outpost → Mission → Combat → Result mà không bị lạc.
- Trong combat, threat chính được nhận biết nhanh.
- Resource shortage có action rõ ràng.
- Tower/module selection dễ hiểu.
- Mission result cho thấy tại sao người chơi thắng/thua.
- Progression cho thấy bước tiếp theo.
- Shop/Plus/Pro rõ ràng và không gây hiểu lầm.
- UI không che khuất gameplay ở các tình huống quan trọng.
- Toàn bộ flow hoạt động ổn định trên thiết bị mobile mục tiêu.

---

# XXXV. HANDOFF RULE

UI/UX Designer, Game Designer và Developer khi implementation phải dùng cùng một thuật ngữ:

`Tower / Base / Barrel / Module / Commander / Roster / Wave / Threat / Scrap / Ammo / Power / Water / Biomass / Blueprint / Mastery / Outpost / Plus / Pro`

Không tự tạo tên mới cho cùng một khái niệm nếu chưa cập nhật GDD/System Design.

---

# FINAL PRINCIPLE

> **UI không được làm game đơn giản đi. UI phải làm chiều sâu của game trở nên dễ đọc.**

Người chơi có thể phải đối mặt với hàng trăm enemy, nhiều Tower, logistics, heat, power, water, hazards và tactical decisions; nhưng tại mỗi thời điểm, màn hình chỉ nên yêu cầu họ hiểu **điều quan trọng nhất tiếp theo** và cho họ một cách rõ ràng để hành động.
