package com.lastoutpost.core

import com.lastoutpost.core.combat.DamageFormula
import kotlin.test.Test
import kotlin.test.assertEquals

class DamageFormulaTest {
    @Test
    fun `finalDamage voi moi modifier mac dinh chi bang baseDamage`() {
        assertEquals(10.0, DamageFormula.finalDamage(baseDamage = 10.0))
    }

    @Test
    fun `finalDamage nhan dung tat ca modifier`() {
        val result = DamageFormula.finalDamage(
            baseDamage = 10.0,
            barrelModifier = 1.5,
            moduleModifier = 2.0,
            targetInteraction = 0.5,
            temporaryModifier = 1.2,
        )
        assertEquals(10.0 * 1.5 * 2.0 * 0.5 * 1.2, result)
    }

    @Test
    fun `targetInteraction duoi 1 lam giam damage - khang`() {
        val resisted = DamageFormula.finalDamage(baseDamage = 100.0, targetInteraction = 0.5)
        assertEquals(50.0, resisted)
    }

    @Test
    fun `targetInteraction tren 1 lam tang damage - yeu`() {
        val weak = DamageFormula.finalDamage(baseDamage = 100.0, targetInteraction = 1.5)
        assertEquals(150.0, weak)
    }

    @Test
    fun `damageAfterArmor tru dung effectiveArmor`() {
        assertEquals(70, DamageFormula.damageAfterArmor(rawDamage = 100.0, effectiveArmor = 30.0))
    }

    @Test
    fun `damageAfterArmor khong bao gio duoi 1 du armor rat cao`() {
        assertEquals(1, DamageFormula.damageAfterArmor(rawDamage = 10.0, effectiveArmor = 1000.0))
    }

    @Test
    fun `damageAfterArmor mac dinh khong co armor thi giu nguyen raw`() {
        assertEquals(42, DamageFormula.damageAfterArmor(rawDamage = 42.0))
    }
}
