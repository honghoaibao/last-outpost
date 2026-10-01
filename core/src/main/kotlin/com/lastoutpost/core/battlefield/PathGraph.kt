package com.lastoutpost.core.battlefield

/**
 * Path Graph — System & Gameplay Design v1.1 §3.3: "Quái không pathfind trên toàn bộ bản đồ mỗi frame.
 * Map được chia thành node/segment; barricade thay đổi connectivity; path được tái tính khi topology thay
 * đổi đáng kể." Đây là abstraction gameplay cho di chuyển enemy (ADR-0005) — lane là rule của từng mission,
 * KHÔNG phải một global coordinate grid.
 *
 * Chỉ model cấu trúc đồ thị + toggle connectivity ở đây. Thuật toán di chuyển/pathfinding thật (system chạy
 * ở TickStage.MOVEMENT) là milestone riêng, CHƯA triển khai.
 */
data class LaneNode(val id: String, val position: WorldPosition)

/** Cạnh vô hướng giữa 2 lane node. [blocked] mô phỏng barricade/topology change (mặc định thông). */
data class LaneEdge(val from: String, val to: String, val blocked: Boolean = false)

class PathGraph(nodes: List<LaneNode>, edges: List<LaneEdge>) {
    private val nodesById: Map<String, LaneNode> = nodes.associateBy { it.id }
    private val edgeKeys: MutableMap<Pair<String, String>, LaneEdge> = LinkedHashMap()

    init {
        for (edge in edges) {
            require(edge.from in nodesById) { "LaneEdge tham chiếu node không tồn tại: ${edge.from}" }
            require(edge.to in nodesById) { "LaneEdge tham chiếu node không tồn tại: ${edge.to}" }
            edgeKeys[edge.from to edge.to] = edge
            edgeKeys[edge.to to edge.from] = edge // vô hướng
        }
    }

    fun node(id: String): LaneNode? = nodesById[id]

    val nodeIds: Set<String> get() = nodesById.keys

    /** Các node kề còn thông (chưa bị barricade chặn). */
    fun neighbors(nodeId: String): List<String> =
        edgeKeys.entries.filter { (key, edge) -> key.first == nodeId && !edge.blocked }.map { it.key.second }

    /** Barricade/topology change — chặn hoặc mở lại một cạnh (đổi connectivity, System Gameplay §3.3). */
    fun setBlocked(from: String, to: String, blocked: Boolean) {
        val edge = edgeKeys[from to to] ?: error("Không có LaneEdge giữa $from và $to")
        val updated = edge.copy(blocked = blocked)
        edgeKeys[from to to] = updated
        edgeKeys[to to from] = updated
    }
}
