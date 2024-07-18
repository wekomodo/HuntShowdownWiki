package com.wekomodo.huntshowdownwiki.data.model.firebase.weapons

data class AmmoType(
    val capacity: String = "",
    val cost: Int = 0,
    val damage: Int = 0,
    val effective_range: Int =0,
    val muzzle_velocity: Int = 0,
    val name: String = "",
    val sighted_range: Double = 0.00,
    val spread: Double = 0.00,
    val vertical_recoil: Int = 0
)