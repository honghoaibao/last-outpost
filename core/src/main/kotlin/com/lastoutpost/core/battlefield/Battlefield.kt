package com.lastoutpost.core.battlefield

/**
 * Battlefield — System & Gameplay Design v1.1 §3.1, tổng hợp Buildable Nodes + Path Graph cho MỘT mission
 * (ADR-0005). Map designer quyết định node + lane (điểm 7, ADR-0005) — hiện được dựng thủ công bằng Kotlin
 * (test/smoke), CHƯA có content pipeline (JSON) cho map/mission layout (xem TECH_DEBT).
 *
 * Terrain Layer, Hazard Zones, Resource Nodes, Salvage Objects, Enemy Spawn Points, Base Core, Outpost
 * Structures (cũng thuộc Battlefield theo §3.1) CHƯA model — nằm ngoài phạm vi tăng dần hiện tại.
 */
class Battlefield(
    nodes: List<BuildableNode>,
    val paths: PathGraph = PathGraph(emptyList(), emptyList()),
    /** entry node theo lane index — Wave Director dùng để đặt enemy mới spawn vào đúng lane (§3.1 Enemy Spawn Points). */
    private val laneEntryNodes: Map<Int, String> = emptyMap(),
    /**
     * Node đại diện Base Core trên Path Graph — dùng để tính "tiến xa nhất về phía Core" cho
     * `PrimaryOrder.FIRST` ([com.lastoutpost.core.targeting.TowerTargetingSystem]). `null` nếu mission chưa
     * cấu hình (hệ thống targeting sẽ fallback, xem TECH_DEBT).
     */
    val coreNodeId: String? = null,
) {
    private val nodesById: MutableMap<String, BuildableNode> = LinkedHashMap(nodes.associateBy { it.id })

    fun node(id: String): BuildableNode? = nodesById[id]

    val nodeIds: Set<String> get() = nodesById.keys

    /** Vị trí spawn cho một lane, nếu mission có khai báo entry node cho lane đó. */
    fun spawnPositionForLane(lane: Int?): WorldPosition? =
        lane?.let { laneEntryNodes[it] }?.let { nodesById[it]?.position }

    /**
     * Vị trí Core (nếu [coreNodeId] đã cấu hình) — Core nằm trên Path Graph (đích của lane), không phải một
     * Buildable Node, nên tra ở [paths] chứ không phải danh sách node ở trên.
     */
    val corePosition: WorldPosition? get() = coreNodeId?.let { paths.node(it)?.position }

    /** Đặt Tower vào node — gọi sau khi đã validate; ném lỗi nếu node không tồn tại hoặc đã bị chiếm. */
    fun occupy(nodeId: String, entityId: Long) {
        val node = nodesById[nodeId] ?: error("Node không tồn tại: $nodeId")
        check(!node.isOccupied) { "Node $nodeId đã bị chiếm bởi entity ${node.occupiedBy}" }
        nodesById[nodeId] = node.copy(occupiedBy = entityId)
    }

    /** Trả node về trạng thái trống. @return true nếu node có tồn tại (bất kể trước đó có bị chiếm hay không). */
    fun vacate(nodeId: String): Boolean {
        val node = nodesById[nodeId] ?: return false
        nodesById[nodeId] = node.copy(occupiedBy = null)
        return true
    }
}
