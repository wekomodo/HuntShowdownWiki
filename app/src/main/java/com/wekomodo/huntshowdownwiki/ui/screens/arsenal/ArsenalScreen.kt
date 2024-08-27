package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
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
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.consumables.Consumables
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.tools.Tools
import com.wekomodo.huntshowdownwiki.data.model.firebase.items.weapons.Weapons
import com.wekomodo.huntshowdownwiki.ui.components.ErrorUiState
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.ui.components.LoadingUiState
import com.wekomodo.huntshowdownwiki.ui.components.SearchComponent


private var filteringCriteria = setOf("weapon", "tools", "consumable")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArsenalScreen(navUiState: ArsenalUiState, refresh : ()-> Unit) {
    /*    val arsenal by viewModel.arsenal.collectAsStateWithLifecycle(
            initialValue = Resource.Loading(
                null
            )
        )*/
    var searchText by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }
    var uiState by remember { mutableStateOf(navUiState) }
    var selectedWeapon by remember { mutableStateOf<Weapons?>(null) }
    var selectedTool by remember { mutableStateOf<Tools?>(null) }
    var selectedConsumable by remember { mutableStateOf<Consumables?>(null) }
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
            selectedWeapon?.let {
                ArsenalDetailScreen(item = it)
            }
            selectedTool?.let {
                ArsenalDetailScreen(item = it)
            }
            selectedConsumable?.let {
                ArsenalDetailScreen(item = it)
            }

        }
    }
    // reacts when changes to filters are made
    LaunchedEffect(uiState.activeFilters) {
        uiState = if (uiState.activeFilters.isNotEmpty()) {
            // normal filtering
            uiState.copy(
                displayedList = uiState.cacheList.filter {
                    if (it.type.isNotEmpty())
                        it.type[0] in uiState.activeFilters
                    else
                        false
                }
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
            if(searchText.isEmpty())
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
                    displayedList = uiState.cacheList.filter {
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
        Spacer(modifier = Modifier.size(16.dp))
        LoadingUiState(uiState.loading)
        ErrorUiState(uiState.error, refresh)
        LazyColumn {
            itemsIndexed(uiState.displayedList) { _, item ->
                ArsenalItem(
                    name = item.name,
                    image = item.image_url,
                    desc = item.desc,
                    cost = item.cost
                ) {
                    selectedWeapon = if (item.type.contains("weapon"))
                        uiState.weaponsList.find { it.name == item.name }
                    else
                        null
                    selectedTool = if (item.type.contains("tools"))
                        uiState.toolsList.find { it.name == item.name }
                    else
                        null
                    selectedConsumable = if (item.type.contains("consumable"))
                        uiState.consumablesList.find { it.name == item.name }
                    else
                        null
                    showBottomSheet = true
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemScreenPreview() {
    ArsenalScreen(ArsenalUiState()) {

    }
}