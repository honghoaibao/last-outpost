package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.battlefield.MountType
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.EnemySystem
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.targeting.TowerTargetingSystem
import com.lastoutpost.core.tower.ReferenceBaseCatalog
import com.lastoutpost.core.tower.TowerSystem
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TowerTargetingSystemTest {
    private fun field() = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))

    private fun combatWorld(f: Battlefield, systems: List<com.lastoutpost.core.sim.TickSystem>) =
        BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = systems).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }

    @Test
    fun `tower lock enemy gan nhat trong range`() {
        val f = Battlefield(
            nodes = listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)),
            laneEntryNodes = mapOf(0 to "N0"),
        )
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(
            FixedRoster(setOf("ENEMY_CRAWLER")),
            listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1, lane = 0)),
        )

        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_TEST", "MODULE_TEST", "N0"))
        world.step()

        val towerId = world.entities.towerIds.first()
        val enemyId = world.entities.enemyIds.first()
        assertEquals(enemyId, world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `enemy ngoai range thi khong bi lock`() {
        val f = Battlefield(
            nodes = listOf(
                BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND),
                BuildableNode("FAR", WorldPosition(1000.0, 0.0), MountType.GROUND),
            ),
            laneEntryNodes = mapOf(0 to "FAR"),
        )
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 50.0) // enemy ở (1000,0) — ngoài range
        val director = WaveDirector(
            FixedRoster(setOf("ENEMY_CRAWLER")),
            listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1, lane = 0)),
        )

        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_TEST", "MODULE_TEST", "N0"))
        world.step()

        val towerId = world.entities.towerIds.first()
        assertNull(world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `mat target thi lock duoc xoa ve null`() {
        val f = field()
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(
            FixedRoster(setOf("ENEMY_CRAWLER")),
            listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1)),
        )

        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_TEST", "MODULE_TEST", "N0"))
        world.step()
        val towerId = world.entities.towerIds.first()
        val enemyId = world.entities.enemyIds.first()
        assertEquals(enemyId, world.entities.tower(towerId)!!.targeting.currentTargetId)

        world.entities.destroyEnemy(enemyId)
        world.step()
        assertNull(world.entities.tower(towerId)!!.targeting.currentTargetId)
    }
}
