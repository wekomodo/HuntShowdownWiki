package com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables

import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.MeleeStats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.Stats

data class Consumables(
    val cost: Int = 0,
    val desc: String = "",
    val image_url: String = "",
    val name: String = "",
    val melee_stats : MeleeStats? = null,
    val stats: Stats? = null,
    val type : List<String> = emptyList()
)