package com.wekomodo.huntshowdownwiki.data.model.firebase

data class Category(
    val fire: Boolean,
    val healing: Boolean,
    val light: Boolean,
    val melee: Boolean,
    val noise: Boolean,
    val pistol: Boolean,
    val placeable: Boolean,
    val throwable: Boolean
)