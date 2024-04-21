package com.wekomodo.huntshowdownwiki.data.model.firebase.items

data class Item(
    val capacity: String = "",
    val cost: Int = 0,
    val desc: String = "",
    val effect_stats: EffectStats = EffectStats(),
    val image_url: String = "",
    val melee_stats: MeleeStats = MeleeStats(),
    val name: String = "",
    val sub_type: String = "",
    val type: String = "",
    val weapon_stats: WeaponStats = WeaponStats()
)