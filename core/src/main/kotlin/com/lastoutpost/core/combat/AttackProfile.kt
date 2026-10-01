package com.lastoutpost.core.combat

import com.lastoutpost.core.entity.DamageTag

/**
 * Số liệu tấn công của một Barrel, keyed theo `barrelId` — CHƯA có trong content thật (OQ-DESIGN-02), PHẢI
 * truyền từ ngoài vào [TowerAttackSystem] (giống cách `range` được truyền vào
 * [com.lastoutpost.core.targeting.TowerTargetingSystem]).
 *
 * [baseDamage]/[cooldownTicks] KHÔNG phải số liệu canon — chỉ để hệ thống chạy được (demo/test).
 * [damageTag] LÀ canon (Combat Resolution §6.4, đúng 7 tag).
 */
data class AttackProfile(val baseDamage: Double, val cooldownTicks: Int, val damageTag: DamageTag)
