package com.lastoutpost.core.tower

/**
 * Thứ tự chính của Custom Priority — canon Beta 01 (quyết định Bảo, 2026-09-27).
 * Ngữ nghĩa (đúng theo quyết định):
 * - FIRST: tiến xa nhất trên path/lane về phía Core.
 * - NEAREST: khoảng cách spatial gần Tower nhất.
 * - STRONGEST: Max HP cao nhất trong tập target hợp lệ.
 * - LOWEST_HP: Current HP thấp nhất.
 */
enum class PrimaryOrder { FIRST, NEAREST, STRONGEST, LOWEST_HP }
