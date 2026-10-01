package com.lastoutpost.core.entity

/**
 * Target class — AI & Simulation Design §III ("Target classes"). Mỗi enemy chỉ được dùng class phù hợp với
 * archetype của nó (xem `ReferenceEnemyCatalog.Entry.preferredTargetClasses`).
 *
 * Hiện chỉ [TOWER] có entity đại diện thật trong `:core` (`TowerEntity`) — các class còn lại (Base Core,
 * Wall/Gate, Logistics Hub, Ammo Route, Power Node, Commander, Civilian/Internal Asset, Drone) CHƯA có
 * entity, nên enemy nhắm các class đó sẽ chưa tìm được mục tiêu thật cho tới khi các hệ thống tương ứng
 * (Outpost, Logistics, Commander...) tồn tại.
 */
enum class TargetClass {
    BASE_CORE, WALL_GATE, TOWER, LOGISTICS_HUB, AMMO_ROUTE, POWER_NODE, COMMANDER, CIVILIAN_ASSET, DRONE,
}
