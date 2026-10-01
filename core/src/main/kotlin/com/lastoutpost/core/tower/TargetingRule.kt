package com.lastoutpost.core.tower

/**
 * Custom Priority Beta 01 — FILTER → PRIMARY ORDER → TIE BREAKER (canon, quyết định Bảo 2026-09-27).
 * Thay cho enum phẳng cũ (`TargetPriority`, đã xoá): các mode cũ (First/Nearest/Strongest/Lowest HP/Special
 * Threat) đều là trường hợp riêng của cấu trúc này — vd "Special Threat" tương đương
 * `TargetingRule(filter = SPECIAL_THREAT, primaryOrder = NEAREST)` (khớp ví dụ "Special Threat → Nearest").
 *
 * KHÔNG hỗ trợ ở Beta 01 (theo quyết định): nested conditions, AND/OR rule chain, HP/distance/time/resource
 * threshold, custom scripting, nhiều tầng priority tuỳ ý.
 *
 * Ví dụ hợp lệ: `TargetingRule(SPECIAL_THREAT, NEAREST)`, `TargetingRule(AIR, STRONGEST)`,
 * `TargetingRule(ALL, LOWEST_HP, NEAREST)`, `TargetingRule(BOSS_ELITE, FIRST)`.
 */
data class TargetingRule(
    val filter: TargetFilter = TargetFilter.ALL,
    val primaryOrder: PrimaryOrder = PrimaryOrder.NEAREST,
    val tieBreaker: TieBreaker? = null,
) {
    companion object {
        /** Mặc định khi chưa cấu hình — GIẢ ĐỊNH kỹ thuật, tài liệu không nêu default toàn cục cho mọi Tower. */
        val DEFAULT = TargetingRule()
    }
}
