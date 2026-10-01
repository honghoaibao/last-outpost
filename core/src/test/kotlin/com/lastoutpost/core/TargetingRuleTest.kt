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
import com.lastoutpost.core.sim.TickSystem
import com.lastoutpost.core.targeting.TowerTargetingSystem
import com.lastoutpost.core.tower.PrimaryOrder
import com.lastoutpost.core.tower.TargetFilter
import com.lastoutpost.core.tower.TargetingRule
import com.lastoutpost.core.tower.TieBreaker
import com.lastoutpost.core.tower.ReferenceBaseCatalog
import com.lastoutpost.core.tower.TowerSystem
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TargetingRuleTest {
    private fun combatWorld(f: Battlefield, systems: List<TickSystem>) =
        BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = systems).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }

    private fun buildTower(world: BattleWorld, nodeId: String) {
        world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_TEST", "MODULE_TEST", nodeId))
    }

    @Test
    fun `filter AIR chi khop enemy dang bay`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        // ENEMY_CRAWLER (ground) + ENEMY_SCAVENGER_HARPY (flying) cùng roster
        val director = WaveDirector(
            FixedRoster(setOf("ENEMY_CRAWLER", "ENEMY_SCAVENGER_HARPY")),
            listOf(
                SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1),
                SpawnGroup(atTick = 0, speciesId = "ENEMY_SCAVENGER_HARPY", count = 1),
            ),
        )
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(filter = TargetFilter.AIR))) }
        world.step()

        val lockedId = world.entities.tower(towerId)!!.targeting.currentTargetId
        assertEquals("ENEMY_SCAVENGER_HARPY", world.entities.enemy(lockedId!!)!!.speciesId)
    }

    @Test
    fun `filter SWARM chi khop role Swarm (Crawler)`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(
            FixedRoster(setOf("ENEMY_CRAWLER", "ENEMY_ACID_BLOATER")),
            listOf(
                SpawnGroup(atTick = 0, speciesId = "ENEMY_ACID_BLOATER", count = 1), // Role: Structure Punisher, không phải Swarm
                SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1), // Role: Swarm
            ),
        )
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(filter = TargetFilter.SWARM))) }
        world.step()

        val lockedId = world.entities.tower(towerId)!!.targeting.currentTargetId
        assertEquals("ENEMY_CRAWLER", world.entities.enemy(lockedId!!)!!.speciesId)
    }

    @Test
    fun `filter BOSS_ELITE chi khop role Boss`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(
            FixedRoster(setOf("ENEMY_CRAWLER", "BOSS_COLOSSUS")),
            listOf(
                SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1),
                SpawnGroup(atTick = 0, speciesId = "BOSS_COLOSSUS", count = 1),
            ),
        )
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(filter = TargetFilter.BOSS_ELITE))) }
        world.step()

        val lockedId = world.entities.tower(towerId)!!.targeting.currentTargetId
        assertEquals("BOSS_COLOSSUS", world.entities.enemy(lockedId!!)!!.speciesId)
    }

    @Test
    fun `filter SPECIAL_THREAT chua co tieu chi nen khong khop ai`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1)))
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(filter = TargetFilter.SPECIAL_THREAT))) }
        world.step()

        assertNull(world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `primary order STRONGEST chon Max HP cao nhat, ho tro null xep cuoi`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 2)))
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val ids = world.entities.enemyIds.toList()
        // gán Health thủ công cho test (bình thường luôn null — OQ-CONTENT-02)
        world.entities.updateEnemy(ids[0]) { it.copy(health = com.lastoutpost.core.entity.Health(current = 10, max = 50)) }
        world.entities.updateEnemy(ids[1]) { it.copy(health = com.lastoutpost.core.entity.Health(current = 80, max = 100)) }

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(primaryOrder = PrimaryOrder.STRONGEST))) }
        world.step()

        assertEquals(ids[1], world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `primary order LOWEST_HP chon Current HP thap nhat`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 2)))
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val ids = world.entities.enemyIds.toList()
        world.entities.updateEnemy(ids[0]) { it.copy(health = com.lastoutpost.core.entity.Health(current = 10, max = 50)) }
        world.entities.updateEnemy(ids[1]) { it.copy(health = com.lastoutpost.core.entity.Health(current = 80, max = 100)) }

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(primaryOrder = PrimaryOrder.LOWEST_HP))) }
        world.step()

        assertEquals(ids[0], world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `primary order FIRST dung corePosition khi battlefield co coreNodeId`() {
        val f = Battlefield(
            nodes = listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)),
            paths = PathGraph(listOf(LaneNode("CORE", WorldPosition(20.0, 0.0))), emptyList()),
            coreNodeId = "CORE",
        )
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0, battlefield = f)
        val director = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 2)))
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val ids = world.entities.enemyIds.toList()
        // enemy gần Core hơn (x=15) phải được chọn dù xa Tower hơn enemy còn lại (x=1)
        world.entities.updateEnemy(ids[0]) { it.copy(position = WorldPosition(1.0, 0.0)) }
        world.entities.updateEnemy(ids[1]) { it.copy(position = WorldPosition(15.0, 0.0)) }

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) { it.copy(targeting = it.targeting.copy(rule = TargetingRule(primaryOrder = PrimaryOrder.FIRST))) }
        world.step()

        assertEquals(ids[1], world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `tie breaker NEAREST duoc dung khi primary order bang nhau`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 2)))
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val ids = world.entities.enemyIds.toList()
        // Cùng Max HP (STRONGEST hoà) — tie breaker NEAREST phải chọn enemy gần Tower hơn
        world.entities.updateEnemy(ids[0]) { it.copy(health = com.lastoutpost.core.entity.Health(50, 50), position = WorldPosition(5.0, 0.0)) }
        world.entities.updateEnemy(ids[1]) { it.copy(health = com.lastoutpost.core.entity.Health(50, 50), position = WorldPosition(1.0, 0.0)) }

        val towerId = world.entities.towerIds.first()
        world.entities.updateTower(towerId) {
            it.copy(targeting = it.targeting.copy(rule = TargetingRule(primaryOrder = PrimaryOrder.STRONGEST, tieBreaker = TieBreaker.NEAREST)))
        }
        world.step()

        assertEquals(ids[1], world.entities.tower(towerId)!!.targeting.currentTargetId)
    }

    @Test
    fun `khong co tie breaker thi fallback id dam bao tat dinh`() {
        val f = Battlefield(listOf(BuildableNode("N0", WorldPosition(0.0, 0.0), MountType.GROUND)))
        val towers = TowerSystem(f, barrelCatalog = setOf("BARREL_TEST"), moduleCatalog = setOf("MODULE_TEST"))
        val enemies = EnemySystem(f)
        val ranged = TowerTargetingSystem(range = 100.0)
        val director = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 2)))
        val world = combatWorld(f, listOf(director, enemies, towers, ranged))
        buildTower(world, "N0")
        world.step()

        val ids = world.entities.enemyIds.toList().sorted()
        // cả hai cùng vị trí, cùng HP — không tie breaker → id nhỏ hơn thắng (ổn định, không crash)
        world.entities.updateEnemy(ids[0]) { it.copy(position = WorldPosition(3.0, 0.0)) }
        world.entities.updateEnemy(ids[1]) { it.copy(position = WorldPosition(3.0, 0.0)) }

        val towerId = world.entities.towerIds.first()
        world.step()

        assertEquals(ids[0], world.entities.tower(towerId)!!.targeting.currentTargetId)
    }
}
