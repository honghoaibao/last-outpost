# LAST OUTPOST — BẢN TỐI ƯU CƠ CHẾ v1.0

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

# VI. MÙA & THIÊN TAI

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

# VII. CĂN CỨ & CƯ DÂN

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

# VIII. TƯỚNG

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

# IX. TECH TREE

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

# X. WORLD MAP & EXPEDITION

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

# XI. GIAO THƯƠNG & XÃ HỘI

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

# XII. GUILD / PVP — BẢN GIẢN LƯỢC

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

# XIII. MONETIZATION

## Nguyên tắc

**Không bán lợi thế chiến thuật trực tiếp.**

Ưu tiên:

- Skin.
- Visual Effects.
- Base themes.
- Commander cosmetics.
- Seasonal Pass.
- QoL nhẹ.

### Blueprint

Blueprint hiếm vẫn có thể tồn tại nhưng:

- có thể kiếm bằng gameplay;
- không khóa build mạnh nhất sau paywall;
- không biến PvP thành cuộc đua trả tiền.

---

# XIV. GAMEPLAY LOOP CHUẨN

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

# XV. THỨ TỰ MỞ KHÓA HỆ THỐNG

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

# XVI. DANH SÁCH HỆ THỐNG GIỮ / GIẢM / GỘP

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
| Gacha Power | HẠN CHẾ / KHÔNG DÙNG LÀM TRỤ CỘT |

---

# XVII. TIÊU CHUẨN ĐỂ THÊM HỆ THỐNG MỚI

Trước khi thêm một cơ chế, phải trả lời 5 câu:

1. Nó tạo ra quyết định chiến thuật nào?
2. Nó có làm người chơi hiểu game khó hơn không?
3. Nó có trùng chức năng với hệ thống hiện tại không?
4. Nó có thể tạo ra khoảnh khắc đáng nhớ không?
5. Nó có cần thiết ngay bây giờ, hay nên để vào endgame?

Nếu không vượt qua ít nhất **3/5 câu**, chưa thêm hệ thống.

---

# XVIII. TẦM NHÌN CUỐI

**Last Outpost không cần trở thành game có nhiều hệ thống nhất.**

Mục tiêu là trở thành game mà người chơi cảm thấy:

> “Mình vừa thắng vì mình đã xây đúng hệ thống và xử lý đúng tình huống.”

Bản sắc cuối cùng:

**2.5D Base Defense + Modular Towers + Logistics + Survival + Adaptive Battlefield.**

Guild/PvP chỉ là lớp endgame bổ sung, không được phép làm loãng vòng lặp thủ thành cốt lõi.
