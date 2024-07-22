package com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables

data class Stats(
    val control_range: Int,
    val damage: Int,
    val duration: Int,
    val fuse_timer: Int,
    val heavy_melee: Int,
    val melee: Int,
    val radius: Int,
    val throw_range: Int
)