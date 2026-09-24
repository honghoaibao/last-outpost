package com.lastoutpost.core

import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.sim.ResultState
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BattleWorldTest {
    private fun world(systems: List<TickSystem> = emptyList()) =
        BattleWorld(missionId = "MISSION_TEST_001", contentVersion = "test", seed = 42L, systems = systems)

    private fun BattleWorld.toCombat() {
        advanceTo(BattlePhase.LOAD_CONTENT)
        advanceTo(BattlePhase.BUILD_WORLD)
        advanceTo(BattlePhase.PREPARE)
        advanceTo(BattlePhase.COMBAT)
    }

    @Test
    fun `vong doi phase chi di tuan tu`() {
        val w = world()
        assertFailsWith<IllegalStateException> { w.advanceTo(BattlePhase.COMBAT) }
        w.toCombat()
        assertEquals(BattlePhase.COMBAT, w.phase)
    }

    @Test
    fun `khong the roi combat bang advanceTo ma khong co ket qua`() {
        val w = world().apply { toCombat() }
        assertFailsWith<IllegalStateException> { w.advanceTo(BattlePhase.CALCULATE_RESULT) }
        w.endCombat(ResultState.VICTORY)
        assertEquals(BattlePhase.CALCULATE_RESULT, w.phase)
        assertEquals(ResultState.VICTORY, w.resultState)
    }

    @Test
    fun `step chi chay khi dang o COMBAT`() {
        val w = world()
        assertFalse(w.step())
        assertEquals(0L, w.tick)
        w.toCombat()
        assertTrue(w.step())
        assertEquals(1L, w.tick)
    }

    @Test
    fun `system chay theo thu tu stage bat ke thu tu dang ky`() {
        val order = mutableListOf<TickStage>()
        fun probe(s: TickStage) = object : TickSystem {
            override val stage = s
            override fun update(world: BattleWorld) {
                order += s
            }
        }
        val w = world(listOf(probe(TickStage.OBJECTIVES), probe(TickStage.MOVEMENT), probe(TickStage.WAVE_DIRECTOR)))
        w.toCombat()
        w.step()
        assertEquals(listOf(TickStage.WAVE_DIRECTOR, TickStage.MOVEMENT, TickStage.OBJECTIVES), order)
    }

    @Test
    fun `command duoc xu ly o buoc dau cua tick va ghi log kem tick`() {
        val w = world().apply { toCombat() }
        w.step() // tick 0 — chưa có command
        w.commands.submit(Command.MoveCommander(3, 4))
        w.commands.submit(Command.Repair(entityId = 7L))
        w.step() // tick 1
        assertEquals(0, w.commands.size)
        assertEquals(listOf(1L, 1L), w.commandLog.map { it.tick })
        assertEquals(Command.MoveCommander(3, 4), w.commandLog[0].command)
    }

    @Test
    fun `cung seed cho cung chuoi so ngau nhien`() {
        val a = BattleWorld("MISSION_TEST_001", "test", seed = 123L)
        val b = BattleWorld("MISSION_TEST_001", "test", seed = 123L)
        assertEquals(List(5) { a.rng.nextInt() }, List(5) { b.rng.nextInt() })
    }
}
