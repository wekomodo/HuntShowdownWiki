package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import androidx.compose.runtime.Immutable
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.Arsenal
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables.Consumables
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.tools.Tools
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons
import com.wekomodo.huntshowdownwiki.util.Resource

@Immutable
data class ArsenalUiState(
    val weaponsList: List<Weapons> = emptyList(),
    val toolsList: List<Tools> = emptyList(),
    val consumablesList : List<Consumables> = emptyList(),
    val displayedList  : List<Arsenal> = emptyList(),
    val cacheList  : List<Arsenal> = emptyList(),// cache
    val activeFilters: Set<String> = emptySet(),
    val error : Boolean = false,
    val loading : Boolean = false
)
