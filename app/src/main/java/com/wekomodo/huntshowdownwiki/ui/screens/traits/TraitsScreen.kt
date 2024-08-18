package com.wekomodo.huntshowdownwiki.ui.screens.traits

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wekomodo.huntshowdownwiki.ui.components.ErrorUiState
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.ui.components.LoadingUiState
import com.wekomodo.huntshowdownwiki.ui.components.SearchComponent


private var filteringCriteria = setOf("Base Trait", "Scarce Trait", "Event Trait")

@Composable
fun TraitsScreen(
    navUiState: TraitsUiState
) {
    var TAG = "TRAITS SCREEN"
    var uiState by remember { mutableStateOf(navUiState) }
    var searchText by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }
    Log.d(TAG,uiState.activeFilters.toString())
    // reacts when changes to filters are made
    LaunchedEffect(uiState.activeFilters) {
        uiState = if (uiState.activeFilters.isNotEmpty()) {
            // normal filtering
            uiState.copy(
                displayedList = uiState.traitList.filter {
                    it.type.contains(uiState.activeFilters.first(), ignoreCase = true)
                }
            )
        } else {
            // will come in handy if somebody selects a filter and unselects it after
            uiState.copy(
                displayedList = uiState.traitList
            )
        }
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            if (searchText.isEmpty())
                filteringCriteria.forEach { filter ->
                    val isSelected = filter in uiState.activeFilters
                    FilterChipComp(enabled = isSelected, name = filter) {
                        val tempSet = uiState.activeFilters.toMutableSet()
                        if (it) tempSet.add(filter) else tempSet.remove(filter)
                        uiState = uiState.copy(
                            activeFilters = tempSet // will be cast to Set<>
                        )
                    }
                }
        }
        SearchComponent(
            text = searchText,
            onValueChange = {
                searchText = it
            },
            onSearch = {
                uiState = uiState.copy(
                    displayedList = uiState.traitList.filter {
                        it.name.contains(searchText, ignoreCase = true)
                    }
                )
                searchActive = false
            },
            active = searchActive,
            onActiveChange = {
                searchActive = it
            },
            leadingIcon = Icons.Rounded.Search,
            trailingIcon = Icons.Rounded.Close
        )
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingUiState(uiState.loading)
            ErrorUiState(uiState.error)
            LazyColumn {
                itemsIndexed(uiState.displayedList) { _, item ->
                    TraitsItem(
                        link = item.image,
                        name = item.name,
                        item.desc,
                        item.cost,
                        item.rank_unlocked,
                        event_effect = item.event_effect,
                        item.pact
                    )
                }
            }
        }

    }
}

fun List<String>.contains(s: String, ignoreCase: Boolean = true): Boolean {
    return any { it.equals(s, ignoreCase) }
}

@Preview
@Composable
fun TraitsScreenPreview() {
    TraitsScreen(TraitsUiState())
}