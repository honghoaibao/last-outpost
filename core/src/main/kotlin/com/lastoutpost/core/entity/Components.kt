package com.lastoutpost.core.entity

/**
 * Máu — Tech Architecture §V liệt kê Health ở cả TowerEntity lẫn EnemyEntity, dùng chung một cấu trúc.
 *
 * Vị trí không gian của entity dùng [com.lastoutpost.core.battlefield.WorldPosition] (ADR-0005) — không
 * đặt ở đây vì đó là khái niệm thuộc lớp battlefield, không phải một "component" trung lập như Health.
 */
data class Health(val current: Int, val max: Int)
