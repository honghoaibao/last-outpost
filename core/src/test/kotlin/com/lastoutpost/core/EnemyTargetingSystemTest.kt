package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.battlefield.LaneNode
import com.lastoutpost.core.battlefield.MountType
import com.lastoutpost.core.battlefield.PathGraph
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.EnemySystem
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.targeting.EnemyTargetingSystem
import com.lastoutpost.core.tower.ReferenceBaseCatalog
import com.lastoutpost.core.tower.TowerSystem
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class EnemyTargetingSystemTest {
    private fun battlefieldWithTowersAt(vararg positions: WorldPosition): Battlefield {
        val nodes = positions.mapIndexed { i, pos -> BuildableNode("N$i", pos, MountType.GROUND) }
        return Battlefield(nodes)
    }

    private fun buildTowersAt(world: BattleWorld, field: Battlefield, nodeIds: List<String>) {
        for (nodeId in nodeIds) {
            world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_TEST", "MODULE_TEST", nodeId))
        }
    }

    private fun combatWorld(field: Battlefield, systems: List<com.lastoutpost.core.sim.TickSystem>) =
        BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = systems).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }

    @Test
    fun `enemy co TOWER trong preferred class nham ung dung tower gan nhat`() {
        val field = Battlefield(
            nodes = listOf(
                BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND),
                BuildableNode("N1", WorldPosition(10.0, 0.0), MountType.GROUND),
            ),
            paths = PathGraph(listOf(LaneNode("ENTRY_0", WorldPosition(0.0, 0.0))), emptyList()),
            laneEntryNodes = mapOf(0 to "ENTRY_0"), // enemy spawn ngay cạnh tower N0 → phải nhắm N0, không phải N1
        )
        val towers = TowerSystem(field, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        // EMP Stalker: preferredTargetClasses = [TOWER]
        val roster = FixedRoster(setOf("ENEMY_EMP_STALKER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_EMP_STALKER", count = 1, lane = 0)))
        val enemies = EnemySystem(field)
        val targeting = EnemyTargetingSystem()

        val world = combatWorld(field, listOf(director, enemies, towers, targeting))
        buildTowersAt(world, field, listOf("N0", "N1"))
        world.step() // xây 2 tower + spawn enemy tại N0 (0,0)

        val enemyId = world.entities.enemyIds.first()
        val nearTowerId = world.entities.towerIds.first { world.entities.tower(it)!!.position == WorldPosition(0.0, 0.0) }
        assertEquals(nearTowerId, world.entities.enemy(enemyId)!!.targeting.currentTargetId)
    }

    @Test
    fun `enemy khong co TOWER trong preferred class thi khong co target`() {
        val field = battlefieldWithTowersAt(WorldPosition(0.0, 0.0))
        val towers = TowerSystem(field, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        // Crawler: preferredTargetClasses = [BASE_CORE] — không có entity BASE_CORE thật
        val roster = FixedRoster(setOf("ENEMY_CRAWLER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1)))
        val enemies = EnemySystem()
        val targeting = EnemyTargetingSystem()

        val world = combatWorld(field, listOf(director, enemies, towers, targeting))
        buildTowersAt(world, field, listOf("N0"))
        world.step()

        val enemyId = world.entities.enemyIds.first()
        assertNull(world.entities.enemy(enemyId)!!.targeting.currentTargetId)
    }

    @Test
    fun `khong con tower nao thi target duoc xoa ve null`() {
        val field = battlefieldWithTowersAt(WorldPosition(0.0, 0.0))
        val towers = TowerSystem(field, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val roster = FixedRoster(setOf("ENEMY_EMP_STALKER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_EMP_STALKER", count = 1)))
        val enemies = EnemySystem()
        val targeting = EnemyTargetingSystem()

        val world = combatWorld(field, listOf(director, enemies, towers, targeting))
        buildTowersAt(world, field, listOf("N0"))
        world.step()
        val enemyId = world.entities.enemyIds.first()
        val towerId = world.entities.towerIds.first()
        assertEquals(towerId, world.entities.enemy(enemyId)!!.targeting.currentTargetId)

        world.commands.submit(Command.CancelBuild(towerId))
        world.step()
        assertNull(world.entities.enemy(enemyId)!!.targeting.currentTargetId)
    }
}
