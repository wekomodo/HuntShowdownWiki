package com.wekomodo.huntshowdownwiki.data.model.firebase

data class WeaponStats(
    val cycle_time: Double,
    val damage: Int,
    val effective_range: Int,
    val heavy_melee: Int,
    val melee: Int,
    val muzzle_velocity: Int,
    val reload_speed: Double,
    val rpm: Int,
    val spread: Double,
    val sway: Int,
    val vertical_recoil: Int
)