package com.lastoutpost.game

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.battlefield.LaneEdge
import com.lastoutpost.core.battlefield.LaneNode
import com.lastoutpost.core.battlefield.MountType
import com.lastoutpost.core.battlefield.PathGraph
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.combat.AttackProfile
import com.lastoutpost.core.combat.DamageResolutionSystem
import com.lastoutpost.core.combat.HeatPowerWaterSystem
import com.lastoutpost.core.combat.TowerAttackSystem
import com.lastoutpost.core.entity.BaseCore
import com.lastoutpost.core.entity.DamageTag
import com.lastoutpost.core.entity.Health
import com.lastoutpost.core.enemy.EnemySystem
import com.lastoutpost.core.enemy.MovementSystem
import com.lastoutpost.core.mission.CoreAssaultProfile
import com.lastoutpost.core.mission.CoreAssaultSystem
import com.lastoutpost.core.mission.MissionObjectiveSystem
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.targeting.EnemyTargetingSystem
import com.lastoutpost.core.targeting.TowerTargetingSystem
import com.lastoutpost.core.tower.Ammo
import com.lastoutpost.core.tower.Heat
import com.lastoutpost.core.tower.ReferenceBaseCatalog
import com.lastoutpost.core.tower.TowerSystem
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import java.util.Locale

/**
 * Chạy một trận headless ngắn để chứng minh :core được nối vào app (không render, không gameplay thật).
 * Battlefield ở đây (1 buildable node, 1 lane entry → 1 Core trên Path Graph) là demo cục bộ dựng tay,
 * KHÔNG phải map/mission thật (map/mission thật sẽ được map designer author và nạp từ content — TD-17,
 * chưa có content pipeline cho map). Enemy dùng EMP Stalker vì đây là loài duy nhất trong roster demo có
 * preferredTargetClasses chứa TOWER (đủ để thấy targeting hai chiều hoạt động) — không phải lựa chọn thiết kế.
 * speed = 1.0/tick chỉ để demo di chuyển chạy được — KHÔNG phải số liệu canon (OQ-CONTENT-02).
 */
internal fun runHeadlessSmokeTest(): List<String> {
    val battlefield = Battlefield(
        nodes = listOf(BuildableNode("N_DEMO", WorldPosition(4.0, 4.0), MountType.GROUND)),
        paths = PathGraph(
            nodes = listOf(LaneNode("ENTRY_LANE_0", WorldPosition(0.0, 4.0)), LaneNode("CORE", WorldPosition(20.0, 4.0))),
            edges = listOf(LaneEdge("ENTRY_LANE_0", "CORE")),
        ),
        laneEntryNodes = mapOf(0 to "ENTRY_LANE_0"),
        coreNodeId = "CORE",
    )

    val roster = FixedRoster(setOf("ENEMY_EMP_STALKER"))
    val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 25, speciesId = "ENEMY_EMP_STALKER", count = 3, lane = 0)))
    // Danh mục barrel/module ở đây là demo cục bộ, KHÔNG phải content chính thức (chưa có roster — OQ-DESIGN-02).
    val towers = TowerSystem(battlefield, barrelCatalog = setOf("BARREL_DEMO"), moduleCatalog = setOf("MODULE_DEMO"))
    val enemies = EnemySystem(battlefield)
    val movement = MovementSystem(battlefield)
    val enemyTargeting = EnemyTargetingSystem()
    // range = 10.0 chỉ để demo chạy được — KHÔNG phải số liệu canon (Tower chưa có range thật, OQ-DESIGN-02).
    val towerTargeting = TowerTargetingSystem(range = 10.0, battlefield = battlefield)
    // baseDamage/cooldownTicks/ammoPerShot/shotHeat demo — KHÔNG phải số liệu canon (OQ-DESIGN-02). Enemy
    // chưa có Health thật (OQ-CONTENT-02) nên phát bắn sẽ ghi "damage_no_effect" — đúng hành vi thật ở
    // mission thật hiện nay.
    val attackProfiles = mapOf(
        "BARREL_DEMO" to AttackProfile(baseDamage = 10.0, cooldownTicks = 5, damageTag = DamageTag.KINETIC, ammoPerShot = 1, shotHeat = 40),
    )
    val attack = TowerAttackSystem(attackProfiles)
    val resolve = DamageResolutionSystem()
    val heatPowerWater = HeatPowerWaterSystem(coolingPerTick = mapOf("BARREL_DEMO" to 5))
    // Core 50 HP và 10 damage/đòn chỉ để demo — KHÔNG phải số liệu canon (OQ-CONTENT-02). Enemy tới Core đánh
    // MẤT DẦN HP (System Gameplay §17.2), Defeat khi Core Destroyed (§17.4).
    val coreAssault = CoreAssaultSystem(battlefield, mapOf("ENEMY_EMP_STALKER" to CoreAssaultProfile(damage = 10, cooldownTicks = 1)))
    val objective = MissionObjectiveSystem(director)
    val world = BattleWorld(
        missionId = "MISSION_TUTORIAL_001",
        contentVersion = "smoke",
        seed = 1L,
        systems = listOf(towers, director, enemies, movement, enemyTargeting, towerTargeting, attack, coreAssault, resolve, heatPowerWater, objective),
        core = BaseCore(Health(current = 50, max = 50)),
    )
    world.advanceTo(BattlePhase.LOAD_CONTENT)
    world.advanceTo(BattlePhase.BUILD_WORLD)
    world.advanceTo(BattlePhase.PREPARE)
    world.advanceTo(BattlePhase.COMBAT)

    world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_DEMO", "MODULE_DEMO", nodeId = "N_DEMO"))
    world.step() // xây tower trước, rồi mới gán Ammo/Heat demo (Tower mới luôn có cả hai = null — OQ-DESIGN-02)
    world.entities.towerIds.firstOrNull()?.let { towerId ->
        world.entities.updateTower(towerId) { it.copy(ammo = Ammo(current = 2, max = 10), heat = Heat(current = 0, max = 100)) }
    }

    // Spawn xảy ra ở tick 25 (SpawnGroup.atTick) — chạy tới đúng tick đó trước để gán speed demo cho enemy
    // vừa spawn (Movement.speed luôn null trong gameplay thật — OQ-CONTENT-02 — nên phải set thủ công ở đây).
    repeat(25) { world.step() }
    for (enemyId in world.entities.enemyIds) {
        world.entities.updateEnemy(enemyId) { it.copy(movement = it.movement.copy(speed = 1.0)) }
    }
    val startPosition = world.entities.enemyIds.firstOrNull()?.let { world.entities.enemy(it)?.position }

    val remainingTicks = world.config.ticksPerSecond * 2 - world.tick.toInt()
    repeat(remainingTicks) { world.step() }

    val spawnedEnemy = world.entities.enemyIds.firstOrNull()?.let { world.entities.enemy(it) }
    val builtTower = world.entities.towerIds.firstOrNull()?.let { world.entities.tower(it) }

    return listOf(
        "phase = ${world.phase}",
        "tick = ${world.tick} (${"%.1f".format(Locale.ROOT, world.elapsedSeconds)} giây mô phỏng)",
        "tower đã đặt = ${world.entities.towerIds.size} (đang nhắm enemy #${builtTower?.targeting?.currentTargetId}, rule=${builtTower?.targeting?.rule})",
        "enemy đã spawn = ${world.entities.enemyIds.size} (từ $startPosition di chuyển tới ${spawnedEnemy?.position}, node hiện tại=${spawnedEnemy?.movement?.currentNodeId})",
        "tower đã bắn = ${world.eventLog.count { it.type == "tower_fired" }} lần " +
            "(chưa có tác dụng vì enemy chưa có Health thật: ${world.eventLog.count { it.type == "damage_no_effect" }} lần — OQ-CONTENT-02)",
        "ammo còn lại = ${builtTower?.ammo?.current}/${builtTower?.ammo?.max}, heat = ${builtTower?.heat?.current}/${builtTower?.heat?.max} " +
            "(hết ammo ${world.eventLog.count { it.type == "tower_out_of_ammo" }} lần)",
        "core = ${world.core?.health?.current}/${world.core?.health?.max}, kết quả trận = ${world.resultState} (phase ${world.phase})",
        "event = ${world.eventLog.joinToString { it.type }}",
    )
}
