package com.wekomodo.huntshowdownwiki.ui.screens.traits

import androidx.compose.runtime.Immutable
import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait
import java.lang.Error

@Immutable
data class TraitsUiState(
    val traitList: List<Trait> = emptyList(), // cache
    val displayedList: List<Trait> = emptyList(),
    val activeFilters: Set<String> = emptySet(),
    val loading : Boolean = false,
    val error: Boolean = false,
)
