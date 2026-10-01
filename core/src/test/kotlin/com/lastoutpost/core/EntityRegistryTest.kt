package com.lastoutpost.core

import com.lastoutpost.core.entity.EntityRegistry
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.AIState
import com.lastoutpost.core.enemy.Movement
import com.lastoutpost.core.enemy.MovementType
import com.lastoutpost.core.enemy.ThreatProfile
import com.lastoutpost.core.tower.BarrelState
import com.lastoutpost.core.tower.BaseState
import com.lastoutpost.core.tower.ModuleState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

class EntityRegistryTest {
    @Test
    fun `tao tower tra ve id tang dan va doc lai duoc dung du lieu`() {
        val registry = EntityRegistry()
        val a = registry.createTower(WorldPosition(1.0, 2.0), BaseState("BASE_TREADED"), BarrelState("BARREL_X"), ModuleState("MODULE_X"))
        val b = registry.createTower(WorldPosition(3.0, 4.0), BaseState("BASE_TREADED"), BarrelState("BARREL_X"), ModuleState("MODULE_X"))
        assertNotEquals(a, b)
        assertEquals(WorldPosition(1.0, 2.0), registry.tower(a)?.position)
        assertEquals(setOf(a, b), registry.towerIds)
    }

    @Test
    fun `xoa tower thi khong con truy van duoc va tra ve false neu id khong ton tai`() {
        val registry = EntityRegistry()
        val id = registry.createTower(WorldPosition(0.0, 0.0), BaseState("B"), BarrelState("R"), ModuleState("M"))
        assertEquals(true, registry.destroyTower(id))
        assertNull(registry.tower(id))
        assertEquals(false, registry.destroyTower(id))
        assertEquals(false, registry.destroyTower(9999L))
    }

    @Test
    fun `tower moi chua co health ammo heat power vi chua co so lieu canon`() {
        val registry = EntityRegistry()
        val id = registry.createTower(WorldPosition(0.0, 0.0), BaseState("B"), BarrelState("R"), ModuleState("M"))
        val tower = registry.tower(id)!!
        assertNull(tower.health)
        assertNull(tower.ammo)
        assertNull(tower.heat)
        assertNull(tower.power)
    }

    @Test
    fun `tao enemy tra ve id rieng du chung bo dem voi tower`() {
        val registry = EntityRegistry()
        val towerId = registry.createTower(WorldPosition(0.0, 0.0), BaseState("B"), BarrelState("R"), ModuleState("M"))
        val enemyId = registry.createEnemy("ENEMY_CRAWLER", WorldPosition(1.0, 1.0), Movement(MovementType.GROUND))
        assertNotEquals(towerId, enemyId)
        assertEquals("ENEMY_CRAWLER", registry.enemy(enemyId)?.speciesId)
        assertEquals(setOf(enemyId), registry.enemyIds)
        assertEquals(setOf(towerId), registry.towerIds) // hai bảng độc lập, không lẫn nhau
    }

    @Test
    fun `enemy moi mac dinh o AIState SPAWN va chua co health hay resistances`() {
        val registry = EntityRegistry()
        val id = registry.createEnemy("ENEMY_CRAWLER", WorldPosition(0.0, 0.0), Movement(MovementType.GROUND), ThreatProfile(1))
        val enemy = registry.enemy(id)!!
        assertEquals(AIState.SPAWN, enemy.behavior.state)
        assertEquals(1, enemy.threatProfile.threatCost)
        assertNull(enemy.health)
        assertNull(enemy.resistances)
    }

    @Test
    fun `xoa enemy thi khong con truy van duoc`() {
        val registry = EntityRegistry()
        val id = registry.createEnemy("ENEMY_CRAWLER", WorldPosition(0.0, 0.0), Movement(MovementType.GROUND))
        assertEquals(true, registry.destroyEnemy(id))
        assertNull(registry.enemy(id))
        assertEquals(false, registry.destroyEnemy(id))
    }
}
