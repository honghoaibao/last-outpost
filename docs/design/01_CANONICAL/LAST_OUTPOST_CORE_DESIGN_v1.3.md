> **STATUS:** CURRENT / CANONICAL CORE DESIGN
>
> **Version note:** v1.3 is a documentation consolidation of the previously optimized v1.2 design. Core gameplay intent is preserved; this file does not silently reset or redesign locked systems.
>
> **Authority:** This document defines macro/core design decisions. Detailed rules belong to the supporting source documents listed in the Master Design Index.

# LAST OUTPOST — CORE DESIGN / OPTIMIZED MASTER v1.3

> Cập nhật v1.2: Trụ Sở phát triển vĩnh viễn không reset; Mission/Challenge; Enemy Intel; Loadout; Mastery; World Events; Blueprint Crafting; monetization theo 3 tầng Free → Outpost Plus → Outpost Pro, cho phép lợi thế chiến thuật nhẹ có giới hạn chủ yếu trong PvE.

> Mục tiêu: giữ lại bản sắc 2.5D hậu tận thế, thủ thành, logistics, sinh tồn và build tự do; đồng thời giảm số lượng hệ thống phải theo dõi cùng lúc để gameplay rõ ràng, dễ học và dễ mở rộng.

---

## 0. TRIẾT LÝ THIẾT KẾ

### 0.1. 4 trụ cột gameplay

**BUILD → PREPARE → SURVIVE → ADAPT**

1. **BUILD:** xây căn cứ, tuyến phòng thủ, tháp và module.
2. **PREPARE:** chuẩn bị đạn, điện, nước/tản nhiệt và nhân lực.
3. **SURVIVE:** chống wave quái, boss và thiên tai.
4. **ADAPT:** thay đổi module, tuyến phòng thủ hoặc cách dùng địa hình theo tình huống.

Mọi hệ thống mới về sau phải phục vụ ít nhất một trong 4 trụ cột này.

### 0.2. Quy tắc chống quá tải

- Một thời điểm chỉ nên có **1 vấn đề lớn + 1 vấn đề phụ** cần người chơi xử lý.
- Không để nhiều hệ thống đồng thời báo lỗi ở mức khẩn cấp.
- Hệ thống mới ưu tiên tạo **quyết định chiến thuật**, không chỉ tạo thêm hình phạt.
- Các hệ thống meta sâu như bang hội/PvP chỉ mở rộng sau khi vòng lặp PvE đã đủ vui.

---

# I. GÓC NHÌN, ĐỒ HỌA & HỆ THỐNG ĐỊA HÌNH

## 1. 2.5D Isometric

Giữ góc nhìn 2.5D Isometric và hệ thống Z-Layer:

- **Z=0:** mặt đất, bẫy, tường thấp, dòng nước, cháy, bão.
- **Z=1:** tường chính, tháp mặt đất, tướng và phần lớn quái.
- **Z=2:** tháp treo, cáp, quái bay, một số hiệu ứng thời tiết.

Giữ định hướng 1000+ quái nhưng chỉ bật mật độ cực cao ở các đoạn cao trào; phần còn lại dùng pooling/lod/particle batching.

## 2. Vị trí xây dựng

Giảm còn **3 loại chính + 1 loại đặc biệt**:

1. **Ground Mount:** tháp hạng nặng, ổn định.
2. **Wall/Ceiling Mount:** tháp tầm trung, né quái mặt đất.
3. **Rail Mount:** tháp cơ động theo tuyến ray.
4. **Salvaged Fortification:** xác xe/container/tháp nước dùng như công trình đặc biệt, mỗi màn chỉ có một số điểm tương tác.

Không biến mọi vật thể trên bản đồ thành một hệ thống xây dựng riêng.

## 3. Đường đi của quái

Giữ:

- Tường/Barricade.
- Dây thép gai.
- Hào chông.
- Dầu/Keo/Bẫy.

Đường xây dựng dùng **snap segment** thay cho kéo tự do hoàn toàn. Người chơi vẫn có cảm giác sandbox nhưng dễ đặt, dễ tính pathfinding và dễ đọc trên mobile.

---

# II. HỆ THỐNG THÁP MÔ-ĐUN

## 1. Công thức

**Tháp = Base + Barrel + Module**

Giữ nguyên cơ chế 3 phần vì đây là một trong những điểm khác biệt mạnh nhất.

### Base

Quyết định:
- máu/độ bền;
- tốc độ xoay hoặc di chuyển;
- sức chứa đạn;
- khả năng tản nhiệt.

### Barrel

Quyết định:
- loại sát thương;
- tầm bắn;
- tốc độ bắn;
- mục tiêu ưu tiên.

### Module

Tạo hiệu ứng phụ:
- hút phế liệu;
- tăng tốc độ bắn;
- chiếu sáng;
- làm chậm;
- hỗ trợ logistics.

## 2. Giới hạn để dễ cân bằng

- Mỗi màn chỉ mở một số slot module tùy cấp Tech Tree.
- Không cho phép cộng vô hạn mọi hiệu ứng.
- Module có **Tag**: Damage / Control / Utility / Logistics.
- Một tháp chỉ có thể đạt tối đa 2 Tag chính.

Điều này giúp build có cá tính mà không biến thành hàng nghìn tổ hợp khó cân bằng.

---

# III. LOGISTICS — GIỮ SÂU NHƯNG KHÔNG RỐI

## 1. Tài nguyên vận hành trong trận

Chỉ giữ **4 loại chính**:

| Tài nguyên | Vai trò |
|---|---|
| **Scrap** | Xây dựng, sửa chữa, nâng cấp tạm thời |
| **Ammo** | Đạn và duy trì hỏa lực |
| **Power** | Tháp điện tử, ray, drone |
| **Water** | Dân cư + tản nhiệt |

**Biomass** chuyển thành tài nguyên meta, không cần theo dõi liên tục trong trận.

## 2. Tiếp tế

Hệ thống cốt lõi:

**Kho trung tâm → tuyến tiếp tế → tháp**

Có thể dùng:
- Xe tiếp đạn.
- Drone.
- Ray.

Nhưng trong một trận chỉ nên chọn **một tuyến logistics chính** và một tuyến dự phòng.

## 3. Quá nhiệt

Giữ cơ chế nhiệt nhưng đơn giản hóa:

- 0–70%: bình thường.
- 70–90%: hiệu suất giảm nhẹ.
- 90–100%: cảnh báo.
- 100%: Jam hoặc tắt tạm thời.

Không cho tháp bốc cháy thường xuyên. Cháy là sự kiện đặc biệt hoặc do combo môi trường.

---

# IV. ĐỊA HÌNH & VẬT LÝ

Giữ các tương tác có giá trị chiến thuật:

- Quái nặng phá tường.
- Nước có thể cuốn quái ở địa hình phù hợp.
- Vách đá có thể sập.
- Dầu + lửa tạo vùng cháy.
- Xác quái có thể tạo **Corpse Bridge**.

### Corpse Bridge

Giữ vì đây là cơ chế rất đặc biệt.

Nhưng dùng **ngưỡng tích tụ** thay vì mô phỏng từng xác vật lý quá nặng:

- 0–30%: bình thường.
- 30–70%: địa hình bị cản.
- 70–100%: tạo cầu xác.

Tháp Thiêu Mẫu hoặc công cụ dọn xác làm giảm thanh tích tụ.

---

# V. QUÁI VẬT — ROSTER CỐ ĐỊNH THEO TỪNG MÀN

Giữ 12 loại quái trong toàn game, nhưng **mỗi màn chỉ có một số loài được chỉ định trước**. Người chơi có thể biết hoặc dần mở khóa thông tin về roster của màn, sau đó chuẩn bị đội hình tháp/module phù hợp.

### 1. Roster của màn

Mỗi màn có một cấu hình quái riêng gồm:

- **Số loài quái tối đa của màn.**
- **Danh sách loài được phép xuất hiện.**
- **Loài nào là quái chủ lực, hỗ trợ hoặc đặc biệt.**
- **Boss của màn (nếu có).**

Wave **không được tự ý thêm loài ngoài roster của màn**. Điều này giúp mỗi màn có một "bài toán chiến thuật" riêng thay vì biến trận đấu thành một nồi lẩu quái vật. 😭

### 2. Phân nhóm 12 loài trong toàn game

#### Nhóm A — Cơ bản
- Crawler.
- Acid Bloater.
- Iron-Hide Behemoth.
- Tunnel Burrower.

#### Nhóm B — Chuyên phá hệ thống
- EMP Stalker.
- Corpse Weaver.
- Scavenger Harpy.
- Spore Spreader.

#### Nhóm C — Chuyên ép chiến thuật
- Riot Mech Mutate.
- Siren Screamer.

#### Boss
- Colossus Titan.
- Hive Mother.

### 3. Quy tắc phối hợp trong một màn

Roster được cố định trước, nhưng thứ tự và tỷ lệ xuất hiện thay đổi theo wave. Một wave nên ưu tiên:

**1 quái gây áp lực chính + 1 quái hỗ trợ**, sau đó mới tăng độ phức tạp ở các wave cao hơn.

Một màn có thể dạy một "bài" rõ ràng, ví dụ:

> **Màn A:** Crawler + Acid Bloater → học kiểm soát đám đông và xử lý mục tiêu nguy hiểm.

> **Màn B:** Behemoth + Crawler + Siren → học phá giáp và ưu tiên mục tiêu.

> **Màn C:** Burrower + EMP Stalker + Corpse Weaver → học chống đột nhập, dự phòng điện và xử lý xác.

### 4. Nguyên tắc thiết kế màn

- **Không random loài ngoài roster.**
- Có thể random **vị trí, thời điểm xuất hiện, số lượng và thứ tự wave** trong phạm vi roster.
- Boss chỉ phối hợp với roster được thiết kế cho chính màn đó.
- Mỗi màn nên có **một bài toán chiến thuật trung tâm** để người chơi có thể học, thử nghiệm và làm chủ.

---

# VI. MISSION, INTEL & REPLAYABILITY

## 1. Mission Objectives

Mỗi màn không chỉ có mục tiêu "sống sót".

Mỗi màn gồm:

- **Mục tiêu chính:** hoàn thành toàn bộ wave.
- **1–3 mục tiêu phụ:** tạo cách chơi khác nhau.
- **Challenge:** điều kiện khó tùy chọn để nhận phần thưởng thêm.

Ví dụ:
- Không để Kho Đạn xuống dưới 50%.
- Không dùng quá 3 Tower điện.
- Giữ Base HP trên 70%.
- Hoàn thành trong giới hạn thời gian.
- Không để Tướng bị hạ.

Mục tiêu phụ không được biến thành yêu cầu bắt buộc để qua màn.

## 2. Enemy Intel / Codex

Trước khi vào màn, người chơi có thể xem những thông tin đã khám phá:

- Roster quái của màn.
- Vai trò từng loài.
- Điểm mạnh/yếu.
- Cơ chế đặc biệt.
- Gợi ý counter.

Thông tin mới được mở dần khi người chơi thực sự gặp quái, hoàn thành nhiệm vụ hoặc nghiên cứu Tech.

Mục tiêu là giúp người chơi **học cách giải game ngay trong game**, không bắt buộc phải tra wiki bên ngoài.

## 3. Loadout

Người chơi có thể lưu nhiều bộ:

- Tower.
- Module.
- Tướng.
- Ưu tiên logistics.
- Thiết lập chiến thuật.

Ví dụ:

**Anti Swarm / Anti Armor / Anti EMP / Boss**

Loadout không tự chơi thay người. Nó chỉ giúp chuẩn bị nhanh và giảm thao tác lặp lại trên mobile.

## 4. Battle Report

Sau mỗi trận hiển thị:

- Wave hoàn thành.
- Base HP còn lại.
- Damage gây ra.
- Ammo/Power/Water tiêu thụ.
- Tower quá nhiệt.
- Quái đặc biệt đã xử lý.
- Mục tiêu đã đạt/chưa đạt.
- Điểm mạnh/yếu của chiến thuật.

Battle Report phải ưu tiên **thông tin hữu ích để cải thiện trận sau**, không chỉ là màn hình thống kê.

## 5. Daily / Weekly Challenge

### Daily
Một biến thể nhẹ của gameplay, ví dụ:

> Chỉ dùng Mechanical Towers.

### Weekly
Một thử thách dài hơn:

> Quái nhanh hơn 30%, đổi lại Scrap ×2.

Có thể dùng **Seed** để toàn bộ người chơi tham gia cùng một cấu hình thử thách.

Phần thưởng ưu tiên:
- cosmetic;
- currency;
- Blueprint Fragment;
- Mastery XP;
- tài nguyên meta.

Không dùng challenge để bán sức mạnh.

## 6. World Events

Các event ngắn 24–72 giờ có thể thay đổi một phần gameplay:

- Scrap Meteor Shower.
- Extreme Heat.
- Spore Season.
- Frozen Supply Route.

Một event nên có:
- 1 cơ chế đặc biệt;
- 1 mục tiêu;
- 1 nhóm phần thưởng;
- 1 bộ cosmetic/event reward.

Không cần tạo nguyên một game mode mới cho từng event.

# VII. MÙA & THIÊN TAI

## 1. Chu kỳ mùa

Giữ 4 mùa, nhưng mỗi mùa có:

- **1 thay đổi gameplay lớn**.
- **1 lợi thế đặc biệt**.
- **1 tài nguyên đáng tranh giành**.

| Mùa | Thách thức chính | Lợi thế |
|---|---|---|
| Xuân | Quái sinh sản nhanh | Biomass tăng |
| Hạ | Quá nhiệt | Solar Power tăng |
| Thu | Sương mù | Tài nguyên hiếm xuất hiện |
| Đông | Logistics chậm | Tháp ít quá nhiệt |

Không để một mùa sửa quá nhiều hệ thống cùng lúc.

## 2. Thiên tai

Giữ 6 loại nhưng **chỉ một thiên tai lớn hoạt động tại một thời điểm**.

- Acid Downpour.
- Earthquake/Sinkhole.
- Scrap Meteor.
- Solar EMP.
- Fire Whirlwind.
- Toxic Spore Cloud.

Thiên tai phải tạo ra **thay đổi chiến thuật**, không chỉ trừ máu công trình.

---

# VIII. TRỤ SỞ — PROGRESSION VĨNH VIỄN

## 1. Nguyên tắc quan trọng

**Trụ Sở không reset khi người chơi bắt đầu mùa mới.**

Vượt ải → nhận tài nguyên → phát triển Trụ Sở → mở công nghệ → quay lại các màn khó hơn.

Trụ Sở là "xương sống" tiến trình dài hạn của tài khoản.

## 2. Những thứ VĨNH VIỄN

Không reset:

- Cấp/tiến trình Trụ Sở.
- Công trình đã mở khóa.
- Tech Tree.
- Blueprint đã sở hữu.
- Tower/Module đã sở hữu.
- Commander.
- Mastery.
- Thành tựu.
- Cosmetic.
- Nội dung Story đã mở.

## 3. Những thứ CÓ THỂ RESET THEO MÙA

Có thể reset:

- Seasonal Challenge progress.
- Seasonal leaderboard.
- Seasonal Event currency.
- Seasonal mission progress.
- Thành tích xếp hạng mùa.

Không được reset core progression đã kiếm được bằng gameplay.

## 4. Outpost Evolution

Trụ Sở không chỉ tăng Level mà còn **thay đổi hình thái**.

### Stage 1 — Survivor Camp
- Bunker tạm.
- Container.
- Hàng rào cơ bản.

### Stage 2 — Fortified Outpost
- Tường thép.
- Kho đạn.
- Tháp canh.

### Stage 3 — Industrial Outpost
- Xưởng.
- Ray.
- Generator.
- Drone.

### Stage 4 — Advanced Fortress
- Research Lab.
- Defense Grid.
- Logistics Hub.

### Stage 5 — Last Bastion
- Mega Outpost.
- Khu quân sự.
- Khu nghiên cứu.
- Công trình endgame.

## 5. Xây dựng và mở rộng Trụ Sở

Không chỉ dùng nút:

> Upgrade HQ Lv.27 → Lv.28

Người chơi mở các khu đất/khu chức năng mới và quyết định xây gì trước.

Ví dụ:

> **Research Wing** hoặc **Industrial Wing**

Mỗi lựa chọn ưu tiên một hướng phát triển, nhưng không khóa vĩnh viễn toàn bộ nội dung.

## 6. Ba lớp progression

### HQ Level
Mở:
- diện tích;
- công trình cơ bản;
- sức chứa.

### Tech Tree
Mở:
- cơ chế;
- công nghệ;
- build mới.

### Story / Achievement
Mở:
- công trình đặc biệt;
- khu vực hiếm;
- cosmetic/visual;
- nội dung lore.


# IX. CĂN CỨ & CƯ DÂN

## 1. Thu gọn hệ thống dân cư

Không cần mỗi dân có quá nhiều chỉ số ẩn ở giai đoạn đầu.

Mỗi dân chỉ cần:

- Nghề hiện tại.
- Hiệu suất.
- Trạng thái còn sống/bị thương.

Nhóm công việc:

1. Logistics.
2. Food & Water.
3. Repair.
4. Expedition.
5. Guard.

## 2. Lương thực và nước

Không để người chơi phải liên tục can thiệp vào việc ăn uống.

Dùng cơ chế **Dự trữ → Tiêu thụ → Cảnh báo**.

Chỉ khi kho xuống thấp mới sinh ra quyết định:

> ưu tiên nước / đạn / sửa chữa / sinh hoạt.

## 3. Morale

Giữ Morale nhưng biến thành **chỉ số tổng hợp** thay vì một hệ thống vi mô.

Morale lấy từ:

- đủ nước;
- đủ lương thực;
- căn cứ an toàn;
- cư dân bị thương/thất nghiệp;
- thời tiết/thảm họa.

Morale chỉ tạo 3 trạng thái:

- **High:** buff nhẹ.
- **Normal:** không hiệu ứng.
- **Low:** hiệu suất giảm.

Không để cư dân tự phát sinh hàng chục loại sự cố riêng.

---

# X. TƯỚNG

Giữ hệ thống Tướng nhưng giới hạn vai trò rõ ràng.

### 3 class lõi

**Engineer**
- sửa tháp;
- xử lý nhiệt;
- dựng công trình tạm.

**Guardian**
- đánh gần;
- bảo vệ tuyến trong;
- cắt quái đột nhập.

**Bio Specialist**
- xử lý Biomass;
- buff sinh tồn;
- hỗ trợ đối phó quái dị biến.

Mỗi Tướng có:

- 1 đòn cơ bản.
- 2 skill chủ động.
- 1 ultimate.

Không cần hệ thống skill quá lớn ở phiên bản đầu.

---

# XI. TECH TREE

Giữ 4 nhánh:

1. Hỏa lực.
2. Hậu cần.
3. Sinh tồn.
4. Quân sự.

Nhưng thay 5 Tier tuyến tính bằng **các node lựa chọn**.

Mỗi nhánh có khoảng:

- Early: 4–6 node.
- Mid: 6–8 node.
- Late: 4–6 node.

Mỗi node nên mở:

> một cơ chế mới / một lựa chọn build / một cách giải quyết vấn đề.

Hạn chế node chỉ tăng +5%/+10% chỉ số.

---

# XII. WORLD MAP & EXPEDITION

Không cần một hệ thống thế giới mở quá lớn ở v1.

World Map chỉ cần 4 loại điểm:

- Resource.
- Dangerous.
- Survivor.
- Special Event.

### Expedition

Cư dân hoặc Tướng có thể đi Expedition khi offline.

Kết quả dựa trên:

- thời lượng;
- class/đội hình;
- trang bị;
- vùng nguy hiểm.

Mục tiêu là biến offline reward thành **quyết định trước khi thoát game**, không phải click claim mỗi vài phút.

---

# XIII. GIAO THƯƠNG & XÃ HỘI

Giảm hệ thống ngoại giao xuống:

### 1. Trade

Trao đổi:
- Scrap.
- Biomass.
- Water.
- Blueprint.

### 2. Reputation

Chỉ giữ 1 chỉ số Reputation dùng để:

- giảm phí giao dịch;
- mở điểm trao đổi;
- nhận nhiệm vụ đặc biệt.

Không cần một cây ngoại giao phức tạp ở v1.

---

# XIV. GUILD / PVP — BẢN GIẢN LƯỢC

## 1. Guild

Bang hội tập trung vào **Co-op**, không xây thành một MMORPG thu nhỏ.

Guild có:

- Guild HQ.
- Shared Tech.
- Shared Storage.
- Guild Mission.
- Guild Defense.

## 2. Guild Defense

Mỗi tuần có một **Seasonal Horde**.

Thành viên đóng góp một phần căn cứ/phòng thủ hoặc trực tiếp tham gia các lượt thủ thành.

Mục tiêu chung:

> giữ HQ sống sót qua nhiều wave.

## 3. PvP

Chỉ giữ **1 mode PvP chính: Raid/Challenge Base**.

- Người chơi tạo snapshot căn cứ phòng thủ.
- Người khác tấn công căn cứ đó trong một trận PvE giả lập.
- Không phá hủy vĩnh viễn tài sản.
- Phần thưởng dựa trên kết quả và xếp hạng mùa.

### Tạm bỏ khỏi v1

- Hex-grid territory war.
- 24 giờ công thành.
- Nuclear war monuments.
- Ngoại giao chiến tranh phức tạp.
- Chiếm lãnh thổ liên tục.

Các hệ thống này chỉ xem xét lại khi core loop PvE đã ổn định.

---

# XV. MASTERY & LONG-TERM ENGAGEMENT

## 1. Mastery

Tower và Commander có Mastery riêng.

Mastery tăng bằng:
- số trận sử dụng;
- damage/gây hiệu ứng;
- hoàn thành điều kiện đặc biệt;
- tiêu diệt các loại quái phù hợp.

Phần thưởng ưu tiên:
- badge;
- title;
- skin;
- VFX;
- profile cosmetics;
- Mastery XP.

Mastery **không tăng sức mạnh vô hạn**.

## 2. Mục tiêu của Mastery

Mastery khuyến khích người chơi:
- thử nhiều Tower;
- học cơ chế sâu;
- tạo build cá nhân;
- có mục tiêu dài hạn sau khi đã hoàn thành Story.


# XVI. BLUEPRINT CRAFTING

## 1. Blueprint không phụ thuộc hoàn toàn vào Gacha

Blueprint có thể nhận từ:
- Mission.
- Boss.
- Expedition.
- World Event.
- Guild activity.
- Blueprint Fragment.

## 2. Fragment Crafting

**Blueprint Fragment × N → Blueprint**

Số Fragment cần thiết phụ thuộc độ hiếm.

Mục tiêu:
- giảm cảm giác bị khóa nội dung bởi may mắn;
- tạo tiến trình rõ ràng;
- cho phép người chơi biết chính xác mình đang tiến tới Blueprint nào.

Blueprint mạnh phải có đường kiếm bằng gameplay.


# XVII. MONETIZATION

## 1. Triết lý Free → Plus → Pro

Monetization **được phép bán một chút lợi thế chiến thuật**, nhưng lợi thế phải:

- nhỏ và có trần rõ ràng;
- ưu tiên **PvE**, QoL và thêm lựa chọn build;
- không khóa Tower/Blueprint cốt lõi sau tiền;
- không biến tiền thành cách mua thẳng chiến thắng;
- không tạo chênh lệch lớn trong PvP cạnh tranh.

### Free
Người chơi miễn phí vẫn có thể:
- mở toàn bộ Tower và Blueprint cốt lõi bằng gameplay;
- hoàn thành toàn bộ Campaign;
- phát triển Trụ Sở;
- chơi Expedition, Challenge, Guild và PvP.

### Outpost Plus
Gói nâng cấp phổ thông, thiên về **QoL + lợi thế PvE nhẹ**:
- +5% tốc độ logistics;
- +5% hiệu quả thu Scrap từ phần thưởng sau trận;
- thêm 1 Loadout;
- thêm 2 Build Preset;
- thêm 1 Tactical Utility slot trong PvE, nếu màn cho phép;
- daily reward tốt hơn;
- cosmetic Plus độc quyền.

### Outpost Pro
Gói cao hơn, dành cho người chơi chuyên sâu:
- tối đa khoảng +8–10% một số hiệu suất logistics PvE;
- thêm Loadout/Preset;
- thêm 1 Tactical Slot PvE so với Plus ở các chế độ cho phép;
- Expedition reward/cooldown thuận lợi hơn ở mức giới hạn;
- thêm một số lựa chọn Experimental Module thiên về **trade-off** (đổi lợi thế này lấy bất lợi khác), thay vì chỉ cộng damage thẳng;
- cosmetic/VFX Pro độc quyền.

**Không bán kiểu +30% damage, +50% HP Tower hoặc Tower độc quyền mạnh hơn để ép người chơi nạp tiền.**

## 2. Quy tắc PvP

PvP cạnh tranh sử dụng **Stat Normalization** đối với các bonus ảnh hưởng trực tiếp đến sức mạnh. Ví dụ:

- bonus resource ngoài trận: vẫn giữ;
- thêm preset/loadout: vẫn giữ;
- bonus logistics quá lớn: giới hạn hoặc chuẩn hóa;
- bonus damage/HP trực tiếp từ gói: không cho phép;
- Tactical Slot thêm: chỉ hoạt động nếu mode PvP đó cho phép công bằng.

Mục tiêu là để người mua Plus/Pro cảm thấy game thuận tiện và giàu lựa chọn hơn, nhưng kết quả PvP không biến thành cuộc đua nạp tiền.

## 3. Cosmetic Shop

Có thể bán:

- Tower Skin.
- Commander Skin.
- Base Theme.
- Supply Truck Skin.
- Drone Skin.
- Explosion/Projectile VFX.
- Weather VFX.
- UI/Profile cosmetics.
- Music Pack.

Cosmetic là nguồn doanh thu lớn nhưng không bắt buộc thay đổi chỉ số chiến đấu.

## 4. Seasonal Pass

### Free Track
- tài nguyên;
- Blueprint Fragment;
- Mastery XP;
- cosmetics cơ bản.

### Premium Track
- exclusive skins;
- base theme;
- VFX;
- profile frame;
- emote;
- premium currency.

Không đặt Tower mạnh hoặc Power Spike chỉ ở Premium Track.

## 5. Supporter / Founder Pack

Một gói mua một lần có thể gồm:

- Base Theme độc quyền.
- Commander Skin.
- 1–2 Tower Skin.
- VFX Pack.
- Profile Badge.
- Music Pack.
- Premium currency.

## 6. Rewarded Ads

Chỉ dùng **tự nguyện**, ví dụ:

- +25% Expedition reward.
- Thêm một Reward Chest.
- Giảm thời gian Expedition.
- Một phần thưởng tài nguyên nhỏ sau trận.

Không ép người chơi xem quảng cáo giữa wave hoặc sau mọi trận.

## 7. Expansion / Scenario Pack

Có thể bán gói nội dung lớn:

- 10–20 màn.
- 1 biome.
- 1–2 quái mới.
- 1 boss.
- 2 Tower/Module.
- 1 Commander.
- Story/lore.
- Cosmetics.
- Soundtrack.

Đây là cách bán **content**, không bán thắng lợi.

## 8. Outpost Customization

Một lớp monetization dành cho người thích xây căn cứ:

- Main Base Skin.
- Wall Skin.
- Alarm/VFX.
- Supply Truck Skin.
- Drone Skin.
- Environment Theme.
- Night Sky Theme.

Căn cứ càng phát triển càng có nhiều vị trí để trang trí.

## 9. Gacha

Không dùng Gacha để bán **Power** làm trụ cột.

Nếu sử dụng loot/random box:
- chỉ dành cho cosmetic;
- odds phải minh bạch theo yêu cầu của nền tảng/pháp luật áp dụng;
- không để duplicate trở thành cách hút tiền vô hạn một cách khó chịu.


# XVIII. GAMEPLAY LOOP CHUẨN

## 1. Chuẩn bị — 1–3 phút

- kiểm tra tài nguyên;
- chọn 3–6 tower;
- lắp module;
- chỉnh tuyến logistics;
- bố trí tướng;
- chọn ưu tiên phòng thủ.

## 2. Giao tranh — 5–10 phút

Người chơi liên tục:

- điều chỉnh mục tiêu;
- xử lý quái đặc biệt;
- quản lý nhiệt/đạn/điện;
- di chuyển Tướng;
- dùng môi trường;
- phản ứng với tối đa 1 thiên tai lớn.

## 3. Tổng kết — 1–2 phút

- thu Scrap/Biomass;
- sửa chữa;
- mở Tech node;
- nâng module;
- điều Expedition;
- chuẩn bị cho màn tiếp theo.

---

# XIX. THỨ TỰ MỞ KHÓA HỆ THỐNG

### Giai đoạn 1 — Tutorial

- Ground Tower.
- Ammo.
- Barricade.
- 3 quái cơ bản theo roster của màn tutorial.
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

# XX. DANH SÁCH HỆ THỐNG GIỮ / GIẢM / GỘP

| Hệ thống | Quyết định |
|---|---|
| 2.5D Isometric | GIỮ |
| Z-Layer | GIỮ |
| Modular Tower 3 phần | GIỮ |
| Free Building | GIỮ NHƯNG SNAP |
| Rail Tower | GIỮ |
| Logistics | GIỮ |
| Ammo | GIỮ |
| Heat | GIỮ |
| Corpse Bridge | GIỮ |
| 12 loại quái | GIỮ toàn game; mỗi màn có roster và số loài cố định |
| 4 mùa | GIỮ |
| 6 thiên tai | GIỮ, chỉ 1 thiên tai lớn/lần |
| Citizens | GIỮ, giảm vi mô |
| Food/Water | GIỮ, vận hành theo kho |
| Morale | GIỮ, gộp thành 3 trạng thái |
| 4 Tech Branches | GIỮ |
| 3 Hero Classes | GIỮ |
| World Map | GIỮ, thu gọn |
| Trade | GIỮ |
| Reputation | GIỮ |
| Guild | GIẢN LƯỢC |
| Territory War | TẠM BỎ |
| Hex-grid PvP | TẠM BỎ |
| 24h Siege | TẠM BỎ |
| 6 War Monuments | TẠM BỎ |
| Full Diplomacy | GỘP VÀO REPUTATION |
| Offline Expedition | GIỮ |
| Gacha Power | KHÔNG DÙNG ĐỂ BÁN POWER TRỰC TIẾP |
| Outpost Plus | GIỮ: QoL + lợi thế PvE nhẹ |
| Outpost Pro | GIỮ: QoL + lợi thế PvE nhẹ đến vừa, có trần |
| PvP monetization | CHUẨN HÓA STAT, HẠN CHẾ POWER ADVANTAGE |
| Trụ Sở persistent progression | GIỮ, KHÔNG RESET |
| Outpost Evolution | GIỮ |
| Mission Objectives | GIỮ |
| Enemy Intel / Codex | GIỮ |
| Loadout | GIỮ |
| Battle Report | GIỮ |
| Daily/Weekly Challenge | GIỮ |
| World Events | GIỮ |
| Mastery | GIỮ |
| Blueprint Fragment Crafting | GIỮ |
| Cosmetic Economy | GIỮ |
| Rewarded Ads tự nguyện | GIỮ CÓ KIỂM SOÁT |
| Expansion/Scenario Pack | GIỮ CHO ENDGAME |

---

# XXI. TIÊU CHUẨN ĐỂ THÊM HỆ THỐNG MỚI

Trước khi thêm một cơ chế, phải trả lời 5 câu:

1. Nó tạo ra quyết định chiến thuật nào?
2. Nó có làm người chơi hiểu game khó hơn không?
3. Nó có trùng chức năng với hệ thống hiện tại không?
4. Nó có thể tạo ra khoảnh khắc đáng nhớ không?
5. Nó có cần thiết ngay bây giờ, hay nên để vào endgame?

Nếu không vượt qua ít nhất **3/5 câu**, chưa thêm hệ thống.

---

# XXII. TẦM NHÌN CUỐI

**Last Outpost không cần trở thành game có nhiều hệ thống nhất.**

Mục tiêu là trở thành game mà người chơi cảm thấy:

> “Mình vừa thắng vì mình đã xây đúng hệ thống và xử lý đúng tình huống.”

Bản sắc cuối cùng:

**2.5D Base Defense + Modular Towers + Logistics + Survival + Adaptive Battlefield + Persistent Outpost.**

Trụ Sở là tiến trình vĩnh viễn; Campaign và các mùa là những lớp nội dung chồng lên trên nó.

Guild/PvP chỉ là lớp endgame bổ sung, không được phép làm loãng vòng lặp thủ thành cốt lõi.

Mọi monetization ưu tiên:
**cosmetic → convenience hợp lý → content expansion**, thay vì bán sức mạnh chiến đấu.
