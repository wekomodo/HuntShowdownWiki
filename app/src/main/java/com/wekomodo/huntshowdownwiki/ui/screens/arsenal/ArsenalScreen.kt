package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons
import com.wekomodo.huntshowdownwiki.domain.firebase.FirebaseViewModel
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.ui.components.LoadingUiState
import com.wekomodo.huntshowdownwiki.util.Resource
import com.wekomodo.huntshowdownwiki.util.Status


private var filteringCriteria = setOf("weapon", "tools", "consumable")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArsenalScreen(viewModel: FirebaseViewModel = viewModel()) {
    val arsenal by viewModel.arsenal.collectAsStateWithLifecycle(
        initialValue = Resource.Loading(
            null
        )
    )
    var error by remember { mutableStateOf(false) }
    var uiState by remember { mutableStateOf(ArsenalUiState()) }
    var loading by remember { mutableStateOf(true) }
    var selectedItem by remember { mutableStateOf(Weapons()) }
    val sheetState = rememberModalBottomSheetState(true)
    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            // Sheet content
            ArsenalDetailScreen(item = selectedItem)
        }
    }
    //var filteredList: List<Item> = emptyList()
    LaunchedEffect(arsenal) {
        if (arsenal is Resource.Success) {
            val data = arsenal.data
            data?.let {
                uiState = uiState.copy(
                    displayedList = data.arsenal,
                    cacheList = data.arsenal,
                    weaponsList = data.weapons,
                    toolsList = data.tools,
                    consumablesList = data.consumables
                )

            }

        }
        when (arsenal.status) {
            Status.SUCCESS -> {
                loading = false
                error = false
                val data = arsenal.data
                data?.let {
                    uiState = uiState.copy(
                        displayedList = data.arsenal,
                        cacheList = data.arsenal,
                        weaponsList = data.weapons,
                        toolsList = data.tools,
                        consumablesList = data.consumables
                    )

                }
            }

            Status.LOADING -> {
                loading = true
                error = false
                Log.d("ARSENAL", "LOADING")
            }

            Status.ERROR -> {
                error = true
                loading = false
                Log.d("ARSENAL", "FAILED")
            }
        }
    }
    // reacts when changes to filters are made
    LaunchedEffect(uiState.activeFilters) {
        uiState = if (uiState.activeFilters.isNotEmpty()) {
            // normal filtering
            uiState.copy(
                displayedList = uiState.cacheList.filter {
                    if(it.type.isNotEmpty())
                    it.type[0] in uiState.activeFilters
                   else
                     false}
            )
        } else {
            // will come in handy if somebody selects a filter and unselects it after
            uiState.copy(
                displayedList = uiState.cacheList
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
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
        LoadingUiState(loading = loading)
        LazyColumn {
            itemsIndexed(uiState.displayedList) { _, item ->
                ArsenalItem(
                    name = item.name,
                    image = item.image_url,
                    desc = item.desc,
                    cost = item.cost
                ) {
                    selectedItem = uiState.weaponsList.find {
                        it.name == item.name
                    }!!
                    showBottomSheet = true
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemScreenPreview() {
    ArsenalScreen()
}