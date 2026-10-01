package com.lastoutpost.core.entity

/**
 * Damage tag — System & Gameplay Design v1.1 §6.4 (Combat Resolution): đúng 7 tag liệt kê. Dùng thay cho
 * "hàng chục stat ẩn" — resistance/weakness của enemy chỉ nên có vài tag rõ ràng (`EnemyEntity.resistances`).
 */
enum class DamageTag { KINETIC, FIRE, ACID, ELECTRIC, FREEZE, EXPLOSIVE, PHYSICAL_CONTROL }
