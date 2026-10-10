package com.lastoutpost.core

import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.combat.AttackProfile
import com.lastoutpost.core.combat.DamageResolutionSystem
import com.lastoutpost.core.combat.HeatPowerWaterSystem
import com.lastoutpost.core.combat.TowerAttackSystem
import com.lastoutpost.core.entity.DamageTag
import com.lastoutpost.core.entity.Health
import com.lastoutpost.core.enemy.Movement
import com.lastoutpost.core.enemy.MovementType
import com.lastoutpost.core.enemy.Resistances
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.tower.Ammo
import com.lastoutpost.core.tower.BarrelState
import com.lastoutpost.core.tower.BaseState
import com.lastoutpost.core.tower.Heat
import com.lastoutpost.core.tower.ModuleState
import com.lastoutpost.core.tower.Targeting
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Test cô lập cho TowerAttackSystem + DamageResolutionSystem + HeatPowerWaterSystem: dựng thẳng entity qua
 * EntityRegistry và khoá target thủ công (bỏ qua TowerSystem/EnemySystem/WaveDirector/Target Acquisition —
 * đã có test riêng ở TowerSystemTest/EnemySystemTest/TargetingRuleTest), để kiểm soát chính xác
 * Health/Ammo/Heat/cooldown trước mỗi bước.
 */
class CombatSystemTest {
    private val profiles = mapOf("BARREL_TEST" to AttackProfile(baseDamage = 10.0, cooldownTicks = 3, damageTag = DamageTag.KINETIC))

    private fun setup(
        attackProfiles: Map<String, AttackProfile> = profiles,
        coolingPerTick: Map<String, Int> = emptyMap(),
    ): Triple<BattleWorld, Long, Long> {
        val attack = TowerAttackSystem(attackProfiles)
        val resolve = DamageResolutionSystem()
        val heatPowerWater = HeatPowerWaterSystem(coolingPerTick)
        val world = BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = listOf(attack, resolve, heatPowerWater)).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }
        val towerId = world.entities.createTower(
            WorldPosition(0.0, 0.0), BaseState("BASE_TEST"), BarrelState("BARREL_TEST"), ModuleState("MODULE_TEST"),
        )
        val enemyId = world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(1.0, 0.0), Movement(MovementType.GROUND))
        world.entities.updateTower(towerId) { it.copy(targeting = Targeting(currentTargetId = enemyId)) }
        return Triple(world, towerId, enemyId)
    }

    @Test
    fun `tower ban ngay tick dau khi het cooldown va co target khoa san`() {
        val (world, towerId, enemyId) = setup()
        world.step()
        assertTrue(world.eventLog.any { it.type == "tower_fired" && "tower=$towerId" in it.detail && "target=$enemyId" in it.detail })
    }

    @Test
    fun `enemy chua co Health thi ban khong co tac dung, khong crash`() {
        val (world, _, enemyId) = setup()
        world.step()
        assertNull(world.entities.enemy(enemyId)?.health)
        assertTrue(world.eventLog.any { it.type == "damage_no_effect" })
    }

    @Test
    fun `enemy co Health thi bi tru dung so damage`() {
        val (world, _, enemyId) = setup()
        world.entities.updateEnemy(enemyId) { it.copy(health = Health(current = 50, max = 50)) }
        world.step()
        assertEquals(40, world.entities.enemy(enemyId)!!.health!!.current) // 50 - 10 (baseDamage, không armor/resistance)
    }

    @Test
    fun `enemy chet thi bi xoa khoi registry va phat enemy_died`() {
        val (world, _, enemyId) = setup()
        world.entities.updateEnemy(enemyId) { it.copy(health = Health(current = 5, max = 50)) }
        world.step()
        assertEquals(null, world.entities.enemy(enemyId))
        assertTrue(world.eventLog.any { it.type == "enemy_died" && "target=$enemyId" in it.detail })
    }

    @Test
    fun `cooldown duoc tuan thu - khong ban lai cho den khi het cooldown`() {
        val (world, _, enemyId) = setup()
        world.entities.updateEnemy(enemyId) { it.copy(health = Health(current = 1000, max = 1000)) }

        world.step() // tick 1: cooldown=0 → bắn, reset cooldown=3
        assertEquals(1, world.eventLog.count { it.type == "tower_fired" })

        world.step() // tick 2: cooldown 3→2, chưa bắn
        world.step() // tick 3: cooldown 2→1, chưa bắn
        assertEquals(1, world.eventLog.count { it.type == "tower_fired" })

        world.step() // tick 4: cooldown 1→0, chưa bắn (chỉ decrement)
        world.step() // tick 5: cooldown=0 → bắn lại
        assertEquals(2, world.eventLog.count { it.type == "tower_fired" })
    }

    @Test
    fun `barrel khong co attack profile thi khong ban`() {
        val (world, _, _) = setup(attackProfiles = emptyMap())
        world.step()
        assertTrue(world.eventLog.none { it.type == "tower_fired" })
    }

    @Test
    fun `resistance lam giam damage dung he so`() {
        val (world, _, enemyId) = setup()
        world.entities.updateEnemy(enemyId) {
            it.copy(health = Health(100, 100), resistances = Resistances(mapOf(DamageTag.KINETIC to 0.5)))
        }
        world.step()
        assertEquals(95, world.entities.enemy(enemyId)!!.health!!.current) // 10 * 0.5 = 5 damage
    }

    @Test
    fun `armor giam damage nhung khong bao gio duoi 1`() {
        val (world, _, enemyId) = setup()
        world.entities.updateEnemy(enemyId) { it.copy(health = Health(100, 100), armor = 1000.0) }
        world.step()
        assertEquals(99, world.entities.enemy(enemyId)!!.health!!.current) // sàn 1 damage dù armor rất cao
    }

    @Test
    fun `target da mat truoc khi resolve thi bo qua khong loi`() {
        val (world, towerId, enemyId) = setup()
        world.entities.updateEnemy(enemyId) { it.copy(health = Health(1, 1)) }
        world.step() // enemy chết + bị xoá trong cùng tick (ATTACK_PROJECTILE rồi DAMAGE_DEATH)
        assertEquals(null, world.entities.enemy(enemyId))

        // tower vẫn còn targeting id cũ (Target Acquisition không chạy ở test cô lập này) — attack tick kế
        // tiếp phải bỏ qua êm, không crash, vì target đã không còn tồn tại.
        world.step()
        world.step()
        world.step()
        world.step() // đủ hết cooldown để thử bắn lại vào id đã mất
        assertTrue(world.entities.tower(towerId) != null) // hệ thống không crash
    }

    @Test
    fun `tower khong co ammo heat thi khong bi gioi han - mac dinh null`() {
        val (world, towerId, _) = setup()
        world.step()
        assertNull(world.entities.tower(towerId)!!.ammo)
        assertNull(world.entities.tower(towerId)!!.heat)
        assertTrue(world.eventLog.any { it.type == "tower_fired" })
    }

    @Test
    fun `het ammo thi khong ban, phat tower_out_of_ammo`() {
        val profilesWithAmmo = mapOf("BARREL_TEST" to AttackProfile(10.0, 3, DamageTag.KINETIC, ammoPerShot = 1))
        val (world, towerId, _) = setup(attackProfiles = profilesWithAmmo)
        world.entities.updateTower(towerId) { it.copy(ammo = Ammo(current = 0, max = 10)) }
        world.step()
        assertTrue(world.eventLog.any { it.type == "tower_out_of_ammo" })
        assertTrue(world.eventLog.none { it.type == "tower_fired" })
    }

    @Test
    fun `ban thi tru dung ammoPerShot, khong vuot duoi 0`() {
        val profilesWithAmmo = mapOf("BARREL_TEST" to AttackProfile(10.0, 3, DamageTag.KINETIC, ammoPerShot = 2))
        val (world, towerId, _) = setup(attackProfiles = profilesWithAmmo)
        world.entities.updateTower(towerId) { it.copy(ammo = Ammo(current = 3, max = 10)) }
        world.step()
        assertEquals(1, world.entities.tower(towerId)!!.ammo!!.current) // 3 - 2 = 1
    }

    @Test
    fun `dung 100 phan tram heat Jam thi khong ban, phat tower_heat_locked`() {
        val profilesWithHeat = mapOf("BARREL_TEST" to AttackProfile(10.0, 3, DamageTag.KINETIC, shotHeat = 20))
        val (world, towerId, _) = setup(attackProfiles = profilesWithHeat)
        world.entities.updateTower(towerId) { it.copy(heat = Heat(current = 100, max = 100)) }
        world.step()
        assertTrue(world.eventLog.any { it.type == "tower_heat_locked" })
        assertTrue(world.eventLog.none { it.type == "tower_fired" })
    }

    @Test
    fun `ban thi cong dung shotHeat, khong vuot qua max`() {
        val profilesWithHeat = mapOf("BARREL_TEST" to AttackProfile(10.0, 3, DamageTag.KINETIC, shotHeat = 30))
        val (world, towerId, _) = setup(attackProfiles = profilesWithHeat)
        world.entities.updateTower(towerId) { it.copy(heat = Heat(current = 85, max = 100)) }
        world.step()
        assertEquals(100, world.entities.tower(towerId)!!.heat!!.current) // 85 + 30 = 115, trần ở max=100
    }

    @Test
    fun `cooling giam heat moi tick qua HeatPowerWaterSystem`() {
        val (world, towerId, _) = setup(coolingPerTick = mapOf("BARREL_TEST" to 5))
        world.entities.updateTower(towerId) { it.copy(heat = Heat(current = 50, max = 100)) }
        world.step()
        assertEquals(45, world.entities.tower(towerId)!!.heat!!.current)
        world.step()
        assertEquals(40, world.entities.tower(towerId)!!.heat!!.current)
    }

    @Test
    fun `cooling khong lam heat am`() {
        val (world, towerId, _) = setup(coolingPerTick = mapOf("BARREL_TEST" to 100))
        world.entities.updateTower(towerId) { it.copy(heat = Heat(current = 5, max = 100)) }
        world.step()
        assertEquals(0, world.entities.tower(towerId)!!.heat!!.current)
    }

    @Test
    fun `jam roi cooling ha nhiet duoi max thi ban lai duoc`() {
        val profilesWithHeat = mapOf("BARREL_TEST" to AttackProfile(10.0, 3, DamageTag.KINETIC, shotHeat = 0))
        val (world, towerId, _) = setup(attackProfiles = profilesWithHeat, coolingPerTick = mapOf("BARREL_TEST" to 100))
        world.entities.updateTower(towerId) { it.copy(heat = Heat(current = 100, max = 100)) } // Jam ngay từ đầu
        world.step() // ATTACK_PROJECTILE: heat=100 → khoá, không bắn; HEAT_POWER_WATER (sau): cooling 100 → heat về 0
        assertTrue(world.eventLog.any { it.type == "tower_heat_locked" })
        assertEquals(0, world.entities.tower(towerId)!!.heat!!.current)

        world.step() // tick kế: heat=0 (<100) → không khoá nữa, cooldown cũng đã sẵn 0 từ đầu → bắn được
        assertTrue(world.eventLog.any { it.type == "tower_fired" })
    }
}
