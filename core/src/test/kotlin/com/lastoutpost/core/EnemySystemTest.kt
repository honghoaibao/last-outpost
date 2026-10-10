package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.LaneNode
import com.lastoutpost.core.battlefield.PathGraph
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.AIState
import com.lastoutpost.core.enemy.EnemySystem
import com.lastoutpost.core.enemy.MovementType
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EnemySystemTest {
    private fun combatWorld(systems: List<com.lastoutpost.core.sim.TickSystem>) =
        BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = systems).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }

    @Test
    fun `enemy system tieu thu spawnQueue va tao dung so luong enemy entity`() {
        val roster = FixedRoster(setOf("ENEMY_CRAWLER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 3)))
        val world = combatWorld(listOf(director, EnemySystem()))

        world.step()

        assertEquals(3, world.entities.enemyIds.size)
        assertEquals(0, world.spawnQueue.size) // đã được EnemySystem tiêu thụ hết
        assertEquals(3, world.eventLog.count { it.type == "enemy_spawned" })
    }

    @Test
    fun `enemy tao ra dung movement type va threat cost theo catalog`() {
        val roster = FixedRoster(setOf("ENEMY_SCAVENGER_HARPY"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_SCAVENGER_HARPY", count = 1)))
        val world = combatWorld(listOf(director, EnemySystem()))

        world.step()

        val enemy = world.entities.enemy(world.entities.enemyIds.first())!!
        assertEquals(MovementType.FLYING, enemy.movement.type)
        assertEquals(7, enemy.threatProfile.threatCost)
        assertEquals(AIState.SPAWN, enemy.behavior.state)
        assertEquals("ENEMY_SCAVENGER_HARPY", enemy.speciesId)
    }

    @Test
    fun `species khong co trong catalog van tao duoc enemy voi gia tri mac dinh an toan`() {
        val roster = FixedRoster(setOf("ENEMY_UNKNOWN_TEST"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_UNKNOWN_TEST", count = 1)))
        val world = combatWorld(listOf(director, EnemySystem()))

        world.step()

        val enemy = world.entities.enemy(world.entities.enemyIds.first())!!
        assertEquals(MovementType.GROUND, enemy.movement.type) // fallback an toàn
        assertEquals(null, enemy.threatProfile.threatCost) // không bịa số liệu cho species lạ
    }

    @Test
    fun `khong co battlefield hoac lane chua khai bao thi spawn tai vi tri placeholder (0,0)`() {
        val roster = FixedRoster(setOf("ENEMY_CRAWLER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1, lane = 0)))
        val world = combatWorld(listOf(director, EnemySystem())) // không truyền battlefield

        world.step()

        val enemy = world.entities.enemy(world.entities.enemyIds.first())!!
        assertEquals(WorldPosition(0.0, 0.0), enemy.position)
    }

    @Test
    fun `co battlefield voi lane entry thi spawn dung vi tri entry node`() {
        // Entry node phải là LaneNode trên Path Graph (không phải BuildableNode) để MovementSystem path được.
        val field = Battlefield(
            nodes = emptyList(),
            paths = PathGraph(listOf(LaneNode("ENTRY_LANE_0", WorldPosition(12.0, 3.0))), emptyList()),
            laneEntryNodes = mapOf(0 to "ENTRY_LANE_0"),
        )
        val roster = FixedRoster(setOf("ENEMY_CRAWLER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1, lane = 0)))
        val world = combatWorld(listOf(director, EnemySystem(battlefield = field)))

        world.step()

        val enemy = world.entities.enemy(world.entities.enemyIds.first())!!
        assertEquals(WorldPosition(12.0, 3.0), enemy.position)
    }

    @Test
    fun `khong co EnemySystem thi spawnQueue khong bi tieu thu`() {
        val roster = FixedRoster(setOf("ENEMY_CRAWLER"))
        val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 2)))
        val world = combatWorld(listOf(director)) // không có EnemySystem

        world.step()

        assertEquals(1, world.spawnQueue.size) // 1 SpawnIntent (count=2 nằm trong intent, chưa bị tiêu thụ)
        assertEquals(2, world.spawnQueue.first().count)
        assertTrue(world.entities.enemyIds.isEmpty())
    }
}
