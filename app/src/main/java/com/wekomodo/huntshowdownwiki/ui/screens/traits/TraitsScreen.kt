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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
    val traitsList = remember {
        mutableStateListOf(Trait())
    }
    var filteredList by remember {
        mutableStateOf(traitsList.toList())
    }
    val activeFilters = remember { mutableSetOf<String>() }

    val result = viewModel.response.collectAsState()
    when (result.value) {
        is Resource.Error -> Log.d("Error", "Error")
        is Resource.Loading -> Log.d("Loading", "Loading")
        is Resource.Success -> result.value.data?.let { it.forEach{trait ->
            traitsList.add(trait)
            Log.d("TRAITLIST",trait.toString())
        }}
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
                    filteredList =
                        if (activeFilters.isEmpty()) traitsList.toList() else traitsList.toList()
                            .filter { trait ->
                                trait.type in activeFilters
                            }

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