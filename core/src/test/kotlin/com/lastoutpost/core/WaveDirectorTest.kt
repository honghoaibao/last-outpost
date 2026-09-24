package com.lastoutpost.core

import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.RosterViolationException
import com.lastoutpost.core.wave.SpawnDecision
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WaveDirectorTest {
    private val roster = FixedRoster(setOf("ENEMY_CRAWLER", "ENEMY_ACID_BLOATER"))

    private fun combatWorld(director: WaveDirector) =
        BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = listOf(director)).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }

    @Test
    fun `ke hoach co species ngoai roster bi tu choi ngay khi khoi tao`() {
        val plan = listOf(SpawnGroup(0, "ENEMY_CRAWLER", 3), SpawnGroup(5, "ENEMY_EMP_STALKER", 1))
        val ex = assertFailsWith<RosterViolationException> { WaveDirector(roster, plan) }
        assertEquals(listOf("ENEMY_EMP_STALKER"), ex.violations.map { it.speciesId })
    }

    @Test
    fun `phat spawn intent dung tick theo ke hoach`() {
        val director = WaveDirector(
            roster,
            listOf(SpawnGroup(2, "ENEMY_CRAWLER", 4, lane = 1), SpawnGroup(4, "ENEMY_ACID_BLOATER", 1)),
        )
        val w = combatWorld(director)
        repeat(3) { w.step() } // tick 0,1,2 → chỉ nhóm đầu
        assertEquals(1, w.spawnQueue.size)
        assertEquals("ENEMY_CRAWLER", w.spawnQueue.first().speciesId)
        assertEquals(2L, w.spawnQueue.first().tick)
        repeat(2) { w.step() } // tick 3,4 → nhóm hai
        assertEquals(listOf("ENEMY_CRAWLER", "ENEMY_ACID_BLOATER"), w.spawnQueue.map { it.speciesId })
    }

    @Test
    fun `tryQueue tu choi species ngoai roster va khong dua vao hang doi`() {
        val director = WaveDirector(roster, emptyList())
        val w = combatWorld(director)
        val decision = director.tryQueue(w, SpawnGroup(0, "BOSS_COLOSSUS", 1))
        assertTrue(decision is SpawnDecision.Rejected)
        assertEquals(0, w.spawnQueue.size)
        assertTrue(w.eventLog.any { it.type == "spawn_rejected" })
    }

    @Test
    fun `roster rong bi tu choi`() {
        assertFailsWith<IllegalArgumentException> { FixedRoster(emptySet()) }
    }
}
