package com.lastoutpost.core.battlefield

import java.util.PriorityQueue

/**
 * Path Graph — System & Gameplay Design v1.1 §3.3: "Quái không pathfind trên toàn bộ bản đồ mỗi frame.
 * Map được chia thành node/segment; barricade thay đổi connectivity; path được tái tính khi topology thay
 * đổi đáng kể." Đây là abstraction gameplay cho di chuyển enemy (ADR-0005) — lane là rule của từng mission,
 * KHÔNG phải một global coordinate grid.
 *
 * [shortestPath] dùng làm "navigation graph/cost map" cho enemy GROUND — AI & Simulation Design §IV.1:
 * "Dùng navigation graph/cost map thay vì mỗi enemy chạy A* độc lập liên tục." Trọng số cạnh = khoảng cách
 * Euclid giữa 2 node (ADR-0005 điểm 3: Euclidean dùng cho phép đo phù hợp, ở đây là chi phí di chuyển).
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

    /**
     * Đường đi ngắn nhất (Dijkstra, trọng số = khoảng cách Euclid giữa 2 node) từ [from] tới [to], chỉ qua
     * cạnh chưa bị block. `null` nếu không tới được (đồ thị đứt do barricade, hoặc node không tồn tại).
     * Tái tính mỗi lần gọi — chấp nhận được ở quy mô Beta 01 (map nhỏ, số enemy giới hạn); tối ưu cache khi
     * cần (TECH_DEBT).
     */
    fun shortestPath(from: String, to: String): List<String>? {
        if (from !in nodesById || to !in nodesById) return null
        if (from == to) return listOf(from)

        val distance = mutableMapOf(from to 0.0)
        val previous = mutableMapOf<String, String>()
        val visited = mutableSetOf<String>()
        val queue = PriorityQueue<String>(compareBy { distance[it] ?: Double.MAX_VALUE })
        queue.add(from)

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (!visited.add(current)) continue
            if (current == to) break
            val currentPos = nodesById.getValue(current).position
            for (next in neighbors(current)) {
                if (next in visited) continue
                val edgeLength = currentPos.distanceTo(nodesById.getValue(next).position)
                val candidate = distance.getValue(current) + edgeLength
                if (candidate < (distance[next] ?: Double.MAX_VALUE)) {
                    distance[next] = candidate
                    previous[next] = current
                    queue.add(next)
                }
            }
        }

        if (to !in distance) return null
        val path = mutableListOf(to)
        var cursor = to
        while (cursor != from) {
            cursor = previous[cursor] ?: return null
            path.add(cursor)
        }
        return path.asReversed()
    }

    /** Tổng độ dài (Euclid) của một path — dùng để so sánh "tiến xa tới đích" giữa các enemy (vd PrimaryOrder.FIRST). */
    fun pathLength(path: List<String>): Double =
        path.zipWithNext { a, b -> nodesById.getValue(a).position.distanceTo(nodesById.getValue(b).position) }.sum()
}
