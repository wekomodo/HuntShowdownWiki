package com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.weapons

import com.wekomodo.huntshowdownwiki.data.model.firebase.items.subItems.common.MeleeStats

data class Weapon(
    val ammo: List<Ammo>,
    val ammoType: String,
    val capacity: Capacity,
    val cost: Int,
    val description: String,
    val family: Family,
    val id: String,
    val isVariant: Boolean,
    val meleeStats: MeleeStats,
    val name: String,
    val pageTitle: String,
    val slot: String,
    val stats: Stats,
    val traits: List<String>,
    val variant: Any
)