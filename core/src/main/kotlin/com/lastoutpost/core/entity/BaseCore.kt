package com.lastoutpost.core.entity

/**
 * Base Core — System & Gameplay Design v1.1 §17.1: "objective chính của trận", thuộc tính HP / Shield nếu mở
 * khoá / emergency state. Chỉ model HP ([health], dùng lại [Health]); Shield (phụ thuộc unlock) và ngưỡng
 * emergency (§17.2 "Core xuống ngưỡng thấp" — tài liệu không nêu ngưỡng) CHƯA model (TECH_DEBT).
 *
 * Không phải entity trong [EntityRegistry] (Tech Architecture §V chỉ có Tower/Enemy) mà là trạng thái mission
 * trên `BattleWorld.core`; `null` nghĩa là mission chưa cấu hình Core → không có objective tự động.
 */
data class BaseCore(val health: Health) {
    val isDestroyed: Boolean get() = health.current <= 0
}
