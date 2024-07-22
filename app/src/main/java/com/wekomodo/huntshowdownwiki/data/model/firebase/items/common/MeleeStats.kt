package com.wekomodo.huntshowdownwiki.data.model.firebase.items.common

data class MeleeStats(
    val heavy_melee: Int = 0,
    val melee: Int = 0,
    val stamina_used_heavy: Int? = null,
    val stamina_used_light: Int? = null,
    val stamina_used_throw: Int? = null,
    val throw_range: Int? = null
)