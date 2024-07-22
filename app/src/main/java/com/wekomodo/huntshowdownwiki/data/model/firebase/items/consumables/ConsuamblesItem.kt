package com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables

data class ConsuamblesItem(
    val cost: Int,
    val desc: String,
    val description: String,
    val image_url: String,
    val item_name: String,
    val name: String,
    val stats: Stats
)