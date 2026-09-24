package com.lastoutpost.core

import com.lastoutpost.core.sim.FixedTickClock
import com.lastoutpost.core.sim.SimConfig
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FixedTickClockTest {
    private val config = SimConfig(ticksPerSecond = 25) // 40 ms / tick

    @Test
    fun `mot frame du dai bang mot tick thi chay dung mot tick`() {
        val clock = FixedTickClock(config)
        assertEquals(1, clock.advance(40_000_000L))
        assertEquals(0L, clock.pendingNanos)
    }

    @Test
    fun `phan du duoc cong don qua cac frame`() {
        val clock = FixedTickClock(config)
        assertEquals(0, clock.advance(30_000_000L))
        assertEquals(1, clock.advance(30_000_000L)) // 60 ms → 1 tick, dư 20 ms
        assertEquals(20_000_000L, clock.pendingNanos)
        assertEquals(1, clock.advance(20_000_000L)) // 40 ms → 1 tick
    }

    @Test
    fun `backlog vuot gioi han thi bi cat de tranh spiral of death`() {
        val clock = FixedTickClock(SimConfig(ticksPerSecond = 25, maxTicksPerFrame = 5))
        assertEquals(5, clock.advance(10_000_000_000L)) // 10 giây
        assertEquals(0L, clock.pendingNanos)
    }

    @Test
    fun `thoi gian am bi tu choi`() {
        assertFailsWith<IllegalArgumentException> { FixedTickClock(config).advance(-1L) }
    }

    @Test
    fun `tick rate ngoai khoang 20 den 30 bi tu choi`() {
        assertFailsWith<IllegalArgumentException> { SimConfig(ticksPerSecond = 19) }
        assertFailsWith<IllegalArgumentException> { SimConfig(ticksPerSecond = 31) }
    }
}
