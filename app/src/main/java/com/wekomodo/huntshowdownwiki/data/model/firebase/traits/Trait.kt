package com.wekomodo.huntshowdownwiki.data.model.firebase.traits

data class Trait(
    val bigImageFile: String,
    val category: String,
    val conditionalEffects: List<ConditionalEffect>?,
    val cost: Int,
    val description: String,
    val id: String,
    val name: String,
    val pageTitle: String,
    val section: String,
    val sectionKey: String,
    val smallImageFile: String,
    val stackLimit: Int,
    val tags: List<String>,
    val type: String,
    val typeParts: List<String>,
    val unlock: Int
)