package com.lastoutpost.core.tower

import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.entity.Health

data class BaseState(val baseId: String)
data class BarrelState(val barrelId: String)
data class ModuleState(val moduleId: String)

data class Ammo(val current: Int, val max: Int)
data class Heat(val current: Int, val max: Int)
data class Power(val current: Int, val max: Int)

/**
 * Khung tối thiểu. [rule] mặc định [TargetingRule.DEFAULT] — giả định kỹ thuật (tài liệu không nêu default
 * toàn cục). Rank/Lock thật ở [com.lastoutpost.core.targeting.TowerTargetingSystem].
 */
data class Targeting(val currentTargetId: Long? = null, val rule: TargetingRule = TargetingRule.DEFAULT)

/** Khung tối thiểu — logic bắn/sát thương thật thuộc milestone Attack/Projectile (chưa triển khai). */
data class Attack(val cooldownRemainingTicks: Int = 0)

/**
 * TowerEntity — Tech Architecture §V (Transform, BaseState, BarrelState, ModuleState, Health, Ammo, Heat, Power,
 * Targeting, Attack). [position] là vị trí Buildable Node đã đặt (ADR-0005), không phải toạ độ tự do.
 *
 * [health]/[ammo]/[heat]/[power] là `null` cho tới khi [BaseDefinition.baseStats] có số liệu canon
 * (OQ-DESIGN-02/OQ-CONTENT-02) — KHÔNG tự đặt giá trị khởi tạo giả.
 */
data class TowerEntity(
    val id: Long,
    val position: WorldPosition,
    val base: BaseState,
    val barrel: BarrelState,
    val module: ModuleState,
    val health: Health? = null,
    val ammo: Ammo? = null,
    val heat: Heat? = null,
    val power: Power? = null,
    val targeting: Targeting = Targeting(),
    val attack: Attack = Attack(),
)
