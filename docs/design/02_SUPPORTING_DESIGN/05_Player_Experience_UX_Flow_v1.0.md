# LAST OUTPOST — PLAYER EXPERIENCE & UX FLOW v1.0

> **Vai trò tài liệu:** tầng UX/Player Experience nằm trên `Last_Outpost_Economy_Design_v1.0` và `Last_Outpost_System_Gameplay_v1.1`.
>
> **Mục tiêu:** định nghĩa người chơi nhìn thấy gì, đi qua màn hình nào, đưa ra quyết định gì và nhận phản hồi gì trong toàn bộ vòng đời trải nghiệm.
>
> **Nguyên tắc:** Gameplay tạo chiều sâu; UX làm cho chiều sâu đó dễ hiểu, nhanh tiếp cận và không gây quá tải.
>
> **Lưu ý:** Các giá trị thời gian/số slot/chi tiết layout trong tài liệu này là đề xuất UX để prototype và playtest, trừ các quy tắc đã được khóa như mission 5–15 phút và Trụ Sở persistent.

---

# 0. UX NORTH STAR

## 0.1. Người chơi phải luôn trả lời được 4 câu hỏi

1. **Tôi đang làm gì?**
2. **Tôi cần lo điều gì tiếp theo?**
3. **Tại sao chuyện này xảy ra?**
4. **Tôi có thể làm gì để xử lý?**

Nếu một cảnh báo không giúp người chơi trả lời được câu hỏi số 4, cảnh báo đó cần được thiết kế lại.

## 0.2. UX pillars

**Readable Chaos** — nhiều quái nhưng không hỗn loạn về thông tin.

**Low Friction** — thao tác lặp lại trên mobile phải ít nhất có thể.

**Actionable Feedback** — mỗi lỗi/cảnh báo phải đi kèm hành động khả thi.

**Persistent Sense of Growth** — người chơi cảm nhận Trụ Sở và tài khoản phát triển xuyên suốt.

**Respect Attention** — không spam popup, không ép quảng cáo, không giữ người chơi bằng waiting time vô ích.

---

# I. TOÀN BỘ PLAYER JOURNEY

```text
FIRST LAUNCH
   ↓
ONBOARDING
   ↓
OUTPOST HOME
   ↓
CAMPAIGN / OTHER ACTIVITIES
   ↓
MISSION BRIEFING
   ↓
LOADOUT
   ↓
PREPARE
   ↓
COMBAT 5–15 MIN
   ↓
MISSION RESULT
   ↓
REWARD / MASTERY / UNLOCK
   ↓
OUTPOST DEVELOPMENT
   ↓
NEXT MISSION / EXPEDITION / CHALLENGE
```

## 1.1. Session ngắn

Một session mobile có thể kết thúc sau **1 màn**. Người chơi không cần phải đi qua nhiều menu để thoát game.

## 1.2. Session dài

Một session dài gồm nhiều vòng:

> Mission → Result → Outpost → Mission

Không ép người chơi phải hoàn tất mọi bước meta trước khi được vào màn tiếp theo.

---

# II. INFORMATION ARCHITECTURE

## 2.1. Navigation chính

Navigation cấp 1 nên giới hạn ở:

1. **Outpost**
2. **Campaign**
3. **Expedition**
4. **Challenges / Events**
5. **Profile / Collection**

Shop không nhất thiết phải chiếm một tab ngang hàng; có thể truy cập từ Outpost hoặc Profile.

## 2.2. Nguyên tắc hierarchy

- **Battle** là CTA (nút hành động chính) mạnh nhất khi người chơi đang chuẩn bị tiếp tục Campaign.
- **Outpost** là trung tâm persistent progression.
- Các hệ thống phụ không được cạnh tranh mạnh với nút Start Mission.

---

# III. FIRST-TIME USER EXPERIENCE

## 3.1. First Launch

Mục tiêu: đưa người chơi vào gameplay thật nhanh.

Flow đề xuất:

```text
Logo
 ↓
1 câu premise
 ↓
Tên / profile cơ bản
 ↓
Tutorial Mission
 ↓
Outpost được giới thiệu
```

Không hiển thị toàn bộ Tech Tree, Shop, Guild, Seasons ngay lần đầu.

## 3.2. Onboarding phải dạy bằng hành động

Thứ tự:

**Build → Shoot → Resupply → Adapt**

Thay vì một chuỗi màn hình giải thích dài.

## 3.3. Unlock pacing

Mỗi hệ thống mới được giới thiệu sau khi người chơi đã hiểu hệ thống trước đó.

Thứ tự theo GDD:

### Giai đoạn 1 — Tutorial
- Ground Tower.
- Ammo.
- Barricade.
- Quái cơ bản theo roster tutorial.
- 1 Tướng.

### Giai đoạn 2 — Core Strategy
- Module.
- Logistics.
- Heat.
- Water.
- Corpse Bridge.

### Giai đoạn 3 — Survival
- Citizens.
- Morale.
- Seasons.
- Disasters.
- Expeditions.

### Giai đoạn 4 — Meta
- Full Tech Tree.
- Trade.
- World Map.
- Advanced Heroes.

### Giai đoạn 5 — Social Endgame
- Guild.
- Guild Defense.
- PvP Raid.
- Seasonal leaderboard.

---

# IV. OUTPOST HOME FLOW

## 4.1. Outpost là “Home Screen” thật sự

Màn hình Outpost cần cho người chơi cảm giác:

> **“Đây là căn cứ của tôi.”**

Thay vì chỉ là một dashboard số liệu.

Các vùng nhìn thấy trực tiếp:

- Command Center.
- Các building chính.
- Khu dân cư.
- Logistics.
- Research.
- Workshop.
- Khu trang trí.

## 4.2. Primary actions

Mỗi lần mở Outpost, thứ tự ưu tiên:

1. **Tiếp tục Campaign** nếu có mission đang chờ.
2. **Nhận reward** nếu có.
3. **Một hành động progression rõ ràng** nếu người chơi đang đủ nguyên liệu.
4. Các hoạt động phụ.

## 4.3. “Next Best Action”

Nếu người chơi có nhiều thứ có thể làm, UI ưu tiên **một gợi ý chính**:

> `Research hoàn tất → Mở Tech mới`

hoặc

> `Mission 12 sẵn sàng → Tiếp tục Campaign`

Không biến Outpost thành danh sách 20 dấu chấm đỏ.

---

# V. CAMPAIGN FLOW

## 5.1. Campaign map

Hierarchy:

```text
Region
  ↓
Chapter
  ↓
Mission
```

Campaign map hiển thị:
- tiến trình đã hoàn thành;
- mission hiện tại;
- mission khóa;
- reward quan trọng;
- boss/challenge marker khi có.

## 5.2. Mission card

Một Mission Card chỉ cần cho người chơi biết:

- Tên màn.
- Biome.
- Roster đã biết.
- Boss nếu có.
- Objective chính.
- Challenge phụ nổi bật.
- Reward chính.
- Best result trước đó.

Không nhồi toàn bộ stat vào card.

---

# VI. MISSION BRIEFING FLOW

## 6.1. Briefing hierarchy

```text
MISSION TITLE
↓
Threat / Biome
↓
Enemy Roster
↓
Objective
↓
Known Hazard
↓
Recommended preparation
↓
START
```

## 6.2. Enemy Intel

Mỗi enemy card nên trả lời:

- Nó làm gì?
- Nó nguy hiểm ở đâu?
- Tôi nên ưu tiên nó khi nào?
- Tôi có counter nào?

Intel bị khóa phải cho biết **cách mở** thay vì chỉ để dấu `???`.

Ví dụ:

> `Đánh bại EMP Stalker lần đầu để mở đầy đủ Intel.`

---

# VII. LOADOUT FLOW

## 7.1. Mục tiêu

Loadout giúp người chơi chuẩn bị nhanh, không làm mất quyền quyết định.

## 7.2. Loadout screen

```text
Tower Slots
├─ Tower A
├─ Tower B
├─ Tower C
└─ ...

Module / Variant

Commander

Logistics Preference

Saved Preset

START
```

## 7.3. Gợi ý chiến thuật

Game có thể hiện:

> `Màn này có Behemoth + Crawler → Crowd Control + Armor Break được khuyến nghị.`

Nhưng không tự ép thay loadout.

---

# VIII. PREPARE PHASE UX

## 8.1. Thời lượng

Khoảng chuẩn theo gameplay loop hiện tại: **1–3 phút**.

## 8.2. Thông tin luôn nhìn thấy

- Base HP.
- Ammo.
- Power.
- Water.
- Current build budget nếu có.
- Wave indicator.
- Ready / Start.

## 8.3. Build placement feedback

Khi kéo tower:

- xanh = hợp lệ;
- vàng = hợp lệ nhưng có trade-off;
- đỏ = không hợp lệ.

Khi đỏ, hiển thị lý do ngắn:

> `Không hỗ trợ Wall Mount`

không chỉ:

> `Invalid Placement`.

---

# IX. COMBAT HUD

## 9.1. HUD ưu tiên

### Top
- Mission progress.
- Base HP.
- Wave.

### Resource strip
- Scrap.
- Ammo.
- Power.
- Water.

### Main battlefield
- Tower range khi chọn.
- Enemy threat indicators.
- Hazard zone.

### Bottom / thumb zone
- Tactical skills.
- Commander skills.
- Pause/options.

## 9.2. Không hiển thị quá nhiều số

Trong combat, ưu tiên:

- trạng thái;
- trend;
- cảnh báo;
- action.

Detailed numbers chỉ xuất hiện khi người chơi inspect.

---

# X. ALERT & PRIORITY SYSTEM

## 10.1. Ba cấp cảnh báo

### Critical
Có nguy cơ mất căn cứ hoặc mất một hệ thống trọng yếu.

Ví dụ:

> `Kho Đạn sắp hết!`

### Important
Có vấn đề cần xử lý sớm.

Ví dụ:

> `Tower A quá nhiệt.`

### Informational
Không cần xử lý ngay.

Ví dụ:

> `Drone đã giao hàng.`

## 10.2. Alert aggregation

Không spam 10 popup riêng biệt.

Ví dụ:

Thay vì:

> Tower A low ammo
> Tower B low ammo
> Tower C low ammo

hiển thị:

> **3 Tower đang thiếu đạn**
> `Đi tới Logistics`

## 10.3. Adaptive alert

Một lỗi lặp lại nhiều lần có thể được gom thành summary.

Mục tiêu: giảm cognitive overload.

---

# XI. WAVE & PACING UX

## 11.1. Countdown

Trước wave:

> `Wave 5 — 0:08`

Trong khoảng này, người chơi có thể hoàn thành thao tác chuẩn bị nhanh.

## 11.2. Threat preview

Chỉ hiển thị lượng thông tin cần thiết:

> `Incoming: Crawler × 18 + Behemoth × 1`

Không nhất thiết tiết lộ toàn bộ spawn sequence của màn.

## 11.3. Special enemy warning

Khi enemy đặc biệt sắp vào battlefield:

- biểu tượng riêng;
- âm thanh riêng;
- hướng xuất hiện;
- tên/role ngắn.

Ví dụ:

> `EMP STALKER — ưu tiên xử lý`

## 11.4. Hazard warning

Thiên tai lớn phải có:

**Forecast → Countdown → Active → Ending**

Không xuất hiện hoàn toàn bất ngờ nếu người chơi không có cách phản ứng.

---

# XII. TACTICAL INTERVENTION FLOW

Người chơi cần hiểu game cho họ cơ hội “cứu tình huống”.

Ví dụ:

```text
Threat detected
↓
Alert
↓
Possible action
├─ Move Commander
├─ Retarget Tower
├─ Use Tactical Skill
├─ Repair / Resupply
└─ Change local build where allowed
```

Game không nên tự sửa mọi thứ.

---

# XIII. MISSION RESULT FLOW

## 13.1. Kết quả trong 1 màn hình đầu tiên

```text
VICTORY / DEFEAT

Stars
Mission Time
Base HP
Main Objective
Bonus Objectives

[COLLECT]
```

## 13.2. Màn hình chi tiết

Sau khi Collect mới mở rộng:

- Scrap.
- Biomass.
- Materials.
- Blueprint Fragment.
- Mastery XP.
- Challenge progress.
- New Intel.
- Unlocks.

## 13.3. Battle Report

Battle Report tập trung vào câu hỏi:

> **“Trận vừa rồi tôi học được gì?”**

Ví dụ:

- `Bạn mất nhiều Ammo nhất ở Wave 7.`
- `EMP Stalker vô hiệu hóa 4 Tower điện.`
- `Corpse Bridge hình thành 2 lần.`

Nếu có insight không chắc chắn, dùng ngôn ngữ mô tả thay vì đánh giá mơ hồ.

---

# XIV. POST-MISSION PROGRESSION FLOW

Sau Result:

```text
Collect
 ↓
Reward Applied
 ↓
Unlock / Upgrade available?
 ├─ YES → highlight one useful next action
 └─ NO  → show progress toward next milestone
 ↓
NEXT MISSION / OUTPOST
```

## 14.1. “Progress toward next milestone”

Ví dụ:

> `72 / 100 Biomass → Research Node còn 28.`

Điều này quan trọng hơn việc chỉ nói:

> `Chưa đủ Biomass.`

---

# XV. OUTPOST PROGRESSION UX

## 15.1. Build prompt

Khi đủ nguyên liệu:

> `Research Lab có thể nâng cấp.`

Người chơi xem:

- Cost.
- Benefit.
- Unlock.
- Visual change.

## 15.2. Building choice

Nếu có branch choice:

> **Industrial Wing**
> tăng khả năng sản xuất/logistics.

hoặc

> **Research Wing**
> mở thêm Tech options.

Luôn ghi rõ:

- nhận được gì;
- chưa nhận được gì;
- có reset được lựa chọn hay không.

## 15.3. Outpost Evolution

Mỗi Stage cần có một khoảnh khắc:

> **“Outpost đã phát triển.”**

Hiển thị cinematic ngắn hoặc camera tour nhẹ, sau đó quay lại gameplay nhanh.

---

# XVI. TECH TREE UX

## 16.1. Không hiển thị một cây quá lớn ngay từ đầu

Show:
- Current Branch.
- Available nodes.
- Next milestone.
- Locked future nodes.

## 16.2. Node card

Mỗi node:

```text
NAME
WHAT IT UNLOCKS
COST
PREREQUISITE
[RESEARCH]
```

Ưu tiên câu:

> `Mở Drone Tiếp Tế Tự Động`

thay vì:

> `+12% Logistics Efficiency`.

---

# XVII. EXPEDITION UX

## 17.1. One-screen flow

```text
Choose Expedition
↓
Reward preview
↓
Duration
↓
Commander / Team
↓
START
```

## 17.2. Offline return

Khi quay lại:

> `Expedition complete`

Cho người chơi thấy:

- expected reward;
- actual reward;
- bonus nếu có Plus/Pro hợp lệ;
- nút nhận.

Không bắt người chơi đi qua nhiều màn hình popup.

---

# XVIII. DAILY / WEEKLY / WORLD EVENT UX

## 18.1. Challenges

Mỗi loại chỉ có một entry point thống nhất:

> **Challenges**

Bên trong chia:
- Daily.
- Weekly.
- Event.

## 18.2. Challenge card

Hiển thị ngay:

- Condition.
- Reward.
- Time remaining.
- Progress.

Ví dụ:

> `Mechanical Only`
> `0/1 complete`
> `Reward: Blueprint Fragment ×2`

## 18.3. Event countdown

Chỉ hiển thị countdown ở những nơi thực sự liên quan.

Không spam:

> `2d 04h`

trên mọi màn hình.

---

# XIX. SHOP / MONETIZATION UX

## 19.1. Shop phải rõ “mua gì được gì”

Mỗi gói:

```text
OUTPOST PLUS

Benefits
• +5% logistics PvE
• +5% post-battle Scrap reward
• +1 Loadout
• +2 Preset
• Exclusive cosmetic

Price
[BUY]
```

Không dùng wording mơ hồ như:

> `POWERFUL BENEFITS!`

## 19.2. Plus / Pro comparison

Một màn hình so sánh:

| Benefit | Free | Plus | Pro |
|---|---|---|---|
| Core gameplay | ✓ | ✓ | ✓ |
| Campaign | ✓ | ✓ | ✓ |
| Extra Loadout | — | ✓ | ✓✓ |
| PvE convenience | — | ✓ | ✓✓ |
| Tactical options | Basic | +1 | +2* |
| PvP stat advantage | ✕ | ✕ | ✕ |

`*` chỉ ở mode cho phép và tuân thủ PvP normalization nếu áp dụng.

## 19.3. No forced pressure

- Không khóa Campaign sau popup mua hàng.
- Không spam gợi ý mua sau mỗi trận.
- Không dùng cảnh báo giả kiểu “sắp mất reward” nếu không thực sự có thời hạn.

## 19.4. Rewarded Ads

Ads phải có nhãn rõ:

> `Xem quảng cáo để nhận +25% Expedition reward?`

Các nút:

`Watch` / `No Thanks`

Không đặt nút từ chối nhỏ hơn hoặc khó bấm hơn.

---

# XX. COLLECTION / MASTERY UX

## 20.1. Tower collection

Người chơi xem:

- Owned.
- Locked.
- Mastery.
- Available Blueprint.
- Suggested use.

## 20.2. Mastery progress

Hiển thị:

> `Mastery 4 → 72%`

và một câu:

> `Còn 14 trận để mở cosmetic.`

Không yêu cầu người chơi tự tính.

---

# XXI. GUILD / PVP UX

## 21.1. Guild

Guild entry point chỉ cần trả lời:

- Guild đang làm gì?
- Tôi có thể đóng góp gì?
- Reward gì?
- Còn bao lâu?

## 21.2. PvP Raid

Flow:

```text
Find / Select Base
↓
Inspect Snapshot
↓
See Allowed Rules
↓
Choose Loadout
↓
Raid
↓
Result
```

Không hiển thị thông tin không được phép biết về defense của đối thủ.

## 21.3. Competitive fairness

Nếu bonus Plus/Pro bị normalize, UI phải nói rõ trước khi vào trận:

> `Competitive modifiers normalized.`

---

# XXII. FAILURE / DEFEAT UX

Defeat không nên chỉ là:

> `DEFEAT — Retry`

Nó nên cho người chơi một hướng tiếp theo:

```text
DEFEAT

Cause:
Ammo exhausted during Wave 8.

Suggested actions:
• Add Ammo Logistics module.
• Reduce electric tower count.
• Try Anti-Armor loadout.

[RETRY]
[CHANGE LOADOUT]
[OUTPOST]
```

Nếu nguyên nhân không chắc chắn, phải dùng wording thận trọng:

> `Ammo shortage likely contributed to defeat.`

---

# XXIII. ACCESSIBILITY

Cần hỗ trợ ngay từ prototype:

- Text size options.
- High contrast option.
- Colorblind-safe indicators.
- Haptic toggle.
- Reduced motion.
- Audio sliders.
- SFX-only combat cues nếu người chơi giảm nhạc.
- Không dùng màu đơn độc để phân biệt trạng thái.

Ví dụ:

`Đỏ + icon + text` thay vì chỉ `đỏ`.

---

# XXIV. NOTIFICATION SYSTEM

## 24.1. Các nhóm notification

**Critical**
- Base danger.
- Major event.

**Progress**
- Research complete.
- Expedition complete.
- Reward ready.

**Social**
- Guild event.
- Guild Defense.

**Commercial**
- Shop / pass / offer.

## 24.2. Notification budget

Không hiển thị quá nhiều notification cùng lúc.

Ưu tiên:

> gameplay critical > progression > social > commercial

Commercial không được che gameplay critical.

---

# XXV. RETURNING PLAYER FLOW

Khi người chơi quay lại sau một khoảng thời gian:

```text
Outpost
↓
Collect completed rewards
↓
1–3 most important updates
↓
Next best action
```

Không đưa một “wall of notifications”.

Ví dụ:

> `Expedition complete.`
> `Research complete.`
> `Weekly Challenge còn 1 ngày.`
> `Mission 18 chưa hoàn thành.`

Sau đó:

> **Continue Campaign**

---

# XXVI. SAVE / ERROR / RECOVERY UX

## 26.1. Save feedback

Không spam “Saving…”.

Chỉ báo khi:
- account sync xảy ra;
- thao tác quan trọng hoàn tất;
- có lỗi.

## 26.2. Reconnect

Nếu mất kết nối:

> `Connection lost. Battle continues using local state where supported.`

Sau khi kết nối lại:

> `Progress synchronized.`

Nếu có conflict:

> `We found conflicting progress data.`

Phải có flow xử lý rõ; không silently overwrite tiến trình.

---

# XXVII. UX ANTI-OVERLOAD RULES

1. Không mở hơn **một hệ thống mới chính** trong cùng một onboarding moment.
2. Không dùng quá nhiều popup chồng nhau.
3. Một alert nên có một hành động rõ ràng.
4. Một màn hình nên có một primary action.
5. Số liệu chi tiết dùng cho inspect, không dùng làm clutter mặc định.
6. Không biến mọi tài nguyên thành badge notification.
7. Không cho Shop/Monetization tranh vị trí với Battle/Outpost progression.
8. Mọi hệ thống mới phải có tutorial hoặc context explanation tại thời điểm cần dùng.

---

# XXVIII. CORE UX METRICS

Prototype telemetry nên theo dõi:

- Time to first playable.
- Time to first successful mission.
- Tutorial completion rate.
- Mission abandon rate.
- Average time in briefing.
- Average prepare time.
- Combat duration.
- Retry rate after defeat.
- Loadout reuse rate.
- Alert interaction rate.
- Tech Tree interaction.
- Outpost upgrade conversion.
- Reward claim latency.
- Shop open rate.
- Plus/Pro view → purchase conversion.
- Rewarded Ad opt-in rate.

UX metric không được biến thành mục tiêu tối ưu bằng cách gây khó chịu. Ví dụ tăng Shop open rate bằng popup spam là thất bại UX dù metric tăng.

---

# XXIX. DEFINITION OF DONE — PLAYER EXPERIENCE

Một flow được coi là đạt khi:

- Người chơi biết mình đang ở đâu.
- Biết mục tiêu hiện tại.
- Hiểu cảnh báo chính.
- Có hành động xử lý rõ ràng.
- Có thể hoàn thành flow bằng ít thao tác không cần thiết.
- Không bị popup che thông tin quan trọng.
- Sau Victory biết mình tiến bộ gì.
- Sau Defeat biết nên thử gì tiếp theo.
- Trụ Sở luôn tạo cảm giác tiến trình dài hạn.
- Monetization không ngăn cản core gameplay.

---

# XXX. UX GOLDEN LOOP

```text
SEE
↓
UNDERSTAND
↓
DECIDE
↓
ACT
↓
RECEIVE FEEDBACK
↓
LEARN
↓
GROW
↓
TRY AGAIN
```

Last Outpost nên khiến người chơi liên tục trải nghiệm vòng:

> **Thấy vấn đề → hiểu nguyên nhân → đưa ra quyết định → hành động → thấy kết quả → học → phát triển Trụ Sở → thử màn tiếp theo.**

Đây là mục tiêu UX cốt lõi của toàn bộ hệ thống.
