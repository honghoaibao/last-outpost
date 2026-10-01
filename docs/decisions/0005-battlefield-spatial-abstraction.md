# ADR-0005: Battlefield spatial abstraction — World Position + Buildable Nodes + Path Graph/Lane

- **Status:** Accepted
- **Ngày:** 2026-09-26
- **Quyết định bởi:** Bảo (Tech), trả lời câu hỏi mở khi triển khai Target Acquisition
- **Liên quan:** System & Gameplay Design v1.1 §III (Battlefield Data Model), §IV.2 (Build Validation), §3.3
  (Path Graph); Core Combat Gameplay §2.2 (Base ↔ mount); giải quyết OQ-TECH-07 (một phần — xem "Còn lại")

## Bối cảnh
`Command.BuildTower` và vị trí spawn enemy trước đây dùng toạ độ nguyên tự do `(x: Int, y: Int)`, với
`MountProvider` là một hàm tra cứu placeholder luôn trả về GROUND (không có bản đồ thật). Cần quyết định
biểu diễn không gian chính thức trước khi làm Target Acquisition — quyết định này áp dụng ngay cho cả Build
placement và Enemy spawn/movement vì cả hai đều phụ thuộc "vị trí là gì" trong game.

## Quyết định
1. **Không dùng Hex Grid** — hex-grid là legacy (territory/PvP), không khôi phục; không dùng hex cell làm
   abstraction chính của battlefield.
2. **Không dùng Cartesian integer grid làm abstraction gameplay duy nhất** — không biến map thành ô vuông
   mà mọi gameplay phụ thuộc x,y integer; không yêu cầu pixel-perfect/grid-perfect placement.
3. **Không gian nền:** `WorldPosition` (Cartesian 2D, `Double`) là lớp biểu diễn thấp nhất. Khoảng cách
   Euclid (`WorldPosition.distanceTo`) dùng cho range/radius/AOE khi rule cần — không phải rule duy nhất
   quyết định gameplay.
4. **Build placement:** dùng `BuildableNode` (System Gameplay §3.2 — đúng schema gốc: mountType, occupied,
   allowedTags, height, stability, coverage, specialInteraction). Người chơi chọn/preview node hợp lệ, không
   tự do đặt theo toạ độ.
5. **Enemy movement:** dùng `PathGraph` (System Gameplay §3.3 — node/segment, barricade đổi connectivity,
   path tái tính khi topology đổi đáng kể). Map có 1–3 lane; lane là rule của từng mission, không phải một
   global coordinate grid.
6. Abstraction gameplay: `World Position → Buildable Nodes → Path Graph/Lanes → Terrain/Hazard/Logistics →
   Gameplay Rules`.
7. Beta 01: ưu tiên representation đơn giản, deterministic, dễ author. Map designer quyết định node + lane +
   terrain + hazard. Không tự thêm procedural grid/hex. Không thiết kế thêm freeform placement ngoài phạm vi.

**Tóm lại:** `WorldPosition` chỉ là spatial representation; gameplay canonical là **Cartesian space +
Buildable Nodes + Fixed/Parametric Lanes (Path Graph)**.

## Triển khai
- `battlefield/WorldPosition.kt`, `MountType.kt` (chuyển từ `tower/`), `HeightLayer.kt` (Z0/Z1/Z2 — Visual
  Identity §"Height Layers"), `BuildableNode.kt`, `PathGraph.kt` (`LaneNode`/`LaneEdge`, `setBlocked` cho
  barricade), `Battlefield.kt` (gộp node + path graph + occupancy cho một mission, `spawnPositionForLane`).
- `Command.BuildTower` đổi từ `(x: Int, y: Int)` sang `nodeId: String`. `MoveCommander`/`UseAbility` đổi
  sang `WorldPosition` (di chuyển tự do, không ràng buộc node).
- `TowerSystem` validate qua `Battlefield.node(nodeId)` (tồn tại + chưa bị chiếm + mount compatibility),
  chiếm/nhả node khi build/cancel.
- `TowerEntity`/`EnemyEntity` đổi field toạ độ (`transform: Transform`, Int-based) thành `position:
  WorldPosition` (Double-based); `entity.Transform` bị xoá.
- `EnemySystem` nhận `Battlefield?` tuỳ chọn, tra `spawnPositionForLane(lane)`; không có battlefield/lane →
  fallback `(0.0, 0.0)` (như cũ, vẫn là placeholder).

## Còn lại (chưa giải quyết bởi ADR này)
- **Content/authoring cho map:** chưa có schema JSON cho node/lane theo từng mission (map designer hiện phải
  dựng bằng Kotlin trong test/smoke) — xem TECH_DEBT TD-17. Đây là phần CÒN LẠI của OQ-TECH-07 (nguồn dữ
  liệu mount type đã có model, nhưng "map thật của Mission X" vẫn chưa tồn tại).
- Thuật toán di chuyển/pathfinding thật trên `PathGraph` (TickStage.MOVEMENT) chưa triển khai.
- `stability`/`coverage`/`specialInteraction` trên `BuildableNode` là trường dữ liệu đúng schema nhưng CHƯA
  có cơ chế gameplay tiêu thụ chúng (Hazard/Combat system chưa tới).
- "không phá các lane bắt buộc của màn nếu màn khóa lane" (System Gameplay §4.2) chưa được validate trong
  `TowerSystem` — cần khái niệm "lane bị khoá" ở mission content, chưa có.

## Hệ quả
- (+) Khớp trực tiếp Battlefield Data Model đã có sẵn trong System & Gameplay Design v1.1 §III — không phải
  kiến trúc tự nghĩ ra ngoài tài liệu.
- (+) `TowerSystem`/`EnemySystem` giờ dùng chung một khái niệm "bản đồ" (`Battlefield`) thay vì mỗi hệ thống
  tự có placeholder riêng (`MountProvider` cũ bị xoá).
- (−) Breaking change: `Command.BuildTower`, `TowerEntity`/`EnemyEntity` field toạ độ, chữ ký
  `EntityRegistry.createTower/createEnemy`, `TowerSystem` constructor đều đổi — đã cập nhật toàn bộ test đi
  kèm (54 test Kotlin, `-Werror` sạch).

## Cách rollback
`battlefield` là package độc lập, không phụ thuộc `sim`/`tower`/`enemy`. Muốn đổi lại abstraction chỉ cần
thay nội dung package này + chữ ký các hàm nhận `WorldPosition`/node id — logic tick pipeline, Command
pattern, Entity Registry không đổi.
