package com.lastoutpost.core.tower

/**
 * Filter nhóm target của Custom Priority — canon Beta 01 (quyết định Bảo, 2026-09-27): FILTER → PRIMARY
 * ORDER → TIE BREAKER. Rank/lock thật ở `com.lastoutpost.core.targeting.TowerTargetingSystem`.
 *
 * [SPECIAL_THREAT] CHƯA có tiêu chí cụ thể nào trong thiết kế (không phải "Special threat priority" ở
 * System Gameplay §7.2 — đó là enemy chọn mục tiêu tấn công, khác chiều với filter này) → hiện KHÔNG khớp
 * enemy nào (rỗng), không tự đặt tiêu chí.
 */
enum class TargetFilter { ALL, SPECIAL_THREAT, BOSS_ELITE, AIR, GROUND, SWARM }
