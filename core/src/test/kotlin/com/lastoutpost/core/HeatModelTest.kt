package com.lastoutpost.core

import com.lastoutpost.core.combat.HeatBand
import com.lastoutpost.core.combat.HeatModel
import com.lastoutpost.core.tower.Heat
import kotlin.test.Test
import kotlin.test.assertEquals

class HeatModelTest {
    @Test
    fun `0 den duoi 70 phan tram la Normal`() {
        assertEquals(HeatBand.NORMAL, HeatModel.band(Heat(current = 0, max = 100)))
        assertEquals(HeatBand.NORMAL, HeatModel.band(Heat(current = 69, max = 100)))
    }

    @Test
    fun `70 den duoi 90 phan tram la Warm`() {
        assertEquals(HeatBand.WARM, HeatModel.band(Heat(current = 70, max = 100)))
        assertEquals(HeatBand.WARM, HeatModel.band(Heat(current = 89, max = 100)))
    }

    @Test
    fun `90 den duoi 100 phan tram la Critical`() {
        assertEquals(HeatBand.CRITICAL, HeatModel.band(Heat(current = 90, max = 100)))
        assertEquals(HeatBand.CRITICAL, HeatModel.band(Heat(current = 99, max = 100)))
    }

    @Test
    fun `dung 100 phan tram la Jam`() {
        assertEquals(HeatBand.JAM, HeatModel.band(Heat(current = 100, max = 100)))
    }

    @Test
    fun `hoat dong dung voi max khac 100`() {
        assertEquals(HeatBand.WARM, HeatModel.band(Heat(current = 35, max = 50))) // 70%
        assertEquals(HeatBand.JAM, HeatModel.band(Heat(current = 50, max = 50))) // 100%
    }

    @Test
    fun `max khong hop le thi coi nhu Normal, khong chia cho 0`() {
        assertEquals(HeatBand.NORMAL, HeatModel.band(Heat(current = 10, max = 0)))
    }
}
