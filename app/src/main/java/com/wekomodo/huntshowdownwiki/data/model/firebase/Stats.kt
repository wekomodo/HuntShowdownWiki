package com.wekomodo.huntshowdownwiki.data.model.firebase

data class Stats(
    val damage: Int,
    val effect_duration: Int,
    val effect_radius: Int,
    val fuse_duration: Int,
    val heavy_melee: Int,
    val melee: Int,
    val sighted_range: Double,
    val stamina_used_heavy: Int,
    val stamina_used_light: Int,
    val stamina_used_throw: Int,
    val throw_range: Int,
    val tick_damage: Int
)