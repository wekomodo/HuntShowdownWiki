package com.wekomodo.huntshowdownwiki.data.model.firebase.weapons

data class Stats(
    val cycle_time: Double? = null,
    val damage: Int? = null,
    val effective_range: String? = null,
    val muzzle_velocity: Int? = null,
    val reload_speed: Double? = null,
    val rpm: Int = 0,
    val sighted_range: Int? = null,
    val spread: Double? = null,
    val sway: Int? = null,
    val vertical_recoil: Double? = null
)