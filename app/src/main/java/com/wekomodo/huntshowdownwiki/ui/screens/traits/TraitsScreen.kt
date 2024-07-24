package com.wekomodo.huntshowdownwiki.ui.screens.traits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wekomodo.huntshowdownwiki.domain.firebase.FirebaseViewModel
import com.wekomodo.huntshowdownwiki.ui.components.ErrorUiState
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.ui.components.LoadingUiState
import com.wekomodo.huntshowdownwiki.util.Resource
import com.wekomodo.huntshowdownwiki.util.Status


private var filteringCriteria = setOf("Base Trait", "Burn Trait", "Event Trait")

@Composable
fun TraitsScreen(
    viewModel: FirebaseViewModel = viewModel()
) {
    var uiState by remember { mutableStateOf(TraitsUiState()) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    val result by viewModel.traits.collectAsStateWithLifecycle(
        initialValue = Resource.Loading(
            null
        )
    )

    // just to react when the results come in
    LaunchedEffect(result) {
        if (result is Resource.Success) {
            val data = result.data?.toList() ?: emptyList()
            uiState = uiState.copy(
                traitList = data,
                displayedList = data
            )
        }
        when (result.status) {
            Status.SUCCESS -> {
                loading = false
                error = false
                val data = result.data?.toList() ?: emptyList()
                uiState = uiState.copy(
                    traitList = data,
                    displayedList = data
                )
            }
            Status.LOADING -> {
                loading = true
                error = false
            }

            Status.ERROR -> {
                error = true
                loading = false
            }
        }
    }

    // reacts when changes to filters are made
    LaunchedEffect(uiState.activeFilters) {
        uiState = if(uiState.activeFilters.isNotEmpty()) {
            // normal filtering
            uiState.copy(
                displayedList = uiState.traitList.filter { it.type in uiState.activeFilters }
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
            filteringCriteria.forEach { filter ->
                val isSelected = filter in uiState.activeFilters
                FilterChipComp(enabled = isSelected, name = filter) {
                    val tempSet = uiState.activeFilters.toMutableSet()
                    if(it) tempSet.add(filter) else tempSet.remove(filter)
                    uiState = uiState.copy(
                        activeFilters = tempSet // will be cast to Set<>
                    )
                }
            }
        }
        Column(Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LoadingUiState(loading = loading)
            ErrorUiState(error = error)
            LazyColumn {

                itemsIndexed(uiState.displayedList) { _, item ->
                    TraitsItem(
                        link = item.image,
                        name = item.name,
                        item.desc,
                        item.cost,
                        item.rank_unlocked
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun TraitsScreenPreview() {
    TraitsScreen()
}