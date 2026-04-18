package com.wekomodo.huntshowdownwiki.data.model.firebase.items



enum class ArsenalKind {
    WEAPON,
    TOOL,
    CONSUMABLE
}

data class ArsenalItem(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val description: String = "",
    val cost: Int? = null,
    val imageKey: String = "",
    val kind: ArsenalKind = ArsenalKind.WEAPON,
    val tags: List<String> = emptyList()
)