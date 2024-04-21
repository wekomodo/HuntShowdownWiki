package com.wekomodo.huntshowdownwiki.data.model.firebase.items

data class WeaponStats(
    val ammo_types: List<String> = emptyList(),
    val cycle_time: Double = 0.0,
    val damage: Int = 0,
    val effective_range: Int = 0,
    val muzzle_velocity: Int = 0,
    val primary_ammo: String = "",
    val reload_speed: Double = 0.0,
    val rpm: Int = 0,
    val secondary_ammo: String = "",
    val sighted_range: Int = 0,
    val spread: Double = 0.0,
    val sway: Int = 0,
    val vertical_recoil: Int = 0
)