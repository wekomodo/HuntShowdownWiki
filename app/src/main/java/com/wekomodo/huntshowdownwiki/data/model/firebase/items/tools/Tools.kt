package com.wekomodo.huntshowdownwiki.data.model.firebase.items.tools

import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.MeleeStats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.Stats

data class Tools(
    val capacity: String = "",
    val cost: Int = 0,
    val desc: String = "",
    val image_url: String = "",
    val melee_stats: MeleeStats = MeleeStats(),
    val name: String = "",
    val stats: Stats = Stats(),
    val type: List<String> = emptyList()
)