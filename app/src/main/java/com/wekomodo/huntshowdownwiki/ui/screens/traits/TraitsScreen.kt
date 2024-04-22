package com.wekomodo.huntshowdownwiki.ui.screens.traits

import android.util.Log
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wekomodo.huntshowdownwiki.data.model.firebase.traits.Trait
import com.wekomodo.huntshowdownwiki.domain.firebase.viewmodel.FirebaseViewModel
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.util.Resource


private var filteringCriteria = setOf("Base Trait", "Burn Trait", "Event Trait")

@Composable
fun TraitsScreen(
    viewModel: FirebaseViewModel = viewModel()
) {
    //  val context = LocalContext.current
    var traitsList = emptyList<Trait>()
    val result by viewModel.response.collectAsStateWithLifecycle(
        initialValue = Resource.Loading(
            null
        )
    )
    val filteredList = remember {
        mutableStateListOf<Trait>()
    }
    val activeFilters = remember { mutableSetOf<String>() }

    // val result = viewModel.response.collectAsStateWithLifecycle()
    LaunchedEffect(result, activeFilters) {
        if (result is Resource.Success) {
            traitsList = result.data ?: emptyList()
            filteredList.clear()
            filteredList.addAll(traitsList)
            Log.d("ListTrait", traitsList.toString())
            Log.d("ListFiltered", filteredList.toList().toString())
        }
        /*   val newList =
               if (activeFilters.isEmpty()) traitsList else traitsList
                   .filter { trait ->
                       trait.type in activeFilters
                   }
           filteredList.clear()
           filteredList.addAll(newList)*/
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            filteringCriteria.forEach { filter ->
                val isSelected = filter in activeFilters
                FilterChipComp(enabled = isSelected, name = filter) {
                    if (it) {
                        activeFilters.add(filter)
                    } else {
                        activeFilters.remove(filter)
                    }
                    val newList =
                        if (activeFilters.isEmpty()) traitsList else traitsList.filter { it.type in activeFilters }
                    filteredList.clear()
                    filteredList.addAll(newList)
                    Log.d("FilterActive", activeFilters.toString())
                }

            }
        }
        LazyColumn {
            itemsIndexed(filteredList) { _, item ->
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