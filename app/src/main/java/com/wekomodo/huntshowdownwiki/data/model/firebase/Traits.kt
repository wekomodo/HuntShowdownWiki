package com.wekomodo.huntshowdownwiki.data.model.firebase

data class Traits(
    val base_traits: ArrayList<BaseTrait> = ArrayList<BaseTrait>(),
    val burn_traits: ArrayList<BurnTrait> = ArrayList<BurnTrait>(),
    val event_traits: ArrayList<EventTrait> = ArrayList<EventTrait>()
)