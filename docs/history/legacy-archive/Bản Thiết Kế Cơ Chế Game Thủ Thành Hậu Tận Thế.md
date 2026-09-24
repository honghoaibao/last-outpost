# BẢN THIẾT Kế CƠ CHẾ CHUYÊN SÂU: LAST OUTPOST (GÓC NHÌN 2.5D ISOMETRIC)

---

## 0. PHONG CÁCH ĐỒ HỌA & GÓC NHÌN 2.5D (VISUAL STYLE & PERSPECTIVE)

Game sử dụng góc nhìn **2.5D Isometric (Góc chiếu bao quát 45 độ)** kết hợp đồ họa Hand-drawn/Pixel Art chi tiết cao.

```
       [Trần Hang / Vách Núi - Layer Cao (Z=2)]
                       |
   ~~~~~~~~~~~~[Cáp Treo Di Động]~~~~~~~~~~~~
                       |
       [Tháp Súng Mặt Đất - Layer Trung (Z=1)]
                       |
====== [Địa Hình / Bẫy Keo / Bão - Layer Nền (Z=0)] ======
```

* **Công nghệ 2D Swarm Engine:** Tối ưu hóa render hàng nghìn quái vật (1000+ Sprites) đồng thời mà không sụt giảm FPS.
* **Hệ thống Phân tầng Độ cao (Z-Ordering & Height Layers):**
  * **Z=0 (Nền Đất):** Đặt bẫy, hào chông, bão bùng, mạch nước.
  * **Z=1 (Tuyến Giữa):** Tường rào, Tháp móng, Tướng di chuyển, Quái vật bộ binh.
  * **Z=2 (Tuyến Trên Cao):** Dây cáp treo, Tháp bám vách núi, Quái bay, Bão tố thời tiết.

---

## I. ĐỔI MỚI CÁCH XÂY & VỊ TRÍ ĐẶT THÁP (2.5D MULTI-SURFACE)

Game áp dụng hệ thống **Xây dựng Tự do & Đa nền tảng 2.5D** thay vì ô vuông cố định.

### 1. Đa dạng vị trí lắp đặt (Mount Types)
* **Tháp Móng Bề Mặt (Ground Base):** Xây trên mặt đất phẳng. Chịu lực tốt, lắp được súng hạng nặng.
* **Tháp Treo Trần / Vách Đá (Ceiling & Wall Mounts):** Bắt vào trần hang động, gầm cầu hoặc vách núi (Z-Layer 2).
  * *Ưu điểm:* Quái cận chiến mặt đất không chạm tới được.
  * *Nhược điểm:* Giới hạn trọng lượng (chỉ lắp được súng tầm trung, đạn nhẹ).
* **Tháp Di Động Trục Ray (Rail-Mounted Towers):** Trượt trơn tru trên đồ họa 2.5D theo tuyến đường ray người chơi vẽ.
* **Tận Dụng Phế Liệu Đô Thị (Scavenged Objects):** Kéo xác xe buýt, container, tháp nước cũ về gia cố thành lô cốt và gắn súng lên trên.

---

### 2. Hệ thống Ghép Tháp Mô-đun 3 Tầng (3-Tier Modular Crafting)
Mỗi tháp không phải một khối duy nhất mà gồm **3 phần riêng biệt** có thể tháo lắp linh hoạt:

$$\text{Sức mạnh Tháp} = \text{Đế (Base)} + \text{Thân \& Nòng (Barrel)} + \text{Lõi Tiện ích (Module)}$$

| Bộ phận | Chức năng | Ví dụ thiết kế |
| :--- | :--- | :--- |
| **1. Đế Tháp (Base)** | Quyết định độ bền, khả năng lưu trữ đạn và cơ động. | - *Đế Bánh Xích:* Di chuyển chậm.<br>- *Đế Tản Nhiệt:* Giảm độ tích nhiệt.<br>- *Đế Nối Đất:* Tăng $50\%$ tầm bắn cho tháp cao. |
| **2. Nòng Súng (Barrel)** | Quyết định dạng sát thương và tầm bắn. | - *Nòng Đóng Đinh:* Làm chậm quái.<br>- *Pháo Bẫy Keo:* Giữ chân nhóm quái.<br>- *Nòng Harpoon:* Kéo quái toàn giáp vào bẫy. |
| **3. Lõi Tiện Ích (Module)** | Tạo hiệu ứng phụ hoặc tương tác diện rộng. | - *Lõi Nam Châm:* Hút phế liệu.<br>- *Lõi Đèn Pha Solar:* Làm mù quái ban đêm.<br>- *Lõi Kích Xung:* Tăng tốc độ bắn xung quanh. |

---

### 3. Tự định hình đường đi của Quái (Maze Building & Terrain Shaping)
* **Tường rào ghép khớp (Tetris Walls):** Tự do xây tường barricade, dây thép gai, hào chông để ép quái đi vào bẫy.
* **Tháp Phun Dầu (Oil Sprinkler):** Phun dầu làm trượt chân quái (giảm $40\%$ tốc độ). Khi bén lửa sẽ bùng cháy thành biển lửa.

---

## II. CÁC CƠ CHẾ BỐI CẢNH & VẬT LÝ THỰC TẾ

```
                  ┌────────────────────────┐
                  │ HỆ THỐNG HẬU CẦN & ĐẠN │
                  └───────────┬────────────┘
                              │
         ┌────────────────────┴────────────────────┐
         ▼                                         ▼
┌──────────────────┐                     ┌──────────────────┐
│   Tháp Tiếp Đạn  │ ──(Chuyển đạn)──>  │ Tháp Hỏa Lực     │
└──────────────────┘                     └─────────┬────────┘
                                                   │ (Bắn liên tục)
                                                   ▼
                                         ┌──────────────────┐
                                         │  Bị Quá Nhiệt    │
                                         └─────────┬────────┘
                                                   │ (Cần Tản Nhiệt)
                                                   ▼
                                         ┌──────────────────┐
                                         │ Xử Lý Xác Quái   │
                                         └──────────────────┘
```

1. **Hệ thống Hậu cần & Đạn dược:** Tháp có băng đạn giới hạn. Cần xây **Kho Đạn Central** và duy trì xe/drone tiếp tế. Đường tiếp tế bị chặn sẽ khiến tuyến đầu ngừng hoạt động.
2. **Quá nhiệt & Tản nhiệt:** Bắn liên tục làm thanh nhiệt đỏ lên. Đạt $100\%$ nhiệt độ sẽ kẹt đạn (Jam) 10 giây hoặc bốc cháy. Cần dùng tháp phun nước/tuyết hoặc bình chữa cháy để hạ nhiệt.
3. **Tích tụ Xác Quái Vật (Corpse Pileup):** Xác quái chất đống tạo thành **"Cầu Xác" (Corpse Bridge)** giúp quái phía sau trèo qua tường rào. Phải dùng **Tháp Thiêu Mẫu** đốt xác hoặc **Xe Cẩu** tái chế thành Biomass.
4. **Địa hình & Vật lý:** Quái hạng nặng có thể tông sập tường. Người chơi có thể bắn sập vách đá hoặc xả nước hồ chứa để đè/cuốn trôi quái.
5. **Lưới Điện & Năng Lượng:** Tháp cao cấp cần điện từ Máy phát điện (Chạy bằng Dầu/Mặt Trời). Đêm xuống hoặc hết dầu, tháp điện sập nguồn, buộc phải dùng tháp cơ khí dự phòng.

---

## III. THẾ GIỚI QUÁI VẬT & THÁCH THỨC CHIẾN THUẬT (12 LOẠI QUÁI VẬT)

```
[Quái Nhỏ / Nhanh] ──> [Quái Biến Dị Đặc Biệt] ──> [Quái Hóa Giáp Phá Hoại] ──> [SIÊU TRÙM]
 (Crawler, Harpy)     (EMP, Acid Bloater)        (Behemoth, Burrower)       (Colossus)
```

| STT | Tên Quái Vật | Đặc điểm & Hành vi | Khắc chế & Tác động Chiến thuật |
| :---/ | :--- | :--- | :--- |
| **1** | **Crawler (Bọ Cào)** | Chạy nhanh, đi theo đàn đông đúc, có thể leo tường rào thấp. | Bắt buộc dùng **Tháp Bẫy Keo** hoặc **Tháp Phun Lửa** diện rộng. |
| **2** | **Acid Bloater (Bọng Axit)** | Di chuyển chậm, phát nổ xả axit khi chết gây sát thương ăn mòn tháp lân cận. | Phải dùng **Súng Tỉa Tầm Xa** tiêu diệt từ xa trước khi nó tiếp cận công trình. |
| **3** | **Iron-Hide Behemoth (Băng Giáp)** | Lớp giáp sắt dày dặn, miễn nhiễm đạn nhẹ, sẵn sàng tông thẳng vào tường rào. | Dùng **Pháo Harpoon** xé giáp hoặc **Tháp Axit** làm mềm giáp trước. |
| **4** | **EMP Stalker (Bọ Kích Xung)** | Phát ra sóng điện từ làm vô hiệu hóa toàn bộ tháp điện tử trong bán kính 15m. | Phải chuẩn bị sẵn **Tháp Bắn Đá/Tháp Đóng Đinh Cơ Khí** không dùng điện. |
| **5** | **Tunnel Burrower (Sâu Đào Hầm)** | Chui xuống đất đi xuyên qua hệ thống tường bẫy và chui lên ngay tại Kho Đạn. | Đặt **Bẫy Địa Chấn (Seismic Mine)** dưới lòng đất để ép chúng phải ngoi lên. |
| **6** | **Corpse Weaver (Quái Dệt Xác)** | Không tấn công tháp, chuyên kéo các xác quái chết lại để đắp thành Cầu Xác cực nhanh. | Ưu tiên dùng **Tháp Thiêu Mẫu** đốt sạch xác quái ngay khi rơi xuống. |
| **7** | **Scavenger Harpy (Quái Bay Trộm)** | Bay trên cao (Layer Z=2), không ăn máu căn cứ mà chuyên sà xuống gắp Drone tiếp đạn. | Lắp đặt **Tháp Treo Trần / Vách Đá** trang bị súng Lưới (Net Gun) để đánh gục. |
| **8** | **Spore Spreader (Nấm Bào Tử)** | Tỏa ra làn mây độc làm giảm $70\%$ tầm nhìn của tháp và làm hư hại Drone. | Dùng **Tháp Quạt Gió / Tháp Nhiệt** để xua tan mây độc. |
| **9** | **Riot Mech Mutate (Robot Biến Dị)** | Cầm tấm khiên kim loại khổng lồ chặn toàn bộ sát thương từ phía trước. | Dùng **Tháp Ray Di Động** trượt ra sau lưng để bắn vào điểm yếu hậu phương. |
| **10** | **Siren Screamer (Kẻ Gào Thần)** | Tiếng gào hét làm tăng $50\%$ tốc độ di chuyển và kháng khống chế cho quái xung quanh. | Dùng kỹ năng của **Tướng** hoặc **Tháp Sóng Âm** để ngắt chiêu gào. |
| **11** | **BOSS: Colossus Titan** | Trùm khổng lồ chiếm 4x4 ô 2.5D, có khả năng bốc tháp của người chơi quăng đi nơi khác. | Phải dồn tổng lực hỏa lực, kích hoạt **Sập Địa Hình (Sạt lở đá/Hồ nước)** để hạ gục. |
| **12** | **BOSS: Hive Mother (Mẹ Tổ)** | Trùm di chuyển chậm nhưng đẻ ra hàng trăm trứng Crawler liên tục mỗi phút. | Bắt buộc dồn sát thương bộc phá (Burst Damage) tiêu diệt nhanh trước khi bị ngợp đạn. |

---

## IV. CHU KỲ 4 MÙA (SPRING, SUMMER, AUTUMN, WINTER) & HỆ THỐNG THIÊN TAI ĐỘC LẬP

```
                  ┌────────────────────────┐
                  │   CHU KỲ 4 MÙA NĂM     │
                  └───────────┬────────────┘
                              │
     ┌────────────────┬───────┴───────┬────────────────┐
     ▼                ▼               ▼                ▼
[Mùa Xuân]       [Mùa Hạ]        [Mùa Thu]        [Mùa Đông]
(Bào Tử/Sinh Sản) (Nhiệt/Nổ)    (Sương/Ẩm)      (Băng/Đóng Cáp)
     │                │               │                │
     └────────────────┴───────┬───────┴────────────────┘
                              │ (Kích hoạt ngẫu nhiên)
                              ▼
                 ┌────────────────────────┐
                 │  HỆ THỐNG THIÊN TAI    │
                 │ (Acid Rain, Earthquake)│
                 └────────────────────────┘
```

### 1. Cơ chế 4 Mùa Sinh Tồn (Seasonal Cycle)
Mỗi mùa giải/chu kỳ game sẽ kéo dài theo chu kỳ 4 Mùa. Mùa ảnh hưởng trực tiếp đến đặc tính của môi trường, tài nguyên và quái vật:

| Mùa | Đặc tính Môi trường | Ảnh hưởng Gameplay & Tác động |
| :--- | :--- | :--- |
| **Mùa Xuân** *(Bào Tử & Sinh Sản)* | - Cây cối biến dị phát triển mạnh.<br>- Nguồn tài nguyên **Biomass** tăng $100\%$. | - Tốc độ đẻ quái của Mẹ Tổ & bầy Crawler tăng $30\%$.<br>- Rễ cây dị thảo có thể mọc xuyên phá móng tháp nếu không phát quang. |
| **Mùa Hạ** *(Thiêu Đốt & Hạn Hán)* | - Bức xạ Mặt Trời cực đại.<br>- Điện Mặt Trời sản xuất $+100\%$. | - Thanh tích nhiệt của Tháp tăng gấp đôi. Tháp cực kỳ dễ **Quá Nhiệt / Phát Nổ**.<br>- Nước tản nhiệt trở thành tài nguyên quý giá nhất. |
| **Mùa Thu** *(Sương Mù & Âm Khí)* | - Sương mù dày đặc bao phủ.<br>- Tầm nhìn Tháp giảm $50\%$. | - Quái tàng hình và Quái Đào Hầm hoạt động mạnh gấp đôi.<br>- Bắt buộc phải dùng **Tháp Đèn Pha Solar** hoặc **Rada Địa Chấn**. |
| **Mùa Đông** *(Bão Tuyết & Đóng Băng)* | - Nhiệt độ xuống dưới $0^\circ\text{C}$.<br>- Giúp Tháp không bao giờ bị quá nhiệt. | - Dây cáp truyền đạn bị đóng băng (ngừng hoạt động).<br>- Tốc độ di chuyển của Tướng & Xe tiếp tế giảm $40\%$. |

---

### 2. Hệ Thống Thiên Tai Đa Dạng (Catastrophic Natural Disasters)
1. **Mưa Axit Đậm Đặc (Acid Downpour):** Mưa gặm nhấm độ bền của toàn bộ tháp ngoài trời ($5\%$ máu/giây). Buộc người chơi phải bật Mái Che Năng Lượng hoặc dùng Tướng đi sửa liên tục.
2. **Động Đất & Sụt Lún (Earthquake & Sinkholes):** Lực địa chấn làm nứt đứt đường ray di động, sập tường barricade và làm đứt cáp điện thoại/điện lực.
3. **Mưa Thiên Thạch Phế Liệu (Scrap Meteor Shower):** Các mảnh vỡ vệ tinh từ quỹ đạo rơi xuống. Vừa phá hủy tháp tại điểm va chạm, vừa tạo ra các Mỏ Phế Liệu Vũ Trụ chứa linh kiện siêu hiếm.
4. **Sóng Điện Từ Mặt Trời (Solar EMP Flare):** Làm tê liệt hoàn toàn toàn bộ tháp chạy bằng điện trong 30 giây. Bắt buộc phải trông cậy vào Tháp Cơ Khí Lò Xo/Cột Đá.
5. **Vòi Rồng Lửa (Fire Whirlwind):** Khi một trận bão cát đi qua khu vực đang bùng cháy, vòi rồng lửa hình thành, cuốn quét quái vật lẫn tháp nhẹ trên đường di chuyển của nó.
6. **Mây Bào Tử Độc (Toxic Spore Cloud):** Trận gió mang mây độc gây sát thương cho Tướng và làm mù toàn bộ ống ngắm bắn tỉa.

---

### 3. Cơ chế Khởi Đầu Ngẫu Nhiên (Randomized Player Spawns)
* Khi bắt đầu mùa giải mới, người chơi được thả xuống một **Ô Địa Hình / Sinh Thái Ngẫu Nhiên** trên bản đồ thế giới (Ví dụ: *Vùng Đô Thị Phế Tích, Vùng Mỏ Núi Đá, Vùng Đầm Lầy Phóng Xạ, Vùng Băng Đảo*).
* Mỗi vùng có trữ lượng tài nguyên độc quyền khác nhau (Ví dụ: Mỏ đá thiếu Dầu, Đầm lầy thừa Axit nhưng thiếu Kim loại). Điều này ép buộc người chơi phải **thiết lập quan hệ ngoại giao và giao thương liên khu**.

---

## V. LIÊN KHU, BANG HỘI & TỔ CHỨC HẬU TẬN THẾ (ENCLAVES & ALLIANCES)

```
[Căn Cứ Khai Thác Mỏ] ──(Xuất khẩu Thép)──> [TRẠM LIÊN BANG] <──(Xuất khẩu Dầu)── [Căn Cứ Đầm Lầy]
                                                    │
                                          (Xây Siêu Công Trình)
                                                    │
                                                    ▼
                                         [TRANH GIÀNH LÃNH THỔ PVP]
```

1. **Trụ Sở Liên Bang (Guild Outpost HQ):** Thành viên Bang hội cùng góp tài nguyên để xây dựng Trạm Liên Bang Khổng Lồ và mở khóa các Siêu Công Trình Chiến Tranh.
2. **Chế độ Thủ Thành Liên Khu (Co-op Alliance Defense):** Siêu Đợt Tấn Công (Seasonal Horde) tấn công toàn bản đồ thế giới vào cuối mùa, buộc các căn cứ phải phân công nhiệm vụ chia lửa (người làm chậm, người dồn sát thương, người xé giáp).

---

## VI. HỆ THỐNG GIAO THƯƠNG, NGOẠI GIAO & TÍNH XÃ HỘI (TRADE & DIPLOMACY)

1. **Chợ Đen Phế Liệu (Post-Apocalyptic Barter Hub):** Trao đổi tự do Bản thiết kế, Mô-đun, Điện, Nước tản nhiệt. Tỷ giá tự động biến động theo nhu cầu từng Mùa.
2. **Tuyến Xe Tải Tiếp Tế (Supply Convoys) & Viện Trợ:** Cho thuê Tướng hoặc Tháp Mô-đun sang Căn cứ đồng đội. Sự kiện Cướp Xe Tải (PvE/PvP) yêu cầu cử Tướng ra hộ tống.
3. **Hệ thống Uy Tín & Ngoại Giao (Reputation System):** Tích lũy điểm tin cậy để giảm thuế Chợ Đen và mở rộng ô vận tải.

---

## VII. NÂNG CẤP GAMEPLAY & HỆ THỐNG TƯỚNG (HERO & MECHANICS)

1. **Tướng Chỉ Huy (Commander Units):** Trực tiếp điều khiển Kỹ sư (Sửa tháp, tản nhiệt), Cảnh binh (Bắn shotgun, cắm cọc choáng), Chuyên gia sinh học (Thu nhặt phế liệu chế thuốc buff).
2. **Kỹ Năng Kích Hoạt Toàn Cầu (Global Tactical Strikes):** Gọi xe tải bọc thép san phẳng đường, Mưa pháo Nitơ đóng băng diện rộng, hoặc Thả thùng đạn khẩn cấp.

---

## VIII. HỆ THỐNG KINH TẾ, ĐÁ QUÝ & MONETIZATION

```
┌──────────────────┐                     ┌──────────────────┐
│   Xu Phế Liệu    │ (In-Game)           │ ĐÁ KIM CƯƠNG RAD │ (Gems / IAP)
└────────┬─────────┘                     └────────┬─────────┘
         │                                        │
         ├── Nâng cấp Tháp cơ bản                 ├── Mua Skin Tháp/Tướng/Xe
         ├── Sửa chữa Xe Căn cứ                   ├── Mở Vé Sinh Tồn Bang Hội
         └── Mua đạn dược                         └── Quay Bản Thiết Kế Hiếm
```

1. **Phân Loại Tiền Tệ:** Xu Phế Liệu (Nâng cấp trận đánh), Điểm Biomass (Nghiên cứu cây công nghệ vĩnh viễn), Đá Kim Cương Phóng Xạ - Rad-Gems (Tiền tệ cao cấp).
2. **Monetization Hooks:** Quay Gacha Bản thiết kế mô-đun hiếm (có hệ thống bảo hiểm Pity), Vé Sinh Tồn Mùa Bang Hội (Alliance Season Pass), Skin/Visual effects, và các gói tiện ích QoL.

---

## IX. CHIẾN TRƯỜNG BANG HỘI: TRANH GIÀNH LÃNH THỔ & PVP (TERRITORY CONTROL)

```
       [BẢN ĐỒ THẾ GIỚI - LƯỚI Ô LỤC GIÁC 2.5D (HEX GRID)]
                            │
       ┌────────────────────┼────────────────────┐
       ▼                    ▼                    ▼
[Mỏ Lithium Cao Cấp]  [Trạm Vệ Tinh Vũ Trụ]  [Cảng Phế Liệu Biển]
```

### 1. Bản Đồ Thế Giới Ô Lục Giác (Hex-Grid World Map)
Thế giới chia thành hàng trăm Hexes chứa tài nguyên chiến lược (Trạm vệ tinh, Mỏ Hạt nhân, Cảng phế liệu).

### 2. Cơ Chế Bất Đối Xứng: Công Thành vs Thủ Thành (PvP War System)
* **Phe Phòng Thủ (Guild Defense):** Có 24 giờ thiết lập Mê cung 2.5D, giăng đường ray, cắm máy phát điện và đặt các **Tháp Chiến Tranh Bang Hội**.
* **Phe Tấn Công (Guild Attackers):** Dùng thiết bị phát sóng nuôi/thả các đàn quái vật (Crawler, Behemoth...) đâm vào căn cứ phe Thủ, đồng thời cử Tướng phá hủy đường truyền đạn của phe Thủ.

---

## X. DANH SÁCH THÁP CHIẾN TRANH BANG HỘI (WAR MONUMENTS PVP)

Tháp Chiến Tranh Bang Hội là các công trình phòng thủ/hỗ trợ cỡ lớn, ngốn cực nhiều tài nguyên của Bang để xây dựng và duy trì. Mỗi trận PvP, phe Thủ chỉ được đặt tối đa 3 Tháp Chiến Tranh.

```
                  ┌────────────────────────┐
                  │ THÁP CHIẾN TRANH PVE/PVP│
                  └───────────┬────────────┘
                              │
     ┌────────────────┬───────┴───────┬────────────────┐
     ▼                ▼               ▼                ▼
[Trạm EMP]     [Pháo Hạt Nhân]   [Tháp Thu Phục]  [Khiên Aegis]
(Gây Choáng)   (Sát Thương Khối) (Thu Phục Quái)   (Tăng Bảo Vệ)
```

| STT | Tên Tháp Chiến Tranh | Cơ Chế Hoạt Động & Tác Động PvP |
| :---/ | :--- | :--- |
| **1** | **Lò Phản Ứng EMP Lĩnh Vực (Pulse Core)** | Định kỳ 45 giây xả ra một làn sóng xung điện toàn bản đồ. Làm tê liệt kỹ năng Tướng công thành và làm nhiễu thiết bị thả quái của phe Công trong 10 giây. |
| **2** | **Đại Pháo Hạt Nhân Căn Cứ (Doomsday Silo)** | Cần **2-3 người chơi** phe Thủ cùng đứng vào bệ điều khiển để nạp đạn. Bắn một quả tên lửa hạt nhân xóa sổ toàn bộ đợt quái công thành và làm thương vong nặng Tướng đối phương. |
| **3** | **Trạm Thần Kinh Thu Phục (Neural Parasite)** | Phun mây Bào Tử Khống Chế. Thu phục $20\%$ số quái vật mà phe Công thả ra, biến chúng thành quân phòng thủ quay lại tấn công Tướng phe Công. |
| **4** | **Tháp Hào Quang Vệ Binh (Aegis Bastion)** | Tạo một vòm khiên năng lượng bảo vệ các tháp thường xung quanh. Đồng thời liên tục phát sóng hồi $3\%$ máu/giây cho toàn bộ công trình trong bán kính 20m. |
| **5** | **Pháo Laser Quỹ Đạo Bắn Điểm (Orbital Array)** | Khóa mục tiêu vào Tướng hoặc Quái Trùm của phe Công. Xả chùm Laser nhiệt độ cao từ vệ tinh liên tục theo thời gian, sát thương tăng dần theo thời gian chiếu. |
| **6** | **Máy Phá Sóng Địa Chấn (Seismic Disruptor)** | Phát ra tần số rung đất cường độ cao. Làm sập toàn bộ các đường hầm do Quái Đào Hầm (Burrower) tạo ra và làm chậm $60\%$ tốc độ di chuyển của phe Công. |

---

## XI. CÂY CÔNG NGHỆ NÂNG CẤP DÀI HẠN (BASE TECH TREE)

Cây Công Nghệ là tiến trình phát triển vĩnh viễn của tài khoản/căn cứ chính. Sử dụng tài nguyên **Điểm Biomass** (đốt xác quái) và **Linh Kiện Cao Cấp** (nhặt từ Thiên tai/PvP) để mở khóa.

```
                             [TRUNG TÂM NGHIÊN CỨU]
                                       │
        ┌───────────────────┬──────────┴───────────┬───────────────────┐
        ▼                   ▼                      ▼                   ▼
[NHÁNH A: HỎA LỰC]  [NHÁNH B: HẬU CẦN]     [NHÁNH C: SINH TỒN]  [NHÁNH D: QUÂN SỰ]
 (Vũ khí/Tháp)       (Năng lượng/Ray)      (Lương thực/Dân)     (Lính gác/Viễn chinh)
```

### 1. Nhánh A: Hỏa Lực & Vũ Khí (Weapons & Towers)
* **Tier 1:** Mở khóa Nòng Súng Đóng Đinh, Tăng $10\%$ sát thương tháp cơ bản.
* **Tier 2:** Mở khóa Lõi Tiện Ích Nam Châm, tăng dung lượng băng đạn tháp $+25\%$.
* **Tier 3:** Mở khóa cơ chế **Phản Ứng Nguyên Tố** (Axit + Lửa = Bộc nổ; Băng + Điện = Choáng diện rộng).
* **Tier 4:** Mở khóa Công nghệ Tháp Treo Trần & Tháp Ray Di Động.
* **Tier 5 (Ultimate):** Mở khóa **Bản Thiết Kế Tháp Siêu Cấp (Gatling Laser / Pháo Nitơ)**.

### 2. Nhánh B: Hậu Cần & Năng Lượng (Logistics & Power)
* **Tier 1:** Mở khóa Máy Phát Điện Chạy Dầu, tăng $20\%$ tốc độ chạy của Xe Tiếp Đạn.
* **Tier 2:** Mở khóa Tấm Năng Lượng Mặt Trời (Tạo điện miễn phí vào ban ngày).
* **Tier 3:** Mở khóa **Drone Tiếp Tế Tự Động** (Tự bay đi tiếp đạn cho tháp mà không cần tuyến ray).
* **Tier 4:** Mở khóa Hệ thống Tản Nhiệt Bằng Nước (Giảm $50\%$ nguy cơ Quá Nhiệt Mùa Hạ).
* **Tier 5 (Ultimate):** Mở khóa **Hạt Nhân Mini** (Cung cấp năng lượng vĩnh viễn cho toàn bộ Căn Cứ).

### 3. Nhánh C: Sinh Tồn & Nông Nghiệp (Survival & Habitat)
* **Tier 1:** Mở khóa Nông Trại Thủy Canh Nông Dân, tăng sức chứa Dân tị nạn ($+5$ người).
* **Tier 2:** Mở khóa Trạm Lọc Nước Axit, tăng $15\%$ Tinh Thần Căn Cứ.
* **Tier 3:** Mở khóa Nhà Chế Biến Biomass (Chuyển xác quái thành Lương thực tổng hợp).
* **Tier 4:** Mở khóa **Trụ Sức Khỏe & Âm Nhạc** (Ngăn ngừa cư dân nổi loạn / bãi công).
* **Tier 5 (Ultimate):** Mở khóa **Mái Che Năng Lượng Khổng Lồ** (Bảo vệ toàn bộ Căn cứ khỏi Mưa Axit & Mưa Thiên Thạch).

### 4. Nhánh D: Quân Sự & Viễn Chinh (Military & Expedition)
* **Tier 1:** Cho phép huấn luyện Dân Quân Cầm Súng (Guard Squads).
* **Tier 2:** Mở khóa Đội Xe Trinh Sát Viễn Chinh (Tự động thu thập phế liệu khi Offline).
* **Tier 3:** Trang bị Giáp Bọc Thép cho Xe Tải Buôn Chợ Đen.
* **Tier 4:** Cho phép Tướng mang theo 2 Trợ Lý Robot (Combat Drones).
* **Tier 5 (Ultimate):** Mở khóa **Quyền Năng Chỉ Huy Pháo Binh Quỹ Đạo**.

---

## XII. CƠ CHẾ SINH TỒN, NÔNG NGHIỆP & LƯƠNG THỰC (SURVIVAL SYSTEM)

Căn cứ không chỉ là một pháo đài quân sự mà còn là khu định cư của những người sống sót. Để duy trì sức chiến đấu, người chơi phải duy trì chỉ số **Lương Thực & Nước Sạch**.

```
                  ┌────────────────────────┐
                  │ HỆ THỐNG NGUỒN SỐNG    │
                  └───────────┬────────────┘
                              │
         ┌────────────────────┴────────────────────┐
         ▼                                         ▼
┌──────────────────┐                     ┌──────────────────┐
│ Trạm Lọc Nước    │ ───(Cung cấp)───>   │ Nông Trại Thủy   │
│ Axit / Băng      │                     │ Canh Dị Thảo     │
└────────┬─────────┘                     └────────┬─────────┘
         │                                        │
         ▼                                        ▼
┌──────────────────────────────────────────────────────────┐
│                   TỈ LỆ TỊNH DÙNG                        │
│ 1 Cư Dân = 1 Lương Thực + 1 Nước Sạch / Ngày Trôi Qua   │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
              ┌──────────────────────────┐
              │  TỈ LỆ TINH THẦN (MORALE) │
              └──────────────────────────┘
```

### 1. Sản Xuất Lương Thực & Nước Sạch
* **Nông Trại Thủy Canh (Hydroponics Bay):** Trồng các loại nấm biến dị và cây trồng ngắn ngày.
  * *Ảnh hưởng Mùa:* Mùa Xuân tăng $100\%$ sản lượng trồng trọt; Mùa Đông đóng băng nông trại nếu không có Tháp Nhiệt sưởi ấm.
* **Trạm Lọc Nước (Water Purifier):** Lọc nước bẩn / mưa axit thành Nước Sạch. Nước sạch vừa dùng cho Cư Dân uống, vừa làm **Nước Tản Nhiệt** hạ nhiệt cho Tháp hỏa lực.

### 2. Chỉ Số Tinh Thần Căn Cứ (Base Morale System)
Chỉ số Tinh thần ($0\% - 100\%$) quyết định trực tiếp hiệu suất làm việc của toàn căn cứ:

$$\text{Tinh Thần} = f(\text{Đủ Lương Thực}, \text{Đủ Nước}, \text{An Toàn Căn Cứ}, \text{Thời Tiết})$$

* **Tinh thần High ($>80\%$):** Tốc độ nạp đạn tháp $+15\%$, tốc độ nghiên cứu Tech Tree $+20\%$.
* **Tinh thần Low ($<30\%$):** Cư dân đình công, không chịu sản xuất đạn; Tướng bị giảm $25\%$ tốc độ di chuyển.
* **Mất Kiểm Soát ($0\%$):** Cư dân nổi loạn hoặc bỏ trốn khỏi Căn Cứ, mang theo một lượng Xu Phế Liệu.

---

## XIII. HỆ THỐNG CƯ DÂN, NHÂN LỰC & QUÂN ĐỘI (CITIZENS & MILITARY)

Cư dân là nguồn nhân lực quý giá điều hành toàn bộ hạ tầng hậu cần phía sau chiến tuyến.

```
                      [DÂN TỊ NẠN VÀO CĂN CỨ]
                                 │
        ┌────────────────────────┼────────────────────────┐
        ▼                        ▼                        ▼
[CÔNG NHÂN NHÀ MÁY]      [NÔNG DÂN / KỸ SƯ]       [LÍNH GÁC VỆ BINH]
(Sản xuất đạn/Sửa tháp)  (Trồng trọt/Lọc nước)    (Tuần tra/Diệt quái chui)
```

### 1. Thu Nhập Cư Dân (Refugees Recruitment)
* Cư dân mới đến từ việc giải cứu trong các nhiệm vụ Viễn Chinh, hoặc thu hút từ các Trạm Radio Phát Sóng.
* Mỗi Cư Dân có các chỉ số ẩn: *Sức Khỏe, Tay Nghề Kỹ Thuật, Độ Trung Thành*.

### 2. Phân Công Lao Động (Job Allocation)
Người chơi phân bổ dân số vào 4 nhóm công việc:
1. **Nhóm Hậu Cần & Đạn Dược:** Trực tiếp làm việc trong Kho Đạn để đúc đạn cho Tháp.
2. **Nhóm Nông Dân & Nước:** Chăm sóc Nông trại Thủy canh và vận hành Bơm nước.
3. **Nhóm Kỹ Sư Sửa Chữa:** Tự động cầm cờ-lê đi sửa chữa các đoạn Tường Barricade và Tháp bị hư hại sau mỗi đợt tấn công.
4. **Nhóm Viễn Chinh (Scavengers):** Rời căn cứ ra ngoài bản đồ thế giới để cày tài nguyên khi người chơi thoát game (Offline Rewards).

### 3. Lực Lượng Vệ Binh / Quân Đội (Base Militia)
* Khi quái vật **Tunnel Burrower (Sâu Đào Hầm)** chui nổ ngay giữa lòng Căn Cứ, tháp bên ngoài không thể bắn vào trong.
* Người chơi huy động Cư dân chuyển thành **Lính Gác Vệ Binh (Outpost Guards)** được trang bị súng rifle/shotgun để bảo vệ Kho Đạn và Trạm Lọc Nước bên trong lòng Căn cứ.
* **Kỹ năng Động Viên Khẩn Cấp (Conscription):** Trong tình huống sinh tồn cận kề cái chết, kích hoạt động viên toàn bộ Cư Dân cầm vũ khí lao ra tuyến đầu làm vật chắn sống.

---

## XIV. MÔ PHỎNG MÀN CHƠI THỰC TẾ (GAMEPLAY LOOP CẬP NHẬT TOÀN DIỆN)

1. **Giai đoạn Chuẩn bị (Ban ngày - 3 phút):**
   * Đang là **Mùa Hạ**. Tháp quá nhiệt rất nhanh. Tinh thần cư dân sụt giảm do thiếu Nước Sạch.
   * Phân công 5 Cư dân vào Trạm Lọc Nước, 10 Cư dân vào Nhà Máy Sản Xuất Đạn.
   * Lên Chợ Đen đổi Năng Lượng Mặt Trời thừa lấy **Bản Thiết Kế Lò Phản Ứng EMP** từ Bang hội đồng minh.
   * Lấy phế liệu đặt 1 đường ray nối Kho Đạn ra hẻm núi. Bố trí Tháp Axit ở đầu hẻm và Tháp Bắn Tỉa treo trên vách đá.

2. **Giai đoạn Giao tranh & Thiên Tai (Ban đêm - 5 phút):**
   * *Đợt 1:* Crawler tràn vào. Tháp Axit phun xả làm mềm giáp.
   * *Cảnh Báo Thiên Tai:* Trận **Mưa Axit Đậm Đặc** bất ngờ bùng phát, làm tụt máu các tháp ngoài trời.
   * *Xử lý:* Tướng Kỹ Sư chạy ra kích hoạt Mái Che Energy và điều nhóm Kỹ Sư Cư Dân ra dội Nước Tản Nhiệt hạ nhiệt cho tháp.
   * *Đợt 2:* **EMP Stalker** xuất hiện làm sập lưới điện. Đồng thời **Tunnel Burrower** chui lên ngay tại Trạm Thủy Canh.
   * *Xử lý:* Huy động **Đội Lính Gác Vệ Binh** cầm Shotgun xả đạn tiêu diệt Burrower bên trong lòng căn cứ. Dùng Viện Trợ Bang Hội thả Tháp Đóng Đinh Cơ Khí không xài điện ngoài mặt trận.
   * *Sự cố:* Corpse Weaver đắp Cầu Xác cho Iron-Hide Behemoth vượt tường.
   * *Xử lý:* Kích hoạt Tháp Phun Dầu, đốt cháy Cầu Xác và thả Mưa Pháo Nitơ đóng băng Behemoth.

3. **Tổng kết, Giao thương & PvP (Rạng sáng):**
   * Tái chế xác quái thành Biomass để nâng cấp Tier 3 Cây Công Nghệ (Mở khóa Drone Tiếp Tế Tự Động).
   * Cư dân hoan hô vì căn cứ an toàn, chỉ số Tinh Thần tăng lên $95\%$.
   * Nhận Xu Phế Liệu và $50$ **Rad-Gems** thưởng hoàn thành màn chơi đạt 3 sao.
   * Mở Bản đồ Thế giới Hex-Grid 2.5D, cùng Bang hội dàn xếp **Tháp Chiến Tranh Doomsday Silo** chuẩn bị bước vào trận Tranh Giành Mỏ Lithium với Bang hội đối thủ.