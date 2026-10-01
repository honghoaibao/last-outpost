package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.battlefield.MountType
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.tower.BaseDefinition
import com.lastoutpost.core.tower.BaseFamily
import com.lastoutpost.core.tower.PlacementCheck
import com.lastoutpost.core.tower.TowerSystem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class TowerSystemTest {
    private val groundBase = BaseDefinition("BASE_TEST_GROUND", 1, BaseFamily.TREADED, setOf(MountType.GROUND))
    private val wallBase = BaseDefinition("BASE_TEST_WALL", 1, BaseFamily.WALL_MOUNT, setOf(MountType.WALL))

    private fun battlefield(vararg nodes: BuildableNode) = Battlefield(nodes.toList())

    private fun system(field: Battlefield) = TowerSystem(
        battlefield = field,
        bases = mapOf(groundBase.id to groundBase, wallBase.id to wallBase),
        barrelCatalog = setOf("BARREL_TEST"),
        moduleCatalog = setOf("MODULE_TEST"),
    )

    private fun combatWorld(system: TowerSystem) = BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = listOf(system)).apply {
        advanceTo(BattlePhase.LOAD_CONTENT)
        advanceTo(BattlePhase.BUILD_WORLD)
        advanceTo(BattlePhase.PREPARE)
        advanceTo(BattlePhase.COMBAT)
    }

    @Test
    fun `build hop le tren node dung mount tao ra tower entity va chiem node`() {
        val field = battlefield(BuildableNode("N1", WorldPosition(5.0, 5.0), MountType.GROUND))
        val sys = system(field)
        val world = combatWorld(sys)
        world.commands.submit(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", nodeId = "N1"))
        world.step()

        assertEquals(1, world.entities.towerIds.size)
        val tower = world.entities.tower(world.entities.towerIds.first())!!
        assertEquals(WorldPosition(5.0, 5.0), tower.position)
        assertEquals(groundBase.id, tower.base.baseId)
        assertTrue(world.eventLog.any { it.type == "build_accepted" })
        assertTrue(field.node("N1")!!.isOccupied)
    }

    @Test
    fun `base khong ho tro mount type cua node thi bi tu choi va khong tao entity`() {
        // groundBase chỉ hỗ trợ GROUND; node là WALL → bị từ chối, lý do nêu đúng mount tại node đó
        // (khớp ví dụ Player UX Flow §8.3: "Không hỗ trợ Wall Mount").
        val field = battlefield(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.WALL))
        val sys = system(field)
        val world = combatWorld(sys)
        world.commands.submit(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", nodeId = "N1"))
        world.step()

        assertEquals(0, world.entities.towerIds.size)
        assertTrue(world.eventLog.any { it.type == "build_rejected" && "Wall Mount" in it.detail })
        assertTrue(field.node("N1")!!.isOccupied.not())
    }

    @Test
    fun `node da bi chiem thi tu choi build thu hai`() {
        val field = battlefield(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND))
        val sys = system(field)
        val world = combatWorld(sys)
        world.commands.submit(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", "N1"))
        world.step()
        world.commands.submit(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", "N1"))
        world.step()

        assertEquals(1, world.entities.towerIds.size) // chỉ tower đầu tiên được tạo
        assertTrue(world.eventLog.any { it.type == "build_rejected" && "đã có công trình" in it.detail })
    }

    @Test
    fun `node khong ton tai bi tu choi voi ly do ro rang`() {
        val check = system(battlefield()).validate(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", "N_GHOST"))
        assertIs<PlacementCheck.Invalid>(check)
        assertTrue("N_GHOST" in check.reason)
    }

    @Test
    fun `base khong ton tai bi tu choi voi ly do ro rang`() {
        val field = battlefield(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND))
        val check = system(field).validate(Command.BuildTower("BASE_GHOST", "BARREL_TEST", "MODULE_TEST", "N1"))
        assertIs<PlacementCheck.Invalid>(check)
        assertTrue("BASE_GHOST" in check.reason)
    }

    @Test
    fun `barrel hoac module khong ton tai bi tu choi`() {
        val field = battlefield(BuildableNode("N1", WorldPosition(0.0, 0.0), MountType.GROUND))
        val sys = system(field)
        assertIs<PlacementCheck.Invalid>(sys.validate(Command.BuildTower(groundBase.id, "BARREL_GHOST", "MODULE_TEST", "N1")))
        assertIs<PlacementCheck.Invalid>(sys.validate(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_GHOST", "N1")))
    }

    @Test
    fun `cancel build xoa dung tower, tra node ve trong, va bao loi neu id khong ton tai`() {
        val field = battlefield(BuildableNode("N1", WorldPosition(1.0, 1.0), MountType.GROUND))
        val sys = system(field)
        val world = combatWorld(sys)
        world.commands.submit(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", "N1"))
        world.step()
        val id = world.entities.towerIds.first()

        world.commands.submit(Command.CancelBuild(id))
        world.step()
        assertEquals(0, world.entities.towerIds.size)
        assertTrue(world.eventLog.any { it.type == "build_cancelled" })
        assertTrue(field.node("N1")!!.isOccupied.not()) // node trở lại trống, xây được tiếp

        world.commands.submit(Command.CancelBuild(id))
        world.step()
        assertTrue(world.eventLog.any { it.type == "build_cancel_rejected" })
    }

    @Test
    fun `node trong khac nhau duoc kiem tra doc lap theo mount type rieng`() {
        val field = battlefield(
            BuildableNode("GROUND_NODE", WorldPosition(0.0, 0.0), MountType.GROUND),
            BuildableNode("WALL_NODE", WorldPosition(1.0, 0.0), MountType.WALL),
        )
        val sys = system(field)
        assertEquals(PlacementCheck.Valid, sys.validate(Command.BuildTower(wallBase.id, "BARREL_TEST", "MODULE_TEST", "WALL_NODE")))
        assertIs<PlacementCheck.Invalid>(sys.validate(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", "WALL_NODE")))
        assertEquals(PlacementCheck.Valid, sys.validate(Command.BuildTower(groundBase.id, "BARREL_TEST", "MODULE_TEST", "GROUND_NODE")))
    }
}
