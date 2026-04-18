package com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.consumables

import com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.common.MeleeStats

data class Consumable(
    val bigImageFile: String?,
    val cost: Any,
    val costLabel: String,
    val costType: String,
    val description: String,
    val id: String,
    val imageFile: String,
    val meleeStats: MeleeStats,
    val name: String,
    val pageTitle: String,
    val section: String,
    val sectionList: List<String>,
    val stats: Stats,
    val tags: List<String>,
    val unlock: Any
)