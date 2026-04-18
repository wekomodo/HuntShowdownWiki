package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.wekomodo.huntshowdownwiki.ui.components.CallNativeAd
import com.wekomodo.huntshowdownwiki.ui.components.ErrorUiState
import com.wekomodo.huntshowdownwiki.ui.components.FilterChipComp
import com.wekomodo.huntshowdownwiki.ui.components.LoadSimpleAd
import com.wekomodo.huntshowdownwiki.ui.components.LoadingUiState
import com.wekomodo.huntshowdownwiki.ui.components.SearchComponent


private var filteringCriteria = setOf("weapon", "tools", "consumable")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArsenalScreen(navUiState: ArsenalUiState, refresh : ()-> Unit) {

}

@Preview
@Composable
fun ItemScreenPreview() {
    ArsenalScreen(ArsenalUiState()) {

    }
}