package com.lastoutpost.core.combat

import kotlin.math.max

/**
 * Damage Model — System & Gameplay Design v1.1 §6.3 (Combat Resolution):
 * ```
 * Final Damage = Base Damage × Barrel Modifier × Module Modifier × Target Interaction × Temporary Modifier
 * Damage After Armor = max(1, Raw Damage − Effective Armor)
 * ```
 *
 * [barrelModifier]/[moduleModifier]/[temporaryModifier] mặc định 1.0 (trung tính) — Module/Tech/Battle/
 * Season-Hazard Modifier (Stat pipeline §5.2) CHƯA có nguồn số liệu nào, không tự đặt hệ số khác 1.0.
 * [targetInteraction]: quy ước — giá trị resistance LÀ hệ số nhân trực tiếp (`EnemyEntity.resistances`,
 * xem KDoc ở đó) — tài liệu không nêu công thức chính xác, đây là diễn giải hợp lý nhất, CHƯA xác nhận.
 */
object DamageFormula {
    fun finalDamage(
        baseDamage: Double,
        barrelModifier: Double = 1.0,
        moduleModifier: Double = 1.0,
        targetInteraction: Double = 1.0,
        temporaryModifier: Double = 1.0,
    ): Double = baseDamage * barrelModifier * moduleModifier * targetInteraction * temporaryModifier

    /** `effectiveArmor` mặc định 0.0 (không giảm damage) khi `EnemyEntity.armor` chưa có số liệu. */
    fun damageAfterArmor(rawDamage: Double, effectiveArmor: Double = 0.0): Int =
        max(1.0, rawDamage - effectiveArmor).toInt()
}
