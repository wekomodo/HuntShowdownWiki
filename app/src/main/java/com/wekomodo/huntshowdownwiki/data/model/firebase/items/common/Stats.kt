package com.wekomodo.huntshowdownwiki.data.model.firebase.items.common

data class Stats(
    val control_range: Int? = null,
    val cycle_time: Double? = null,
    val damage: Int = 0,
    val duration: Int? = null,
    val effective_range: String? = null,
    val fuse_duration: Int? = null,
    val muzzle_velocity: Int? = null,
    val radius: Int? = null,
    val reload_speed: Double? = null,
    val rpm: Int = 0,
    val sighted_range: Int? = null,
    val spread: Double? = null,
    val sway: Int? = null,
    val vertical_recoil: Double? = null
)