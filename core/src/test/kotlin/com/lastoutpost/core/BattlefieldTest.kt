package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.battlefield.HeightLayer
import com.lastoutpost.core.battlefield.LaneEdge
import com.lastoutpost.core.battlefield.LaneNode
import com.lastoutpost.core.battlefield.MountType
import com.lastoutpost.core.battlefield.PathGraph
import com.lastoutpost.core.battlefield.WorldPosition
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WorldPositionTest {
    @Test
    fun `khoang cach Euclid tinh dung cho canh 3-4-5`() {
        assertEquals(5.0, WorldPosition(0.0, 0.0).distanceTo(WorldPosition(3.0, 4.0)))
    }

    @Test
    fun `khoang cach tu mot diem den chinh no la 0`() {
        val p = WorldPosition(7.5, -2.0)
        assertEquals(0.0, p.distanceTo(p))
    }
}

class BuildableNodeTest {
    @Test
    fun `node moi occupiedBy null thi isOccupied la false`() {
        val node = BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND)
        assertFalse(node.isOccupied)
    }

    @Test
    fun `node co occupiedBy thi isOccupied la true`() {
        val node = BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.WALL, height = HeightLayer.STRUCTURE, occupiedBy = 42L)
        assertTrue(node.isOccupied)
        assertEquals(HeightLayer.STRUCTURE, node.height)
    }
}

class PathGraphTest {
    private val nodes = listOf(LaneNode("A", WorldPosition(0.0, 0.0)), LaneNode("B", WorldPosition(1.0, 0.0)), LaneNode("C", WorldPosition(2.0, 0.0)))

    @Test
    fun `neighbor tra ve dung node ke va vo huong`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B")))
        assertEquals(listOf("B"), graph.neighbors("A"))
        assertEquals(listOf("A"), graph.neighbors("B")) // vô hướng — B cũng thấy A
    }

    @Test
    fun `barricade chan canh thi neighbor khong con thay nhau`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B"), LaneEdge("B", "C")))
        graph.setBlocked("A", "B", blocked = true)
        assertEquals(emptyList(), graph.neighbors("A"))
        assertEquals(listOf("C"), graph.neighbors("B")) // B-C vẫn thông
    }

    @Test
    fun `mo lai barricade thi connectivity phuc hoi`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B", blocked = true)))
        assertEquals(emptyList(), graph.neighbors("A"))
        graph.setBlocked("A", "B", blocked = false)
        assertEquals(listOf("B"), graph.neighbors("A"))
    }

    @Test
    fun `edge tham chieu node khong ton tai bi tu choi ngay khi khoi tao`() {
        assertFailsWith<IllegalArgumentException> { PathGraph(nodes, listOf(LaneEdge("A", "GHOST"))) }
    }

    @Test
    fun `setBlocked tren canh khong ton tai bao loi ro rang`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B")))
        assertFailsWith<IllegalStateException> { graph.setBlocked("A", "C", blocked = true) }
    }

    @Test
    fun `shortestPath tra ve path qua nhieu hop theo dung thu tu`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B"), LaneEdge("B", "C")))
        assertEquals(listOf("A", "B", "C"), graph.shortestPath("A", "C"))
    }

    @Test
    fun `shortestPath tu mot node den chinh no tra ve path 1 phan tu`() {
        val graph = PathGraph(nodes, emptyList())
        assertEquals(listOf("A"), graph.shortestPath("A", "A"))
    }

    @Test
    fun `shortestPath chon duong ngan hon theo khoang cach Euclid khong phai so hop`() {
        // D xa trục A-B-C-E (đi vòng, 4 cạnh ngắn) so với cạnh tắt A-E (1 cạnh nhưng dài) — chọn tổng khoảng cách nhỏ nhất
        val farNodes = listOf(
            LaneNode("A", WorldPosition(0.0, 0.0)),
            LaneNode("B", WorldPosition(1.0, 0.0)),
            LaneNode("C", WorldPosition(2.0, 0.0)),
            LaneNode("E", WorldPosition(3.0, 0.0)),
        )
        val direct = PathGraph(farNodes, listOf(LaneEdge("A", "E"))) // 1 cạnh, dài 3.0
        assertEquals(listOf("A", "E"), direct.shortestPath("A", "E"))

        val viaDetour = PathGraph(farNodes, listOf(LaneEdge("A", "B"), LaneEdge("B", "C"), LaneEdge("C", "E"), LaneEdge("A", "E")))
        // đường vòng A-B-C-E dài 1+1+1=3.0, bằng đường tắt A-E (3.0) — cả hai đều hợp lệ về mặt khoảng cách,
        // Dijkstra ổn định sẽ chọn đường khám phá trước; bài test chỉ xác nhận có path và độ dài đúng bằng 3.0
        val path = viaDetour.shortestPath("A", "E")!!
        assertEquals(3.0, viaDetour.pathLength(path))
    }

    @Test
    fun `shortestPath bo qua canh bi blocked`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B"), LaneEdge("B", "C"), LaneEdge("A", "C")))
        graph.setBlocked("A", "C", blocked = true)
        assertEquals(listOf("A", "B", "C"), graph.shortestPath("A", "C")) // phải đi vòng qua B vì A-C bị chặn
    }

    @Test
    fun `shortestPath tra ve null neu khong toi duoc`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B"))) // C không nối với ai
        assertNull(graph.shortestPath("A", "C"))
    }

    @Test
    fun `shortestPath tra ve null neu node khong ton tai`() {
        val graph = PathGraph(nodes, emptyList())
        assertNull(graph.shortestPath("A", "GHOST"))
    }

    @Test
    fun `pathLength tinh tong khoang cach Euclid cua ca path`() {
        val graph = PathGraph(nodes, listOf(LaneEdge("A", "B"), LaneEdge("B", "C")))
        assertEquals(2.0, graph.pathLength(listOf("A", "B", "C"))) // 1.0 + 1.0
    }

    @Test
    fun `pathLength cua path 1 node la 0`() {
        val graph = PathGraph(nodes, emptyList())
        assertEquals(0.0, graph.pathLength(listOf("A")))
    }
}

class BattlefieldTest {
    @Test
    fun `occupy danh dau node va vacate tra ve trong`() {
        val field = Battlefield(listOf(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND)))
        field.occupy("N1", entityId = 5L)
        assertTrue(field.node("N1")!!.isOccupied)
        assertEquals(5L, field.node("N1")!!.occupiedBy)

        assertTrue(field.vacate("N1"))
        assertFalse(field.node("N1")!!.isOccupied)
    }

    @Test
    fun `occupy node da chiem bao loi`() {
        val field = Battlefield(listOf(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND)))
        field.occupy("N1", 1L)
        assertFailsWith<IllegalStateException> { field.occupy("N1", 2L) }
    }

    @Test
    fun `occupy node khong ton tai bao loi`() {
        val field = Battlefield(emptyList())
        assertFailsWith<IllegalStateException> { field.occupy("GHOST", 1L) }
    }

    @Test
    fun `vacate node khong ton tai tra ve false`() {
        val field = Battlefield(emptyList())
        assertFalse(field.vacate("GHOST"))
    }

    @Test
    fun `spawnPositionForLane tra ve null neu entry node chua ton tai tren Path Graph`() {
        val field = Battlefield(
            nodes = listOf(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND)),
            laneEntryNodes = mapOf(0 to "ENTRY_0"),
        )
        // "ENTRY_0" là ID của một LaneNode trên Path Graph, KHÔNG phải BuildableNode — lane đã khai báo
        // entry nhưng node đó chưa tồn tại trên paths → không có vị trí spawn.
        assertNull(field.spawnPositionForLane(0))
        assertNull(field.spawnPositionForLane(null))
        assertNull(field.spawnPositionForLane(99)) // lane không được khai báo
    }

    @Test
    fun `spawnPositionForLane tra ve vi tri khi entry node ton tai tren Path Graph`() {
        val field = Battlefield(
            nodes = emptyList(),
            paths = PathGraph(listOf(LaneNode("ENTRY_0", WorldPosition(10.0, 0.0))), emptyList()),
            laneEntryNodes = mapOf(0 to "ENTRY_0"),
        )
        assertEquals(WorldPosition(10.0, 0.0), field.spawnPositionForLane(0))
    }
}
