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
    fun `spawnPositionForLane tra ve dung vi tri entry node theo lane`() {
        val field = Battlefield(
            nodes = listOf(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND)),
            laneEntryNodes = mapOf(0 to "ENTRY_0"),
        )
        // ENTRY_0 không nằm trong buildable nodes (lane entry là điểm trên Path Graph, không phải build node)
        // — test này minh hoạ trường hợp lane có khai báo nhưng entry node chưa tồn tại ở battlefield nodes.
        assertNull(field.spawnPositionForLane(0)) // vì "ENTRY_0" không có trong danh sách node truyền vào
        assertNull(field.spawnPositionForLane(null))
        assertNull(field.spawnPositionForLane(99)) // lane không được khai báo
    }

    @Test
    fun `spawnPositionForLane tra ve vi tri khi entry node ton tai trong battlefield`() {
        val field = Battlefield(
            nodes = listOf(BuildableNode("ENTRY_0", WorldPosition(10.0, 0.0), MountType.GROUND)),
            laneEntryNodes = mapOf(0 to "ENTRY_0"),
        )
        assertEquals(WorldPosition(10.0, 0.0), field.spawnPositionForLane(0))
    }
}
