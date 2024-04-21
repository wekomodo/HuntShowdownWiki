package com.wekomodo.huntshowdownwiki.data.model.firebase.traits

data class Trait(
    val category: String = "",
    val cost: Int = 0,
    val desc: String = "",
    val image: String = "",
    val name: String = "",
    val pact: String = "",
    val rank_unlocked: Int = 0,
    val stack_limit: Int = 0,
    val type: String = ""
)