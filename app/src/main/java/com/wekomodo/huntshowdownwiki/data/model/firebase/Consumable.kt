package com.wekomodo.huntshowdownwiki.data.model.firebase

data class Consumable(
    val capacity: String,
    val category: Category,
    val cost: Int,
    val desc: String,
    val image_url: String,
    val name: String,
    val stats: Stats,
    val weapon_stats: WeaponStats
)