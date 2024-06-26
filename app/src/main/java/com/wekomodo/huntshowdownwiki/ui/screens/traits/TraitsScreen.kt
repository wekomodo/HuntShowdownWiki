package com.wekomodo.huntshowdownwiki.ui.screens.traits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wekomodo.huntshowdownwiki.domain.firebase.viewmodel.FirebaseViewModel
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.util.Resource


private var filteringCriteria = setOf("Base Trait", "Burn Trait", "Event Trait")

@Composable
fun TraitsScreen(
    viewModel: FirebaseViewModel = viewModel()
) {
    //  val context = LocalContext.current
    var uiState by remember { mutableStateOf(TraitsUiState()) }
//    var traitsList = emptyList<Trait>()
    val result by viewModel.response.collectAsStateWithLifecycle(
        initialValue = Resource.Loading(
            null
        )
    )
//    val filteredList = remember {
//        mutableStateListOf<Trait>()
//    }
//    val activeFilters = remember { mutableSetOf<String>() }

    // val result = viewModel.response.collectAsStateWithLifecycle()

    // just to react when the results come in
    LaunchedEffect(result) {
        if (result is Resource.Success) {
            val data = result.data?.toList() ?: emptyList()
            uiState = uiState.copy(
                traitList = data,
                displayedList = data
            )
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

//    LaunchedEffect(result, activeFilters) {
//        if (result is Resource.Success) {
//            traitsList = result.data ?: emptyList()
//            filteredList.clear()
//            filteredList.addAll(traitsList)
//            Log.d("ListTrait", traitsList.toString())
//            Log.d("ListFiltered", filteredList.toList().toString())
//        }
//        /*   val newList =
//               if (activeFilters.isEmpty()) traitsList else traitsList
//                   .filter { trait ->
//                       trait.type in activeFilters
//                   }
//           filteredList.clear()
//           filteredList.addAll(newList)*/
//    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
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
        Text("Nothing Selected?")
    }
}

@Preview
@Composable
fun TraitsScreenPreview() {
    TraitsScreen()
}