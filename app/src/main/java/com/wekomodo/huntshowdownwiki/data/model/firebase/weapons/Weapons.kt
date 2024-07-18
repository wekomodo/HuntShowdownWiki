package com.wekomodo.huntshowdownwiki.data.model.firebase.weapons

data class Weapons(
    val ammo_types: ArrayList<AmmoType>? = null,
    val capacity: String = "",
    val cost: Int = 0,
    val desc: String = "",
    val image_url: String = "",
    val melee_stats: MeleeStats? = null,
    val name: String = "",
    val stats: Stats? = null,
    val type: List<String> = emptyList()
)