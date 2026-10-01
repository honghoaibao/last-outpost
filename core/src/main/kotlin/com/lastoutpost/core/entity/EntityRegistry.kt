package com.lastoutpost.core.entity

import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.Behavior
import com.lastoutpost.core.enemy.EnemyEntity
import com.lastoutpost.core.enemy.Movement
import com.lastoutpost.core.enemy.ThreatProfile
import com.lastoutpost.core.tower.BarrelState
import com.lastoutpost.core.tower.BaseState
import com.lastoutpost.core.tower.ModuleState
import com.lastoutpost.core.tower.TowerEntity

/**
 * Entity Registry — Tech Architecture §III (Gameplay Runtime) / §V (Entity·Data Model).
 *
 * ID dùng chung một bộ đếm cho MỌI loại entity (Tower, Enemy, và Projectile khi triển khai) để không loại
 * nào trùng ID, nhưng lưu trữ theo bảng riêng từng loại — chưa cần ECS generic đầy đủ vì hiện chỉ có 2 loại.
 */
class EntityRegistry {
    private var nextId = 1L
    private val towers = LinkedHashMap<Long, TowerEntity>()
    private val enemies = LinkedHashMap<Long, EnemyEntity>()

    private fun allocate(): Long = nextId++

    fun createTower(position: WorldPosition, base: BaseState, barrel: BarrelState, module: ModuleState): Long {
        val id = allocate()
        towers[id] = TowerEntity(id, position, base, barrel, module)
        return id
    }

    fun destroyTower(id: Long): Boolean = towers.remove(id) != null

    fun tower(id: Long): TowerEntity? = towers[id]

    val towerIds: Set<Long> get() = towers.keys

    /** Cập nhật tower tại chỗ nếu còn tồn tại (no-op nếu đã bị xoá — vd trong cùng tick). */
    fun updateTower(id: Long, mutate: (TowerEntity) -> TowerEntity) {
        towers[id]?.let { towers[id] = mutate(it) }
    }

    fun createEnemy(
        speciesId: String,
        position: WorldPosition,
        movement: Movement,
        threatProfile: ThreatProfile = ThreatProfile(),
    ): Long {
        val id = allocate()
        enemies[id] = EnemyEntity(
            id = id,
            speciesId = speciesId,
            position = position,
            movement = movement,
            behavior = Behavior(archetypeId = speciesId),
            threatProfile = threatProfile,
        )
        return id
    }

    fun destroyEnemy(id: Long): Boolean = enemies.remove(id) != null

    fun enemy(id: Long): EnemyEntity? = enemies[id]

    val enemyIds: Set<Long> get() = enemies.keys

    /** Cập nhật enemy tại chỗ nếu còn tồn tại (no-op nếu đã bị xoá — vd trong cùng tick). */
    fun updateEnemy(id: Long, mutate: (EnemyEntity) -> EnemyEntity) {
        enemies[id]?.let { enemies[id] = mutate(it) }
    }
}
