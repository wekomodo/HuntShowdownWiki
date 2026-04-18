package com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.weapons

data class Stats(
    val cycleTime: Double,
    val damage: Int,
    val dropRange: Int,
    val muzzleVelocity: Int,
    val rateOfFire: Int,
    val reloadSpeed: Double,
    val spread: Int,
    val sway: Int,
    val verticalRecoil: Int
)