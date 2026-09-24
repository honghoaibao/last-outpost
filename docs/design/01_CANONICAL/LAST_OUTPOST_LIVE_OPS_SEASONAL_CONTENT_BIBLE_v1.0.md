# LAST OUTPOST — LIVE OPS & SEASONAL CONTENT BIBLE v1.0

**Version:** v1.0  
**Status:** Production Design / Proposed where explicitly marked  
**Language:** Vietnamese  
**Scope:** Live Ops, Daily/Weekly/Seasonal Content, World Events, Event Economy, Rotation, New Player, Returning Player, Comeback, Reward Cadence, Season Rollover

> **Important:** Tài liệu này không thay thế GDD, System & Gameplay, Economy, Content Production, Progression hoặc Claude Handoff. Đây là lớp thiết kế Live Ops/Seasonal nằm trên các hệ thống đã được chốt.

---

## 0. MỤC ĐÍCH

LAST OUTPOST cần có nhịp nội dung đủ để người chơi có lý do quay lại, nhưng không biến trải nghiệm thành một danh sách công việc hằng ngày bắt buộc.

Live Ops phải:

- kéo dài tuổi thọ PvE core;
- tạo biến thể gameplay có ý nghĩa;
- tạo mục tiêu ngắn hạn và trung hạn;
- cung cấp cosmetic/event progression;
- hỗ trợ Mastery, Blueprint và Expedition;
- giúp người chơi cũ quay lại mà không cảm thấy bị bỏ quá xa;
- tạo điểm vào rõ ràng cho người chơi mới;
- không phá Persistent Outpost;
- không tạo FOMO cực đoan;
- không yêu cầu người chơi đăng nhập liên tục để giữ sức mạnh đã kiếm được.

### Live Ops Principle

> **Thêm nhịp chơi, không thêm gánh nặng.**

---

# I. QUY TẮC KHÔNG ĐƯỢC VI PHẠM

## 1. Core gameplay không đổi vì Live Ops

Live Ops phải phục vụ:

**BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN**

Không được biến event/season thành một gameplay loop độc lập làm người chơi phải học lại toàn bộ game.

## 2. Outpost là Persistent

Theo Current Decisions:

- Outpost không reset theo Season thông thường.
- Tech Tree permanent không reset.
- Mastery đã kiếm được không reset.
- Blueprint đã mở không mất.
- Tower/Commander đã sở hữu không bị thu hồi.
- Permanent content progression không bị xoá để tạo áp lực mua hoặc chơi lại.

## 3. Fixed Enemy Roster vẫn áp dụng

Season/Event có thể thay đổi:

- timing;
- spawn pattern;
- modifier;
- terrain pressure;
- objective;
- resource pressure;

nhưng không được tự ý đưa enemy species ngoài **Fixed Roster** của mission/event configuration.

## 4. Event không được thành game mode hoàn toàn mới

Ưu tiên:

> Existing Mission + New Modifier + New Objective + New Reward Layer

thay vì:

> New Ruleset + New Economy + New Controls + New Progression

## 5. Reward phải có giá trị rõ ràng

Mỗi hoạt động Live Ops nên trả về ít nhất một trong các giá trị:

- resource;
- Blueprint Fragment;
- Mastery XP;
- Event Token;
- Cosmetic;
- Seasonal Pass progression.

Không thêm loại currency mới chỉ để tạo thêm một thanh tiến độ.

---

# II. LIVE OPS CONTENT HIERARCHY

```text
LIVE OPS
├── Daily
├── Weekly
├── Monthly / Cadence Checkpoint
├── World Event
├── Seasonal Content
│   ├── Seasonal Missions
│   ├── Seasonal Challenges
│   ├── Seasonal Pass
│   └── Seasonal Cosmetics
├── Guild / Social Events
└── Return / Comeback Support
```

### Ownership

| Layer | Vai trò |
|---|---|
| Daily | Mục tiêu ngắn hạn, nhẹ |
| Weekly | Challenge và mastery dài hơn |
| Monthly | Nhịp review/cadence, không bắt buộc là currency layer mới |
| World Event | Biến thể gameplay ngắn 24–72h |
| Season | Chủ đề và progression dài hạn |
| Guild Event | Social/PvE secondary layer |
| Comeback | Đưa người chơi cũ trở lại core loop |

---

# III. CADENCE MODEL

## 1. Daily

Current design đã chốt:

- 1–3 mục tiêu nhỏ.
- Reward vừa phải.
- Không tạo FOMO quá nặng.

### Daily phải đáp ứng

- hoàn thành nhanh;
- có thể hoàn thành thông qua gameplay bình thường;
- không ép một build duy nhất liên tục;
- không yêu cầu đăng nhập nhiều lần trong ngày.

### Ví dụ

- Hoàn thành 1 mission.
- Hoàn thành 1 mission bằng Mechanical Tower.
- Dùng 2 Tower khác nhau.
- Hoàn thành một objective phụ.
- Xử lý một số lượng enemy thuộc roster hiện có.

### Không nên

- “Đăng nhập 3 lần.”
- “Online liên tục 30 phút.”
- nhiệm vụ yêu cầu phải chơi đúng giờ.
- nhiệm vụ khiến Campaign progression bị bỏ lại nếu người chơi không làm.

---

## 2. Weekly

Weekly là lớp challenge trung hạn.

Current design đã có ví dụ:

> Enemy nhanh hơn 30%, đổi lại Scrap ×2.

### Weekly có thể dùng Seed

Một Weekly Challenge có thể sử dụng một cấu hình seed để người chơi cùng đối mặt với cùng một bài toán.

Seed chỉ là công cụ tạo tính nhất quán cho challenge, không phải RNG mới của core campaign.

### Weekly Reward ưu tiên

- Blueprint Fragment;
- Cosmetic;
- Mastery XP;
- resource;
- Seasonal/Event progression khi phù hợp.

### Weekly không nên

- reset permanent progression;
- khóa core Tower;
- yêu cầu hoàn thành mỗi ngày mới đủ reward;
- tạo chênh lệch power trực tiếp theo mức chi tiền.

---

## 3. Monthly / Cadence Checkpoint

### Trạng thái

**ĐỀ XUẤT MỚI — chưa phải một hệ thống currency riêng.**

Monthly nên là một checkpoint vận hành thay vì tạo thêm một daily/weekly layer.

Có thể dùng để:

- kiểm tra content health;
- refresh featured challenge;
- giới thiệu một mini-event;
- rotate cosmetic spotlight;
- phát hành content update;
- review reward pacing.

Không bắt buộc phải có “Monthly Quest” nếu không tạo thêm giá trị gameplay.

---

# IV. SEASON MODEL

## 1. Season Purpose

Season là lớp theme + challenge + reward, không phải reset game.

Season nên tạo:

- chủ đề hình ảnh mới;
- modifier gameplay nhẹ;
- challenge mới;
- seasonal missions;
- cosmetic;
- event layer;
- Pass progression.

## 2. Season Duration

**ĐỀ XUẤT MỚI — prototype target:**

> **6 tuần / Season**

Khoảng 6 tuần đủ dài để season có nội dung nhưng không tạo áp lực sản xuất quá lớn.

Đây là prototype target, chưa phải quyết định bất biến. Có thể điều chỉnh sau khi có retention/content production data.

## 3. Season Structure

```text
Season Launch
    ↓
Theme Introduction
    ↓
Seasonal Missions / Challenges
    ↓
World Event Rotation
    ↓
Milestone Rewards
    ↓
Final Challenge Window
    ↓
Season Rollover
```

## 4. Season không được reset

Không reset:

- Outpost;
- Tech Tree;
- Mastery;
- Blueprint đã mở;
- owned Tower;
- owned Commander;
- permanent resources/progression;
- campaign unlock.

---

# V. SEASONAL CONTENT TYPES

## 1. Seasonal Mission

Mission hiện có +:

- seasonal modifier;
- objective variation;
- special reward profile;
- visual theme.

Không cần map system mới nếu map hiện tại đã đủ diễn đạt theme.

## 2. Seasonal Challenge

Mục tiêu khó hơn Campaign bình thường.

Có thể dùng:

- roster constraint;
- Tower restriction;
- resource pressure;
- weather/hazard;
- time pressure;
- objective mutation.

Difficulty phải đến từ **decision**, không chỉ tăng HP/damage.

## 3. Seasonal Cosmetic

Ưu tiên:

- Commander Skin;
- Tower Skin;
- Base Theme;
- Supply Truck Skin;
- Drone Skin;
- VFX;
- Profile Frame/Badge;
- Music Pack.

Không đặt core Tower hoặc power spike độc quyền vào Premium Season Track.

---

# VI. WORLD EVENT

## 1. Đã chốt

World Event là event ngắn:

> **24–72 giờ**

Ví dụ đã có:

- Scrap Meteor Shower;
- Extreme Heat;
- Spore Season;
- Frozen Supply Route.

## 2. Event Minimum Package

Mỗi event phải có:

1. Theme.
2. Một gameplay modifier chính.
3. Một mục tiêu chính.
4. Một reward group.
5. Visual identity đủ nhận diện.
6. Start/end time rõ ràng.

## 3. Event Loop

```text
Enter
→ Discover
→ Play
→ Earn Event Currency
→ Spend
→ Unlock
→ Complete
```

## 4. Event phải thay đổi gameplay vừa đủ

Ví dụ:

### Scrap Meteor Shower
- Scrap xuất hiện theo các đợt đặc biệt.
- Người chơi phải quyết định có mạo hiểm thu thập hay giữ vị trí phòng thủ.

### Extreme Heat
- Heat pressure tăng.
- Cooling/Power management quan trọng hơn.
- Water trở thành tactical consideration.

### Spore Season
- Spore-related hazard xuất hiện theo cấu hình event.
- Visibility / movement / threat management thay đổi.

### Frozen Supply Route
- Supply route bị hạn chế.
- Logistics trở thành vấn đề trung tâm.

Đây là ví dụ thiết kế, không tự động khóa toàn bộ numerical values.

---

# VII. EVENT ECONOMY

## 1. Event Token

Giữ đúng Economy Design:

- Event Token có thể tồn tại riêng cho từng event.
- Token có thời hạn theo event.
- Token không cần tồn tại vĩnh viễn trong economy.
- Dùng để đổi cosmetic/reward event.

## 2. Event Token Flow

```text
Play Event
    ↓
Earn Event Token
    ↓
View Event Shop
    ↓
Choose Reward
    ↓
Spend Token
    ↓
Claim / Unlock
```

## 3. Event Shop

### Reward Tiers đề xuất

**Core Event Rewards**
- Blueprint Fragment;
- Mastery XP;
- resource.

**Cosmetic Event Rewards**
- Tower Skin;
- Commander Skin;
- Base Theme;
- Profile Cosmetic;
- VFX/Audio Pack.

### Quy tắc

- Không bán core power độc quyền.
- Không dùng token để mở permanent power ngoài normal gameplay path nếu gây mất cân bằng.
- Reward quan trọng nên hiển thị tổng progress để người chơi biết còn bao nhiêu.

## 4. Event Currency Expiry

Khi event kết thúc:

- Event Token ngừng kiếm.
- Event Shop chuyển sang closed/archive state.
- Token hết giá trị theo thiết kế event.

**ĐỀ XUẤT UX:** hiển thị countdown + nhắc thời điểm hết hạn trước khi event đóng, nhưng không spam notification.

---

# VIII. EVENT REWARD PACING

## Reward order

```text
Participation
→ Progress
→ Milestone
→ Completion
→ Optional Mastery
```

Người chơi phải nhận được một tín hiệu tiến bộ tương đối sớm.

Không thiết kế event mà reward có giá trị chỉ xuất hiện ở 99–100% progress.

## Guaranteed vs Optional

### Guaranteed
- participation reward nhỏ;
- milestone;
- một số baseline resource.

### Optional / Advanced
- cosmetic cao cấp;
- mastery challenge;
- completion badge.

Điều này giúp người chơi mới có thể tham gia mà không cần hoàn thành event 100%.

---

# IX. SEASON PASS

Current Economy/Optimized design đã có:

### Free Track

- resource;
- Blueprint Fragment;
- Mastery XP;
- cosmetics cơ bản.

### Premium Track

- exclusive skins;
- Base Theme;
- VFX;
- Profile Frame;
- emote;
- premium currency.

### Không được

- Tower mạnh độc quyền;
- permanent power spike độc quyền Premium;
- core campaign gate;
- PvP stat advantage không được normalize.

---

# X. NEW PLAYER EXPERIENCE TRONG LIVE OPS

## 1. New Player không bị đẩy vào live content quá sớm

Người chơi mới trước tiên cần:

```text
Onboarding
→ Core Tutorial
→ Early Campaign
→ Outpost Growth
→ First Meaningful Build
→ First Challenge
→ Live Ops Introduction
```

Live Ops nên xuất hiện sau khi người chơi đã hiểu core loop.

## 2. New Player Catch-Up

Khi một Season đang ở giữa chu kỳ:

**ĐỀ XUẤT MỚI:** người chơi mới được cung cấp một **Season Starter Track** đơn giản hóa.

Mục tiêu:

- giới thiệu reward path hiện tại;
- bỏ qua các milestone không còn phù hợp;
- không yêu cầu farm bù toàn bộ tiến độ người chơi cũ.

Không cho phép catch-up phá progression pacing của core game.

## 3. Missing Previous Event

Không nên làm người chơi mới cảm thấy “đã chơi muộn nên mất hết content”.

Đối với cosmetic cũ:

- có thể archive;
- một số item có thể quay lại ở future rotation;
- item không quay lại ngay cũng không nên tác động gameplay.

Việc returning cosmetic có thể hay không còn phụ thuộc rarity/brand direction.

---

# XI. RETURNING PLAYER / COMEBACK FLOW

## 1. Mục tiêu

Người chơi quay lại sau thời gian nghỉ phải biết:

- Có gì mới?
- Tôi đang ở đâu?
- Tôi nên làm gì trước?
- Có gì đã thay đổi?

Không bắt người chơi đọc patch note dài mới hiểu được game.

## 2. Comeback Flow

```text
Login
↓
Welcome Back Summary
↓
What Changed
↓
Outpost Snapshot
↓
Recommended Mission / Event
↓
One Small Returning Goal
↓
Reward
↓
Normal Core Loop
```

## 3. Returning Player Summary

Nên cho thấy tối đa 3 nhóm:

- **World/Event:** event đang diễn ra.
- **Progression:** Outpost/Blueprint/Mastery hiện tại.
- **Next Best Action:** một hoạt động rõ ràng.

Không biến comeback screen thành 10 banner marketing.

## 4. Comeback Reward

**ĐỀ XUẤT MỚI:** reward nhỏ để tái kích hoạt gameplay, không đủ lớn để tạo “log out → log in để farm”.

Có thể ưu tiên:

- resource;
- Blueprint Fragment nhỏ;
- Event participation support;
- cosmetic/utility nhẹ.

Không trao permanent combat power lớn chỉ vì nghỉ lâu.

---

# XII. CONTENT ROTATION

## 1. Mục tiêu

Rotation dùng để:

- giữ discovery;
- đổi spotlight;
- hỗ trợ sản xuất content;
- tránh game luôn hiển thị tất cả thứ cùng lúc.

Rotation không được dùng để:

- xóa permanent ownership;
- khóa core progression;
- tạo scarcity giả để ép mua.

## 2. Có thể Rotate

- featured challenge;
- World Event;
- cosmetic spotlight;
- limited mission set;
- seasonal modifier;
- Guild activity spotlight.

## 3. Không Rotate khỏi tài khoản

- Outpost progression;
- unlocked Blueprint;
- owned Tower;
- Mastery;
- Campaign unlock;
- permanent progression.

---

# XIII. RETURNING CONTENT

## 1. Principle

> Temporary content có thể biến mất khỏi active rotation, nhưng không được tạo cảm giác người chơi mất permanent progression.

## 2. Event Archive

Event đã kết thúc nên có archive metadata:

- tên;
- season;
- thời điểm tổ chức;
- reward family;
- status;
- future return eligibility.

Archive chủ yếu cho continuity và production reference; không nhất thiết phải mở thành menu lớn cho player.

## 3. Re-run Policy

**ĐỀ XUẤT MỚI:** event cũ có thể quay lại sau một khoảng cách đủ lớn, nhưng không cam kết lịch trước.

Khi re-run:

- giữ identity chính;
- có thể cập nhật reward;
- không tạo currency tồn tại song song nếu không cần;
- vẫn phải tuân thủ economy cap.

---

# XIV. SEASON ROLLOVER

## 1. Trước khi kết thúc Season

Nên có:

- countdown nhẹ;
- final reward reminder;
- Event Token spend reminder;
- season summary.

Không spam notification.

## 2. Khi Season kết thúc

### Giữ

- Outpost;
- Tech Tree;
- Mastery;
- Blueprint;
- owned content;
- permanent resources/progression.

### Reset / Close

- Seasonal Challenge progress;
- Seasonal leaderboard;
- Seasonal Event Token;
- seasonal mission progress;
- seasonal pass progress.

Đây là danh sách theo Optimized design; bất kỳ exception nào phải được ghi thành decision mới.

## 3. Rollover Flow

```text
Season End
↓
Finalize Results
↓
Grant Validated Rewards
↓
Close Temporary Currency
↓
Archive Seasonal Content
↓
Open New Season
↓
Recalculate Active Live Content
```

Permanent progression không đi qua reset transaction.

---

# XV. SEASONAL REWARD ECONOMY

## Reward Categories

| Category | Permanent? | Seasonal? |
|---|---:|---:|
| Scrap | Có | Không reset |
| Materials | Có | Không reset |
| Blueprint Fragment | Có | Không reset |
| Biomass | Có | Không reset |
| Rad-Gems | Có | Không reset |
| Mastery XP | Có | Không reset |
| Event Token | Không | Có |
| Seasonal Pass Progress | Không | Có |
| Seasonal Leaderboard | Không | Có |
| Seasonal Cosmetic | Ownership permanent | Acquisition seasonal |

### Ghi chú

Seasonal Cosmetic sau khi người chơi sở hữu vẫn là ownership lâu dài; chỉ **cửa sổ acquisition** có thể là temporary.

---

# XVI. LIVE OPS RESOURCE GUARDRAILS

Live Ops không được trở thành nguồn phá economy.

Theo dõi:

- reward/minute;
- reward/mission;
- event reward inflation;
- Event Token generation;
- Blueprint completion acceleration;
- Mastery acceleration;
- Plus/Pro bonus interaction;
- rewarded ads contribution.

## Guardrail

Một event có multiplier không được khiến người chơi cảm thấy Campaign reward bình thường “không đáng chơi” sau khi event kết thúc.

## Anti-Inflation

Không chồng quá nhiều bonus:

```text
Base Reward
+ Event Multiplier
+ Daily Bonus
+ Weekly Bonus
+ Plus Bonus
+ Pro Bonus
+ Ad Bonus
```

Nếu nhiều lớp cùng active, economy phải có cap/soft cap/eligibility rule phù hợp.

---

# XVII. PLUS / PRO TRONG LIVE OPS

Giữ nguyên monetization đã chốt.

## Outpost Plus

Có thể giữ:

- QoL;
- +5% logistics speed;
- +5% Scrap reward efficiency;
- extra Loadout;
- extra Build Preset;
- PvE Tactical Utility Slot ở mode cho phép;
- better daily reward;
- Plus cosmetic.

## Outpost Pro

Có thể giữ:

- khoảng +8–10% một số PvE logistics efficiency;
- extra Loadout/Preset;
- thêm Tactical Slot trong mode được phép;
- Expedition convenience;
- Experimental Module thiên về trade-off;
- Pro cosmetic/VFX.

### Live Ops Rule

Season/Event không được trở thành lý do để tăng bonus Plus/Pro ngoài thiết kế.

Không thêm gói Live Ops pay-to-win mới chỉ vì event có modifier.

---

# XVIII. REWARDED ADS TRONG LIVE OPS

Giữ nguyên nguyên tắc **tự nguyện**.

Có thể hỗ trợ:

- +25% Expedition reward;
- additional Reward Chest;
- giảm thời gian Expedition;
- resource nhỏ sau trận.

Không nên:

- bắt xem ads để claim core seasonal reward;
- reset free event attempt bằng ads liên tục;
- biến ad thành điều kiện hoàn thành Season Pass.

---

# XIX. LIVE OPS UX HIERARCHY

Từ Home/Outpost, người chơi chỉ nên dễ dàng nhận ra:

```text
1. What is happening now?
2. Why should I care?
3. What can I do?
4. What will I get?
5. When does it end?
```

### Event Card tối thiểu

- Event Name;
- short hook;
- time remaining;
- primary action;
- reward preview;
- progress;
- optional details.

Không dùng một màn hình với hàng loạt banner ngang nhau.

---

# XX. NOTIFICATION PRINCIPLES

Notification chỉ nên dùng khi:

- event bắt đầu;
- season sắp kết thúc;
- reward đủ điều kiện claim;
- Expedition hoàn thành nếu người chơi đã bật thông báo.

Không gửi thông báo chỉ để:

- ép login;
- nhắc daily nhiều lần;
- tạo cảm giác mất mát giả;
- quảng cáo cosmetic không liên quan.

### Notification Priority

P0 — Reward / important state  
P1 — Event/Season state  
P2 — Optional promotion  
P3 — Không gửi nếu không cần thiết

---

# XXI. LIVE OPS CONTENT PRODUCTION STANDARD

Mỗi event/season package phải có:

```text
Content ID
Name
Version
Start Time
End Time
Theme
Gameplay Modifier
Objective
Enemy Roster Rule
Reward Table
Currency Rule
UI Assets
VFX/SFX Requirements
Localization
QA Checklist
Analytics Events
Rollback / Disable Rule
```

## Content Status

```text
Draft
→ Internal Review
→ Prototype
→ Playtest
→ Balance
→ Production
→ QA
→ Scheduled
→ Live
→ Archived
```

## Definition of Done — Event

- [ ] Gameplay modifier rõ ràng.
- [ ] Không phá Fixed Enemy Roster.
- [ ] Không phá 5–15 phút nếu dùng mission core.
- [ ] Reward có giá trị.
- [ ] Event Token đúng expiry.
- [ ] UI có countdown.
- [ ] Art/Audio đủ nhận diện.
- [ ] Analytics event names đã xác định.
- [ ] Economy reviewed.
- [ ] Accessibility reviewed.
- [ ] Rollback plan tồn tại.
- [ ] Không làm mất permanent progression.

---

# XXII. WORLD EVENT EXAMPLES

## A. SCRAP METEOR SHOWER

**Core lesson:** reward vs safety trade-off.

**Modifier:** Scrap cache xuất hiện trong khu vực có rủi ro.

**Objective:** thu được lượng Scrap mục tiêu.

**Reward:** Event Token + resource.

**Production load:** Low–Medium.

---

## B. EXTREME HEAT

**Core lesson:** logistics/cooling pressure.

**Modifier:** heat pressure tăng theo cấu hình event.

**Objective:** sống sót với giới hạn heat.

**Reward:** Mastery + Event Token + cosmetic progress.

**Production load:** Medium.

---

## C. SPORE SEASON

**Core lesson:** hazard management.

**Modifier:** spore hazard tạo vùng kiểm soát/visibility pressure.

**Objective:** giữ Outpost ổn định qua các wave.

**Reward:** event rewards.

**Production load:** Medium.

---

## D. FROZEN SUPPLY ROUTE

**Core lesson:** logistics adaptation.

**Modifier:** supply route bị hạn chế/thay đổi.

**Objective:** duy trì Ammo/Power/Water đủ để hoàn thành mission.

**Reward:** Blueprint Fragment / cosmetic.

**Production load:** Medium.

---

# XXIII. CONTENT SCALE CONTROL

Live Ops không được khiến scope tăng vô hạn.

## Event Budget

Một event nên ưu tiên:

**1 mechanic + 1 objective + 1 reward family + reuse existing content**

Nếu cần:

- map system mới;
- enemy mới;
- currency mới;
- UI flow mới;
- save schema mới;
- monetization mới;

thì đó phải được coi là **feature proposal riêng**, không tự động nằm trong event.

## Season Budget

Một season nên ưu tiên:

- 1 theme;
- 1 gameplay modifier lớn;
- một số challenges;
- một nhóm seasonal missions;
- cosmetic set;
- pass progression.

Không tạo hàng chục hệ thống mới chỉ để “có content”.

---

# XXIV. LIVE OPS QUALITY CHECK

## Gameplay

- [ ] Event có một decision rõ.
- [ ] Player hiểu counterplay.
- [ ] Không chỉ tăng HP/damage.
- [ ] Không phá battle readability.
- [ ] Không tạo excessive attention load.

## Progression

- [ ] Permanent progression không reset.
- [ ] Temporary progression được phân biệt.
- [ ] Returning player có đường quay lại.
- [ ] New player không bị bỏ xa không thể hiểu.

## Economy

- [ ] Reward rate hợp lý.
- [ ] Event Token không tạo inflation không kiểm soát.
- [ ] Không có forced monetization.
- [ ] Plus/Pro giữ đúng giới hạn.
- [ ] Ads tự nguyện.

## UX

- [ ] Event end time rõ.
- [ ] Reward preview rõ.
- [ ] Progress dễ đọc.
- [ ] Claim state rõ.
- [ ] Notification không spam.

## Production

- [ ] Content ID.
- [ ] Version.
- [ ] Localization.
- [ ] Art/audio.
- [ ] QA.
- [ ] Analytics.
- [ ] Rollback.

---

# XXV. ANALYTICS HANDOFF PLACEHOLDERS

Chi tiết event naming thuộc **Analytics & KPI Bible v1.0**, nhưng Live Ops phải cung cấp tối thiểu:

- event_started;
- event_viewed;
- event_played;
- event_progressed;
- event_reward_claimed;
- event_completed;
- event_currency_earned;
- event_currency_spent;
- event_expired;
- season_started;
- season_completed;
- comeback_flow_started;
- comeback_flow_completed.

**Lưu ý:** Đây là danh sách design-level cần analytics hỗ trợ, không phải implementation specification.

---

# XXVI. DECISION LOG — SOURCE VS PROPOSAL

## Đã có trong tài liệu hiện tại

- Daily 1–3 mục tiêu nhỏ.
- Weekly Challenge.
- World Event 24–72h.
- Các ví dụ World Event.
- Event Token.
- Seasonal Pass Free/Premium.
- Outpost không reset.
- Persistent Tech Tree/Mastery/Blueprint.
- Seasonal Challenge/leaderboard/event currency/mission progress có thể reset.
- Cosmetic có thể là seasonal reward.
- Không bắt người chơi Daily liên tục để theo kịp Campaign.
- Event không cần tạo nguyên game mode mới.

## Đề xuất mới trong v1.0

- Season target 6 tuần.
- Monthly/Cadence Checkpoint không có currency riêng.
- Season Starter Track.
- Comeback Summary flow.
- Event Archive metadata.
- Re-run policy.
- Event/Season content budget.
- Một số UX/notification guardrails cụ thể.

Những mục này **không được xem là quyết định core đã chốt** cho tới khi được cập nhật vào Current Decisions/Master Design Index.

---

# XXVII. DEFINITION OF DONE — LIVE OPS SYSTEM

Live Ops được coi là đủ production-ready khi:

- [ ] Daily/Weekly cadence được xác định.
- [ ] Season structure được xác định.
- [ ] World Event template đã chuẩn hóa.
- [ ] Temporary vs Permanent progression rõ ràng.
- [ ] Event currency lifecycle rõ ràng.
- [ ] Season rollover rõ ràng.
- [ ] New Player entry flow rõ.
- [ ] Returning Player flow rõ.
- [ ] Reward economy guardrails rõ.
- [ ] Plus/Pro interaction đã được kiểm tra.
- [ ] Rewarded Ads không phá progression.
- [ ] Event production template có đầy đủ handoff fields.
- [ ] QA checklist có.
- [ ] Analytics handoff fields có.
- [ ] Rollback/disable rule có.
- [ ] Không tạo thêm core system chỉ để phục vụ Live Ops.

---

# XXVIII. FINAL DESIGN RULE

> **LAST OUTPOST không được sống nhờ việc bắt người chơi đăng nhập. LAST OUTPOST sống nhờ việc mỗi lần quay lại, người chơi có một bài toán chiến thuật đáng để giải.**

Live Ops tốt phải khiến người chơi nghĩ:

> “Có cái mới để thử.”

chứ không phải:

> “Nếu hôm nay không đăng nhập thì mình sẽ mất progress.”

---

## STATUS

**Document:** LAST OUTPOST — LIVE OPS & SEASONAL CONTENT BIBLE  
**Version:** v1.0  
**Status:** Production Design / Proposed sections explicitly marked  
**Next document:** ANALYTICS & KPI BIBLE v1.0
