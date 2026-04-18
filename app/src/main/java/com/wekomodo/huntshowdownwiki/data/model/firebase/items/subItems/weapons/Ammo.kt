package com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.weapons

data class Ammo(
    val changes: List<Change>,
    val cost: Int,
    val costType: String,
    val iconFile: String,
    val id: String,
    val name: String,
    val notes: List<String>,
    val priceText: String
)