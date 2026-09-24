# LAST OUTPOST — WORLD & NARRATIVE BIBLE v1.0

**Version:** v1.0  
**Status:** DESIGN / SOURCE-OF-TRUTH CANDIDATE  
**Scope:** Worldbuilding, Narrative, Lore, Character/Faction framing, Dialogue, Naming, In-Game Story Delivery  
**Owner:** Game Design / Narrative Design  
**Implementation:** Claude chỉ triển khai sau khi design được chốt  
**Engine:** Không khóa engine  

---

## 0. MỤC ĐÍCH

Tài liệu này định nghĩa **thế giới, nguyên nhân hậu tận thế, timeline, khu vực, nhóm cư dân, Commander, enemy lore, tone kể chuyện và nguyên tắc narrative** của LAST OUTPOST.

Mục tiêu không phải biến LAST OUTPOST thành story-heavy RPG. Narrative tồn tại để:

- tạo ngữ cảnh cho gameplay;
- làm cho Outpost có cảm giác là một nơi thật sự đang sống;
- giải thích vì sao các biome, enemy, hazard, resource và công nghệ tồn tại;
- tạo động lực khám phá và mở khóa;
- giúp mỗi Chapter/Mission có lý do tồn tại;
- bổ sung personality cho Commander và cư dân;
- tạo nền cho Season / Event / Expansion mà không phá core loop.

### Narrative North Star

> **Người chơi không phải đang “cứu thế giới” bằng một chuyến phiêu lưu duy nhất. Họ đang từng bước xây lại một nơi có thể tồn tại lâu dài trong một thế giới đã hỏng.**

---

# I. CANON PRIORITY & DESIGN BOUNDARIES

## 1.1. Quy tắc nguồn

Tài liệu này kế thừa các quyết định hiện tại từ:

- `Last_Outpost_Toi_Uu_v1.2.md` — ưu tiên cao nhất trong nhóm tối ưu hóa gameplay hiện có.
- `Last_Outpost_System_Gameplay_v1.1.md`.
- `Last_Outpost_Core_Combat_Gameplay_v1.0.md`.
- `Last_Outpost_Content_Specification_v1.0.md`.
- `Last_Outpost_Player_Experience_UX_Flow_v1.0.md`.
- `Last_Outpost_UI_UX_Specification_v1.0.md`.
- `Last_Outpost_Visual_Identity_Art_Direction_v1.0.md`.
- `Last_Outpost_Visual_Reference_Pack_v1.0`.
- `Last_Outpost_Vertical_Slice_Specification_v1.0.md`.
- `Last_Outpost_Production_Content_Bible_v1.0.md`.
- `Last_Outpost_Content_Production_Pack_v1.0.md`.
- `Last_Outpost_Playtest_Balance_Specification_v1.0.md`.
- `Last_Outpost_Claude_Handoff_Pack_v1.0`.

## 1.2. Những điều Narrative tuyệt đối không được làm trái

1. Visual: **Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy**.
2. Core loop: **BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN**.
3. Battle: khoảng **5–15 phút**.
4. Mission dùng **Fixed Enemy Roster**; narrative không được dùng lore để biện minh cho việc spawn loài ngoài roster.
5. Tower = **Base + Barrel + Module**.
6. Outpost là progression **persistent**, không reset theo season thông thường.
7. Seasons / Events có thể tạo lớp nội dung tạm thời nhưng không xóa permanent progression.
8. PvE là ưu tiên chính; Guild/PvP là lớp endgame bổ sung.
9. Không biến game thành RPG tuyến tính nhiều cutscene, dialogue tree hoặc quest hub phức tạp.
10. Không dùng lore để ép người chơi đọc văn bản dài trước khi được chơi.

---

# II. NARRATIVE IDENTITY

## 2.1. Thể loại kể chuyện

LAST OUTPOST sử dụng mô hình:

**Environmental Storytelling + Tactical Mission Framing + Character Barks + Codex + Outpost Progression.**

Không sử dụng mô hình chính:

- cinematic RPG;
- visual novel;
- dialogue-heavy survival sim;
- branching morality RPG;
- hero-centric chosen-one story.

## 2.2. Cảm giác chủ đạo

### Friendly Apocalypse

Thế giới đã mất rất nhiều thứ, nhưng chưa mất hết con người.

Người chơi nên thường xuyên thấy:

- ánh đèn của Outpost trong đêm;
- cư dân sửa máy;
- xe tiếp tế chạy qua;
- cây cối mọc lại giữa bê tông;
- biển báo cũ;
- những thứ được tái sử dụng thay vì vứt bỏ;
- dấu vết của đời sống cũ xen với công nghệ tự chế mới.

### Hy vọng có giá

Hy vọng không phải là “mọi thứ sẽ ổn”.

Hy vọng là:

> **mỗi lần nâng cấp Outpost, thế giới xung quanh thật sự bớt nguy hiểm một chút.**

### Tông cảm xúc

- Ấm áp ở Outpost.
- Tập trung và căng thẳng ở Battlefield.
- Tò mò khi khám phá.
- Hài hước nhẹ khi phù hợp.
- Đáng sợ theo hướng nguy hiểm và bất ngờ, không theo horror/gore.
- Không nihilistic.

---

# III. WORLD PREMISE

## 3.1. Những gì đã được tài liệu hiện tại hỗ trợ

Các tài liệu hiện tại đã xác lập một thế giới có:

- thành phố đổ nát;
- khu công nghiệp bỏ hoang;
- mỏ đá và địa hình dốc;
- đầm lầy độc / phóng xạ;
- vùng băng giá và trạm nghiên cứu bỏ hoang;
- máy móc, drone, rail và logistics cũ;
- sinh vật biến dị;
- các hazard môi trường;
- các cộng đồng sống sót;
- Outpost có thể tiến hóa từ **Survivor Camp → Last Bastion**.

Tuy nhiên, tài liệu hiện tại **chưa chốt một canon cụ thể** về nguyên nhân chính xác khiến thế giới sụp đổ hoặc timeline lịch sử.

## 3.2. ĐỀ XUẤT MỚI — Nguyên nhân hậu tận thế

### Tên tạm: “Sự Sụp Đổ”

**Đây là đề xuất mới, chưa phải canon đã chốt.**

Trước Sự Sụp Đổ, con người đã xây dựng một mạng lưới hạ tầng tự động quy mô lớn để xử lý ba vấn đề cùng lúc:

- phục hồi môi trường;
- vận chuyển và tái chế tài nguyên;
- duy trì các vùng dân cư trong điều kiện khí hậu ngày càng bất ổn.

Mạng lưới này phụ thuộc vào:

- hệ thống sinh học hỗ trợ xử lý chất thải / tái tạo môi trường;
- cơ sở nghiên cứu;
- hệ thống năng lượng;
- logistics tự động;
- mạng điều phối và cảnh báo.

Một chuỗi lỗi liên hoàn đã xảy ra. Thay vì một “vụ nổ duy nhất”, thế giới sụp đổ theo kiểu **cascading failure**:

**hạ tầng → logistics → năng lượng → môi trường → sinh thái → xã hội.**

Hệ quả cuối cùng là:

1. các thành phố mất điện và nước ổn định;
2. chuỗi cung ứng đứt gãy;
3. các hệ sinh học nhân tạo mất kiểm soát ở một số khu vực;
4. môi trường xuất hiện các vùng độc / bào tử / axit / biến đổi sinh học;
5. các hệ thống máy móc cũ hoạt động sai, bị chiếm dụng hoặc bị biến đổi;
6. các cộng đồng sống sót rút về các căn cứ nhỏ và điểm phòng thủ.

### Tại sao nguyên nhân này phù hợp game

- Giải thích được cả enemy hữu cơ lẫn enemy gắn với công nghệ.
- Giải thích được toxic biome và spore.
- Giải thích được mạng logistics, rail, drone và research facility.
- Cho phép world có cảm giác hậu tận thế nhưng không cần fantasy magic.
- Cho phép nhiều loại biome tồn tại tự nhiên.
- Cho phép player tìm hiểu quá khứ mà không cần một “villain chính” sống sót đứng sau mọi thứ.

### Ràng buộc

Không khẳng định rằng mọi sinh vật đều do con người “tạo ra”. Một phần mutation phải được hiểu là **hệ quả sinh thái sau Sự Sụp Đổ**, không phải tất cả đều là thí nghiệm.

---

# IV. TIMELINE

> **ĐỀ XUẤT MỚI — timeline dưới đây là canon candidate.**

## Era 0 — Before

Thế giới hiện đại phụ thuộc mạnh vào:

- tự động hóa;
- hệ thống năng lượng phân tán;
- mạng logistics;
- các cơ sở nghiên cứu;
- các hệ thống phục hồi môi trường.

Con người vẫn sống trong các thành phố và khu công nghiệp lớn. Hạ tầng phát triển nhanh hơn khả năng kiểm soát toàn bộ tác động của nó.

## Era 1 — The Cascade

Một chuỗi lỗi làm nhiều hệ thống phụ thuộc lẫn nhau thất bại trong thời gian ngắn.

Không có một ngày “mọi thứ biến mất”. Thay vào đó:

- điện mất từng khu vực;
- nước bị gián đoạn;
- kho dự trữ bị cô lập;
- drone/rail logistics ngừng ổn định;
- chính quyền và lực lượng cứu hộ bị chia nhỏ;
- các vùng môi trường bắt đầu biến đổi.

## Era 2 — The Retreat

Các cộng đồng rời các thành phố lớn.

Ưu tiên chuyển từ:

**phát triển → duy trì → sống sót.**

Những căn cứ tạm thời xuất hiện quanh:

- kho tài nguyên;
- trạm nghiên cứu;
- nhà máy;
- giao lộ logistics;
- vùng có nước sạch.

## Era 3 — The Wilding

Hệ sinh thái mới hình thành.

Các loài biến dị trở nên ổn định hơn và bắt đầu có vai trò sinh thái riêng:

- swarm;
- scavenger;
- heavy;
- infiltrator;
- support;
- air harasser;
- biological spreader;
- apex/boss.

Con người bắt đầu nhận ra rằng đây không phải một đợt khủng hoảng sẽ tự biến mất nhanh chóng.

## Era 4 — The Outpost Age

Giai đoạn của người chơi.

Không còn mục tiêu khôi phục ngay “thế giới cũ”.

Mục tiêu trước mắt là:

> **xây một Outpost đủ mạnh để bảo vệ người, tái sử dụng hạ tầng và mở lại từng tuyến kết nối một.**

## Era 5 — The Reconstruction

Đây là hướng phát triển dài hạn của Campaign:

- mở vùng;
- khôi phục logistics;
- khám phá technology remnants;
- hiểu sâu hơn về Sự Sụp Đổ;
- kết nối các cộng đồng;
- xây dựng một nền văn minh mới thay vì sao chép nguyên trạng thế giới cũ.

---

# V. WORLD STRUCTURE

## 5.1. Hierarchy

```text
WORLD
└── REGION
    └── BIOME
        └── CHAPTER
            └── MISSION
                └── WAVE / ENCOUNTER
```

Narrative phải cùng cấu trúc với content structure hiện tại.

## 5.2. Region và Biome không chỉ là skin

Mỗi biome phải trả lời 4 câu:

1. Con người từng dùng nơi này để làm gì?
2. Vì sao nơi này vẫn có giá trị sau Sự Sụp Đổ?
3. Vì sao enemy/hazard phù hợp với nơi này?
4. Người chơi học được điều gì về thế giới khi đi qua đây?

---

# VI. BIOME & WORLD STORY

## 6.1. Ruined Urban Core

**Vai trò narrative:** điểm bắt đầu chính của Vertical Slice và là nơi người chơi lần đầu nhìn thấy quy mô của thế giới đã mất.

**Hình ảnh:** thành phố sụp đổ, xe bỏ hoang, container, bê tông nứt, cây cối mọc xen, signage cũ.

**Gameplay identity:**
- lane phức tạp;
- cover;
- scavenged objects;
- phù hợp Harpy / Riot Mech / Burrower.

**Narrative question:**

> “Một thành phố từng có hàng triệu người giờ còn lại gì?”

**Story delivery:**

- biển báo cũ;
- màn hình chết;
- trạm tiếp tế bỏ hoang;
- nhật ký logistics ngắn;
- các vật dụng dân sự.

Không biến thành bảo tàng lore.

## 6.2. Abandoned Industrial Zone

**Vai trò narrative:** nơi cho người chơi thấy nền văn minh cũ từng vận hành bằng công nghiệp và logistics.

**Fantasy:** nhà máy, kho thép, đường ray, machinery, container.

**Gameplay identity:**
- nhiều buildable surface;
- logistics thuận lợi;
- Scrap nổi bật;
- vật cản nhân tạo.

**Narrative question:**

> “Con người từng xây mọi thứ này để phục vụ điều gì?”

**Lore focus:**
- supply network;
- old manufacturing;
- maintenance culture;
- abandoned automation.

## 6.3. Rocky Quarry

**Vai trò narrative:** chứng minh con người vẫn còn thể xây dựng và khai thác giữa điều kiện khắc nghiệt.

**Gameplay identity:**
- chênh cao;
- Wall/Cliff Mount;
- lane hẹp;
- terrain-based counterplay.

**Narrative question:**

> “Khi đường lớn biến mất, tài nguyên được vận chuyển bằng cách nào?”

Lore có thể xuất hiện qua:

- đường ray mỏ;
- trạm nâng;
- bản đồ khai thác;
- khu trú của đội salvage.

## 6.4. Toxic Wetland

**Vai trò narrative:** nơi người chơi nhận ra Sự Sụp Đổ không chỉ phá hủy thành phố — nó đã thay đổi hệ sinh thái.

**Fantasy:** nước độc, cây biến dị, spore, vùng ngập.

**Gameplay identity:**
- Water quan trọng;
- Toxic Spore hazard pool;
- movement lane hạn chế.

**Narrative question:**

> “Thứ gì ở đây còn được gọi là tự nhiên?”

Lore phải giữ cân bằng: không biến biome thành horror biome.

## 6.5. Frozen Wasteland

**Vai trò narrative:** lưu giữ các dấu vết nghiên cứu và hạ tầng cũ được bảo tồn nhờ băng giá.

**Gameplay identity:**
- Heat giảm nhanh;
- logistics chậm / đóng băng;
- Power / Heating quan trọng.

**Narrative question:**

> “Có những thứ con người đã cố cứu mà chưa kịp cứu không?”

Đây là một nơi phù hợp để mở rộng các discovery về Sự Sụp Đổ trong mid/late campaign.

---

# VII. SURVIVOR SOCIETY

## 7.1. Nguyên tắc

Không xây xã hội thành nhiều faction phức tạp ở v1.

Gameplay hiện tại chỉ cần:

- cư dân;
- reputation;
- trade;
- expedition;
- guild/co-op ở giai đoạn sau.

## 7.2. ĐỀ XUẤT MỚI — Ba kiểu cộng đồng

Đây là **văn hóa xã hội**, không phải ba faction gameplay bắt buộc.

### Salvagers

Những người chuyên:

- tháo dỡ;
- sửa chữa;
- tìm Scrap;
- khôi phục máy móc.

Tính cách thường:
- thực tế;
- thích đồ dùng được;
- ít lãng mạn hóa quá khứ.

### Wardens

Những người chuyên:

- bảo vệ tuyến;
- hộ tống;
- canh gác;
- giữ dân cư an toàn.

Tính cách thường:
- kỷ luật;
- ít nói;
- coi trọng sự ổn định.

### Researchers

Những người chuyên:

- nghiên cứu Biomass;
- ghi nhận mutation;
- khôi phục dữ liệu;
- tìm hiểu môi trường.

Tính cách thường:
- tò mò;
- cẩn trọng;
- đôi khi tranh luận về mức độ có thể can thiệp vào tự nhiên mới.

### Không biến ba nhóm này thành “RPG factions”

Không cần:

- faction tree;
- faction level riêng;
- faction war;
- hàng chục reputation bar.

Chúng chủ yếu giúp tạo personality cho NPC, dialogue, Expedition và Event.

---

# VIII. THE OUTPOST

## 8.1. Ý nghĩa narrative

Outpost không phải menu có skin.

Outpost là **nhân vật lớn nhất của game** theo nghĩa thế giới.

Nó thay đổi từ:

**Survivor Camp → Fortified Outpost → Industrial Outpost → Advanced Fortress → Last Bastion.**

Mỗi stage phải thể hiện bằng:

- hình khối lớn hơn;
- infrastructure rõ hơn;
- ánh sáng tốt hơn;
- logistics hoạt động tốt hơn;
- nhiều người hơn;
- nhiều hoạt động đời thường hơn;
- ít cảm giác tạm bợ hơn.

## 8.2. Narrative rule

Upgrade phải kể chuyện bằng hình ảnh trước, text sau.

Ví dụ:

> một generator mới không cần một đoạn dialogue dài để nói “Outpost đã có điện”. Người chơi nhìn thấy đèn bật và drone hoạt động.

## 8.3. Outpost không phải pháo đài cô lập mãi mãi

Về narrative dài hạn, Outpost dần trở thành:

**nơi trú ẩn → căn cứ → trung tâm logistics → cộng đồng → một mầm của nền văn minh mới.**

---

# IX. COMMANDER BIBLE

## 9.1. Quy tắc chung

Commander là người giúp gameplay có personality, không phải “chosen one”.

Không ai trong ba class nên có khả năng tự mình cứu thế giới.

### Engineer

**Role:** sửa chữa, logistics, xử lý heat, dựng công trình tạm.

**Narrative identity:**
Một người hiểu rằng thế giới không chết vì thiếu súng, mà vì những hệ thống tưởng như nhỏ đã ngừng hoạt động cùng lúc.

**Tính cách:**
- thực tế;
- bình tĩnh;
- thích giải quyết vấn đề bằng cách nhìn hệ thống.

**Dialogue voice:** ngắn, cụ thể, đôi khi có humor khô.

### Guardian

**Role:** cận chiến, tuyến trong, chống đột nhập.

**Narrative identity:**
Một người coi Outpost trước hết là nơi có những người cần được sống sót.

**Tính cách:**
- chắc chắn;
- bảo vệ;
- ít quan tâm đến lý thuyết hơn thực tế.

**Dialogue voice:** trực tiếp, rõ, đôi khi cứng nhưng không lạnh lùng.

### Bio Specialist

**Role:** Biomass, survival buff, đối phó mutation.

**Narrative identity:**
Một field researcher học cách sống cùng một hệ sinh thái đã không còn giống quá khứ.

**Tính cách:**
- tò mò;
- resourceful;
- quan sát kỹ;
- đôi khi thận trọng quá mức.

**Dialogue voice:** giải thích đơn giản điều phức tạp; không đọc bài giảng khoa học.

## 9.2. Commander hierarchy

Tên cá nhân cụ thể có thể được thiết kế ở Character Content Pack sau.

Trong v1 của Narrative Bible:

- **Class** là gameplay identity.
- **Character name / portrait / backstory** là content layer.
- Không để backstory thay đổi luật combat.

---

# X. ENEMY LORE

## 10.1. Triết lý

Enemy không phải “race phản diện”.

Mỗi species là một **problem made alive**.

Mỗi enemy phải có:

- ecological/functional role;
- gameplay signature;
- visual signature;
- lý do tồn tại trong biome phù hợp.

## 10.2. Nhóm A — Basic

### Crawler

Một loài nhỏ, nhanh, thích nghi tốt với khu đô thị và vùng có nhiều debris.

Narrative purpose:
- đại diện cho việc hệ sinh thái mới đã trở thành bình thường.

### Acid Bloater

Một sinh vật tích tụ chất ăn mòn trong cơ thể.

Narrative purpose:
- cho thấy môi trường hậu Sụp Đổ đã ảnh hưởng trực tiếp lên sinh vật.

### Iron-Hide Behemoth

Một loài heavy đã hình thành lớp giáp sinh học cứng.

Narrative purpose:
- biểu tượng cho sinh vật đã thích nghi với thế giới đầy vật liệu nguy hiểm và cạnh tranh.

### Tunnel Burrower

Sinh vật thích nghi dưới mặt đất.

Narrative purpose:
- nhắc người chơi rằng “tuyến an toàn” trên mặt đất không còn đủ.

## 10.3. Nhóm B — System Breakers

### EMP Stalker

Biological/technological interaction creature có khả năng làm gián đoạn hệ thống điện.

Narrative purpose:
- đại diện cho việc công nghệ và sinh học đã trở thành một hệ sinh thái tương tác mới.

### Corpse Weaver

Không phải sát thủ trực tiếp; nó biến chiến trường thành nơi nguy hiểm hơn bằng Corpse Density.

Narrative purpose:
- cho thấy ngay cả cái chết của enemy cũng trở thành một yếu tố sinh thái.

### Scavenger Harpy

Kẻ săn các drone và vật tư lộ thiên.

Narrative purpose:
- logistics của con người đã trở thành một phần của chuỗi thức ăn.

### Spore Spreader

Sinh vật phát tán bào tử, thay đổi visibility và môi trường cục bộ.

Narrative purpose:
- liên kết trực tiếp giữa ecology, hazard và battlefield readability.

## 10.4. Nhóm C — Tactical Pressure

### Riot Mech Mutate

ĐỀ XUẤT LORE:

Một nền tảng máy kiểm soát đám đông của thế giới cũ bị hỏng và biến đổi sau khi tiếp xúc lâu dài với hệ sinh thái hậu Sụp Đổ.

Không nên giải thích rằng mọi machine enemy đều “bị nhiễm”. Đây là một trường hợp đặc biệt.

### Siren Screamer

Sinh vật có cơ chế âm thanh cực mạnh, tiến hóa để bảo vệ hoặc hỗ trợ quần thể xung quanh.

Narrative purpose:
- cho thấy tiến hóa không chỉ giải quyết vấn đề sống sót cá nhân mà còn tạo ra support behavior.

## 10.5. Boss

### Colossus Titan

Boss phải được hiểu như một **living battlefield event**.

Nó có thể liên quan đến vật liệu / cấu trúc khổng lồ của thế giới cũ, nhưng không cần xác nhận nó là “robot”, “quái vật” hay “vũ khí” ngay lần đầu.

Narrative purpose:
- biểu tượng cho quy mô của thế giới ngoài Outpost;
- buộc người chơi dùng terrain, counterplay và cấu trúc thay vì chỉ DPS.

### Hive Mother

Apex reproductive organism.

Narrative purpose:
- cho thấy một hệ sinh thái có thể tự tạo ra threat lớn hơn nếu con người bỏ mặc nó.

---

# XI. FACTIONS / ORGANIZATIONS

## 11.1. V1 principle

**Không cần faction war làm core content.**

Các nhóm xã hội tồn tại chủ yếu để:

- trade;
- reputation;
- expedition;
- dialogue;
- seasonal stories;
- world events.

## 11.2. ĐỀ XUẤT MỚI — Ba tổ chức nền

Tên dưới đây là **working names** và phải được xác nhận trước khi thành terminology chính thức.

### Salvage Crews

Mạng lưới các đội thu hồi vật tư.

Gameplay relation:
- Scrap;
- Materials;
- Blueprint fragments;
- Expedition.

### Route Wardens

Nhóm bảo vệ và khôi phục tuyến logistics.

Gameplay relation:
- route stability;
- supply;
- escort/event.

### Field Science Network

Các nhóm nghiên cứu nhỏ phối hợp chia sẻ dữ liệu.

Gameplay relation:
- Biomass;
- Enemy Intel;
- research;
- environmental events.

**Không tạo skill tree/faction tech riêng ở v1.**

---

# XII. STORY STRUCTURE

## 12.1. Macro structure

Story nên đi cùng gameplay progression:

```text
SURVIVE
↓
UNDERSTAND
↓
REBUILD
↓
CONNECT
↓
ADAPT
↓
RECONSTRUCT
```

Không dùng:

```text
GET QUEST → WATCH CINEMATIC → KILL BOSS → WATCH CINEMATIC
```

## 12.2. Chapter rule

Mỗi Chapter nên có:

- 1 câu hỏi thế giới;
- 1 bài học gameplay;
- 1 escalation;
- 1 milestone/climax;
- 1 mảnh thông tin lore mới.

Ví dụ:

> Chapter ở Industrial Zone có thể vừa dạy logistics vừa tiết lộ cách supply network cũ từng vận hành.

## 12.3. Mission narrative budget

Mỗi mission thông thường nên giới hạn ở:

- 1–2 đoạn briefing rất ngắn;
- mission objective rõ;
- một số context barks trong trận;
- 1 đoạn result/reward context nếu cần.

Không yêu cầu player đọc lore để hiểu cách chơi.

---

# XIII. LORE DELIVERY SYSTEM

## 13.1. Priority order

### Layer 1 — Gameplay feedback

Thông tin quan trọng nhất phải được truyền bằng:

- animation;
- VFX;
- SFX;
- icon;
- terrain change.

### Layer 2 — World presentation

- environment props;
- signage;
- structures;
- Outpost evolution;
- NPC activity.

### Layer 3 — Short dialogue

- briefing;
- Commander bark;
- NPC bark;
- event message.

### Layer 4 — Codex / Intel

Dùng cho người muốn hiểu sâu hơn:

- enemy ecology;
- old-world technology;
- region records;
- Expedition logs.

### Layer 5 — Optional deep lore

Có thể chứa:

- survivor logs;
- research notes;
- archived transmissions;
- collectible records.

Không bao giờ khóa core gameplay sau việc đọc Layer 5.

---

# XIV. DIALOGUE PRINCIPLES

## 14.1. Dialogue phải phục vụ hành động

Một câu thoại tốt trong LAST OUTPOST nên làm ít nhất một việc:

- cảnh báo;
- định hướng;
- tạo personality;
- giải thích rất ngắn một cơ chế;
- tạo context;
- tạo cảm giác Outpost đang sống.

## 14.2. Dialogue style

Ưu tiên:

- câu ngắn;
- từ vựng tự nhiên;
- ít thuật ngữ hàn lâm;
- humor nhẹ;
- không “edgy” liên tục.

## 14.3. Tránh

- monologue dài;
- exposition dump;
- nhân vật nói điều player vừa nhìn thấy quá rõ;
- villain speech;
- chosen-one prophecy;
- triết lý quá nặng;
- cố biến mọi NPC thành comic relief.

## 14.4. Character voice

**Engineer:** phân tích vấn đề → đề xuất giải pháp.  
**Guardian:** đánh giá rủi ro → bảo vệ người.  
**Bio Specialist:** quan sát → giải thích mutation/environment.  
**Cư dân:** đời thường, thực tế, đôi khi hài hước nhẹ.

---

# XV. NAMING CONVENTIONS

## 15.1. Existing terminology phải giữ nguyên

Các thuật ngữ gameplay đã chốt không đổi tùy ý:

- Outpost
- Tower
- Base
- Barrel
- Module
- Commander
- Mission
- Wave
- Threat Budget
- Fixed Roster
- Hazard
- Objective
- Mastery
- Blueprint
- Expedition
- Season
- Plus
- Pro
- Scrap
- Materials
- Blueprint Fragment
- Biomass
- Rad-Gems
- Ammo
- Power
- Water

## 15.2. Enemy names

Giữ nguyên tên đã có:

- Crawler
- Acid Bloater
- Iron-Hide Behemoth
- EMP Stalker
- Tunnel Burrower
- Corpse Weaver
- Scavenger Harpy
- Spore Spreader
- Riot Mech Mutate
- Siren Screamer
- Colossus Titan
- Hive Mother

Không tự ý đổi tên chỉ để “ngầu hơn”.

## 15.3. New lore terminology

Khi cần tạo thuật ngữ mới:

- ưu tiên mô tả rõ nghĩa;
- tối đa 1–3 từ;
- tránh fantasy vocabulary;
- tránh tên có âm hưởng MMORPG;
- không dùng cùng một khái niệm với nhiều tên.

### Working terminology

- **Sự Sụp Đổ / The Collapse:** tên tạm cho chuỗi thảm họa tạo ra thế giới hiện tại.
- **Old World / Thế Giới Cũ:** thế giới trước Sự Sụp Đổ.
- **Outpost Age / Thời Đại Trụ Sở:** giai đoạn hiện tại của người chơi.

Các tên faction/tổ chức ở Section XI là **working names**, chưa được coi là terminology khóa.

---

# XVI. STORY IN GAMEPLAY

## 16.1. Mission Briefing

Briefing phải trả lời:

1. Đi đâu?
2. Vì sao cần đi?
3. Có threat gì?
4. Người chơi cần làm gì?

Lore chỉ chiếm phần phụ.

## 16.2. Enemy Intel

Codex giúp người chơi hiểu enemy theo ngôn ngữ gameplay:

- vai trò;
- signature;
- điểm mạnh/yếu;
- counter.

Lore có thể xuất hiện dưới dạng một đoạn ngắn phía dưới gameplay information.

## 16.3. Battle Report

Battle Report có thể kể lại hậu quả:

> “Kho Đạn giữ được tuyến.”
>
> “Drone route bị Harpy phá 2 lần.”
>
> “Recovered industrial materials unlocked new research.”

Narrative phải đến sau gameplay clarity.

## 16.4. Outpost progression

Outpost Evolution là storytelling system quan trọng nhất của game.

Stage change phải cho người chơi cảm giác:

> “Mình đã làm được chuyện này.”

## 16.5. Expedition

Expedition là công cụ kể world ngoài màn combat.

Một Expedition có thể trả về:

- tài nguyên;
- survivor story;
- old-world fragment;
- Enemy Intel;
- blueprint fragment.

Không bắt người chơi đọc log để claim reward.

---

# XVII. SEASON & LIVE NARRATIVE

## 17.1. Persistent canon

Season không reset world progression.

Các sự kiện theo mùa chỉ tạo:

- modifiers;
- event missions;
- cosmetics;
- limited story layer;
- event currency;
- temporary rankings.

## 17.2. Seasonal narrative rule

Một Season chỉ nên giới thiệu **một câu hỏi / tình huống thế giới chính**.

Ví dụ:

> “Một tuyến supply cũ đột nhiên hoạt động lại.”

Sau Season:

- event currency biến mất/reset theo design;
- Outpost vẫn nguyên;
- Tower/Blueprint vẫn nguyên;
- story discovery đã mở vẫn nguyên.

## 17.3. Return-player narrative

Người chơi quay lại sau Season không được cảm thấy:

> “Tôi bỏ game vài tuần nên đã mất toàn bộ tiến trình.”

Họ nên thấy:

> “Thế giới đã thay đổi một chút, nhưng Outpost của tôi vẫn là của tôi.”

---

# XVIII. AUDIO/NARRATIVE RELATIONSHIP

World Narrative phải thiết kế để Audio Bible sau này có thể ánh xạ dễ dàng.

Mỗi biome nên có:

- ambience identity;
- signature environmental sound;
- warning motifs;
- material sound language;
- wildlife/enemy ambience;
- Outpost transition cue.

Narrative không được yêu cầu nhạc phim liên tục để “ép cảm xúc”.

Âm thanh phải cho người chơi biết thế giới đang hoạt động.

---

# XIX. VISUAL/NARRATIVE RELATIONSHIP

## 19.1. Visual kể chuyện trước text

Ví dụ:

**Sai:**

> NPC nói: “Nhà máy này đã bỏ hoang từ rất lâu.”

**Đúng:**

Người chơi bước qua:

- đường ray phủ cỏ;
- bảng cảnh báo cũ;
- dây chuyền ngừng hoạt động;
- container tái sử dụng làm barricade.

Text chỉ bổ sung context nếu cần.

## 19.2. Scale của thế giới

Không tạo những công trình khổng lồ chỉ để “wow”.

Mọi environmental scale phải hỗ trợ:

- gameplay readability;
- navigation;
- tactical positioning;
- Outpost identity.

---

# XX. NARRATIVE CONTENT PRODUCTION RULES

Mỗi Mission/Chapter/Region có narrative card tối thiểu:

```text
Narrative ID:
World:
Region:
Biome:
Chapter:
Narrative Question:
Gameplay Lesson:
Why Here:
Enemy Ecology:
Environmental Storytelling:
Briefing Context:
Commander Context:
Reward / Unlock Context:
Lore Fragment (Optional):
Localization Notes:
Dependencies:
```

## Content quality gate

Một content narrative chỉ đạt **Validated** khi:

- không mâu thuẫn với gameplay;
- không yêu cầu hệ thống mới chỉ để kể chuyện;
- không ép player đọc;
- có lý do tồn tại trong world;
- khớp biome identity;
- khớp enemy roster;
- không thêm terminology chưa được duyệt;
- có thể cắt phần text mà gameplay vẫn hiểu được.

---

# XXI. PROPOSED STORY ARC

> Đây là **đề xuất mới** để làm khung campaign; không thay đổi core gameplay.

## Arc 1 — The Empty City

Người chơi thiết lập Survivor Camp và học cách sống sót trong Ruined Urban Core.

Trọng tâm:
- xây;
- phòng thủ;
- salvage;
- hiểu Fixed Roster.

Lore discovery:

> thế giới cũ sụp đổ không phải chỉ vì một trận chiến.

## Arc 2 — The Broken Supply Line

Người chơi tiếp cận Industrial Zone.

Trọng tâm:
- logistics;
- heat;
- Power;
- supply routes.

Lore discovery:

> các thành phố từng phụ thuộc vào mạng vận chuyển liên kết chặt chẽ hơn con người tưởng.

## Arc 3 — The Green Threat

Người chơi tiến vào Toxic Wetland.

Trọng tâm:
- Water;
- biomass;
- Spore hazards;
- ecological counterplay.

Lore discovery:

> hệ sinh thái mới không chỉ nguy hiểm; nó có quy luật riêng.

## Arc 4 — The Frozen Record

Frozen Wasteland mở thêm dữ liệu về nghiên cứu và sự cố trước Sụp Đổ.

Trọng tâm:
- Power;
- route survival;
- research;
- advanced enemy interactions.

Lore discovery:

> một phần dữ liệu cũ cho thấy con người đã nhận ra nguy cơ trước khi mọi thứ thực sự sụp đổ.

## Arc 5 — The Last Network

Người chơi kết nối được nhiều tuyến sống sót và xây dựng một Last Bastion có khả năng duy trì lâu dài.

Trọng tâm:
- endgame preparedness;
- Expedition;
- World Events;
- Guild/endgame layer nếu đã mở.

Lore conclusion:

> mục tiêu không phải khôi phục y nguyên thế giới cũ, mà xây một thế giới mới biết cách sống với những gì còn lại.

---

# XXII. WHAT THE GAME SHOULD NOT REVEAL TOO EARLY

Không tiết lộ toàn bộ ngay từ đầu:

- nguyên nhân chính xác của Sự Sụp Đổ;
- nguồn gốc đầy đủ của tất cả mutation;
- sự thật về các technology remnants;
- toàn bộ lịch sử Research Network;
- toàn bộ mối quan hệ giữa Biomass và hệ sinh thái.

Nhưng gameplay phải luôn tự giải thích được.

Lore mystery chỉ tồn tại để tạo tò mò, không được dùng để che giấu rule gameplay.

---

# XXIII. SCOPE CONTROL

## Không làm trong narrative v1

- romance system;
- companion approval tree;
- morality meter;
- dialogue choices thay đổi toàn campaign;
- faction war;
- branching campaign lớn;
- cinematic cutscenes dài;
- voice acting cho toàn bộ NPC;
- lore collectible hàng trăm item bắt buộc;
- một “main villain” xuất hiện liên tục;
- world map story layer phức tạp như MMORPG.

## Optional cho expansion

Chỉ xem xét sau khi PvE core ổn định:

- named rival survivor leaders;
- larger regional stories;
- special scenario characters;
- deeper world-state events.

---

# XXIV. CONFLICT / LEGACY NOTES

## 24.1. Historical ideas không được lấy làm current canon

Tài liệu cơ chế cũ có các ý tưởng như:

- randomized world spawns theo mùa;
- diplomacy phức tạp;
- hex-grid territory PvP;
- 24h siege;
- War Monuments;
- faction/territory war sâu.

Các ý tưởng này **không được dùng làm nền narrative v1**, vì các tài liệu tối ưu hiện tại đã thu gọn hoặc tạm bỏ chúng.

## 24.2. Persistent Outpost

Bất kỳ narrative nào mô tả season mới cũng phải giữ:

> **Outpost progression và permanent collection không reset.**

## 24.3. Fixed Roster

Narrative không được dùng “surprise species” để phá Fixed Enemy Roster của mission.

Surprise có thể đến từ:

- timing;
- terrain;
- hazard;
- composition;
- objective;
- logistics pressure.

---

# XXV. DEFINITION OF DONE

World & Narrative Bible đạt DoD khi:

- [ ] World premise phù hợp với core identity.
- [ ] Nguyên nhân hậu tận thế được ghi rõ là canon hoặc proposal.
- [ ] Timeline không mâu thuẫn với Outpost progression.
- [ ] Tất cả biome hiện tại có vai trò gameplay + narrative.
- [ ] Commander classes có narrative identity nhưng không tạo system dependency mới.
- [ ] 12 enemy có lore role rõ.
- [ ] Survivor society không phình thành faction RPG.
- [ ] Dialogue rules phù hợp UX first.
- [ ] Lore delivery không cản gameplay.
- [ ] Season narrative tôn trọng persistent Outpost.
- [ ] Naming conventions không phá terminology hiện tại.
- [ ] Không cần code mới chỉ để áp dụng Narrative Bible.
- [ ] Claude có thể dùng tài liệu này để hiểu “game đang nói về thế giới nào và vì sao player đang làm việc đó” mà không phải tự đoán canon.

---

# XXVI. CÁC QUYẾT ĐỊNH ĐỀ XUẤT CẦN CHỐT SAU

Các mục sau hiện **chưa được xem là quyết định final**:

1. Canon chính thức của nguyên nhân Sự Sụp Đổ.
2. Timeline số năm cụ thể.
3. Tên chính thức của các tổ chức survivor.
4. Tên cá nhân của các Commander.
5. Mức độ liên hệ chính xác giữa biological mutation và old-world technology.
6. Thứ tự campaign cuối cùng của các region/biome.

Không được tự động coi các mục trên là canon nếu chưa được cập nhật thành quyết định mới.

---

# XXVII. FINAL NARRATIVE RULE

> **LAST OUTPOST kể câu chuyện về con người xây dựng lại khả năng sống sót từng chút một.**
>
> **Gameplay là câu chuyện chính.**
>
> Lore giải thích thế giới, dialogue tạo personality, environment tạo ký ức, và Outpost cho người chơi thấy hậu quả của những gì họ đã làm.

**BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN** không chỉ là gameplay loop.

Đó cũng là cách thế giới LAST OUTPOST tiếp tục tồn tại.
