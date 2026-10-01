package com.lastoutpost.core.enemy

/**
 * Loại di chuyển — AI & Simulation Design §IV (Ground/Flying/Burrow). "Dynamic Terrain" trong tài liệu là
 * thay đổi ở bản đồ (tường/bẫy/đường ray), không phải một loại di chuyển của enemy, nên không đưa vào đây.
 */
enum class MovementType { GROUND, FLYING, BURROW }
