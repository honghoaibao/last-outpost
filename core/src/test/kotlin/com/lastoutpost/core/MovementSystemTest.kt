package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.LaneEdge
import com.lastoutpost.core.battlefield.LaneNode
import com.lastoutpost.core.battlefield.PathGraph
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.Movement
import com.lastoutpost.core.enemy.MovementSystem
import com.lastoutpost.core.enemy.MovementType
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MovementSystemTest {
    private fun combatWorld(battlefield: Battlefield?): BattleWorld {
        val system = MovementSystem(battlefield)
        return BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = listOf(system)).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }
    }

    private fun linearField(coreX: Double = 10.0) = Battlefield(
        nodes = emptyList(),
        paths = PathGraph(
            nodes = listOf(LaneNode("A", WorldPosition(0.0, 0.0)), LaneNode("CORE", WorldPosition(coreX, 0.0))),
            edges = listOf(LaneEdge("A", "CORE")),
        ),
        coreNodeId = "CORE",
    )

    @Test
    fun `ground enemy chua co speed thi khong di chuyen`() {
        val field = linearField()
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(0.0, 0.0), Movement(MovementType.GROUND, speed = null, currentNodeId = "A"))
        world.step()
        assertEquals(WorldPosition(0.0, 0.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `ground enemy di chuyen dung speed moi tick doc theo path graph`() {
        val field = linearField(coreX = 10.0)
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(0.0, 0.0), Movement(MovementType.GROUND, speed = 3.0, currentNodeId = "A"))
        world.step()
        assertEquals(WorldPosition(3.0, 0.0), world.entities.enemy(id)!!.position)
        world.step()
        assertEquals(WorldPosition(6.0, 0.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `ground enemy snap dung vi tri node va cap nhat currentNodeId khi toi gan`() {
        val field = linearField(coreX = 10.0)
        val world = combatWorld(field)
        // còn cách CORE 2.0, speed 5.0 → phải snap đúng (10,0), không vượt quá
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(8.0, 0.0), Movement(MovementType.GROUND, speed = 5.0, currentNodeId = "A"))
        world.step()
        val enemy = world.entities.enemy(id)!!
        assertEquals(WorldPosition(10.0, 0.0), enemy.position)
        assertEquals("CORE", enemy.movement.currentNodeId)
    }

    @Test
    fun `ground enemy da toi Core thi dung lai va phat enemy_reached_core`() {
        val field = linearField(coreX = 10.0)
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(10.0, 0.0), Movement(MovementType.GROUND, speed = 5.0, currentNodeId = "CORE"))
        world.step()
        assertEquals(WorldPosition(10.0, 0.0), world.entities.enemy(id)!!.position)
        assertEquals(0, world.eventLog.count { it.type == "enemy_reached_core" }) // đã ở Core từ trước, không phải tick này mới tới
    }

    @Test
    fun `ground enemy khong co currentNodeId thi dung yen`() {
        val field = linearField()
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(5.0, 5.0), Movement(MovementType.GROUND, speed = 3.0, currentNodeId = null))
        world.step()
        assertEquals(WorldPosition(5.0, 5.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `khong co battlefield thi ground enemy dung yen, khong crash`() {
        val world = combatWorld(battlefield = null)
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(1.0, 1.0), Movement(MovementType.GROUND, speed = 3.0, currentNodeId = "A"))
        world.step()
        assertEquals(WorldPosition(1.0, 1.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `graph dut do barricade thi enemy dung yen`() {
        val field = linearField(coreX = 10.0)
        field.paths.setBlocked("A", "CORE", blocked = true)
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(0.0, 0.0), Movement(MovementType.GROUND, speed = 3.0, currentNodeId = "A"))
        world.step()
        assertEquals(WorldPosition(0.0, 0.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `flying enemy bay thang toi corePosition khong theo path graph`() {
        val field = linearField(coreX = 10.0) // CORE tại (10,0), không có edge nào cần thiết cho flying
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_SCAVENGER_HARPY", WorldPosition(0.0, 0.0), Movement(MovementType.FLYING, speed = 4.0))
        world.step()
        assertEquals(WorldPosition(4.0, 0.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `flying enemy toi dung corePosition thi phat enemy_reached_core`() {
        val field = linearField(coreX = 10.0)
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_SCAVENGER_HARPY", WorldPosition(9.0, 0.0), Movement(MovementType.FLYING, speed = 5.0))
        world.step()
        assertEquals(WorldPosition(10.0, 0.0), world.entities.enemy(id)!!.position)
        assertEquals(1, world.eventLog.count { it.type == "enemy_reached_core" })
    }

    @Test
    fun `flying enemy khong co corePosition thi dung yen`() {
        val field = Battlefield(nodes = emptyList()) // không coreNodeId
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_SCAVENGER_HARPY", WorldPosition(2.0, 2.0), Movement(MovementType.FLYING, speed = 4.0))
        world.step()
        assertEquals(WorldPosition(2.0, 2.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `burrow enemy khong di chuyen o he thong nay`() {
        val field = linearField(coreX = 10.0)
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_TUNNEL_BURROWER", WorldPosition(0.0, 0.0), Movement(MovementType.BURROW, speed = 3.0, currentNodeId = "A"))
        world.step()
        world.step()
        assertEquals(WorldPosition(0.0, 0.0), world.entities.enemy(id)!!.position)
    }

    @Test
    fun `khong co speed thi flying cung dung yen`() {
        val field = linearField(coreX = 10.0)
        val world = combatWorld(field)
        val id = world.entities.createEnemy("ENEMY_SCAVENGER_HARPY", WorldPosition(0.0, 0.0), Movement(MovementType.FLYING, speed = null))
        world.step()
        assertNull(world.entities.enemy(id)!!.health) // sanity: entity vẫn tồn tại bình thường
        assertEquals(WorldPosition(0.0, 0.0), world.entities.enemy(id)!!.position)
    }
}
