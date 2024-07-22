package com.wekomodo.huntshowdownwiki.data.model.firebase.items.tools

import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.MeleeStats
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.common.Stats

data class Tools(
    val capacity: String,
    val cost: Int,
    val desc: String,
    val effect_stats: Any,
    val image_url: String,
    val melee_stats: MeleeStats,
    val name: String,
    val stats: Stats,
    val type: List<String>
)