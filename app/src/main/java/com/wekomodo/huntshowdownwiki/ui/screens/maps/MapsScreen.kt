package com.wekomodo.huntshowdownwiki.ui.screens.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.data.model.local.ItemMap
import com.wekomodo.huntshowdownwiki.ui.components.MapsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(navController: NavController) {
    val maps = listOf(ItemMap("Lawson Delta", R.drawable.lawson_delta_cover),
        ItemMap("StillWater Bayou",R.drawable.still_water_bayou_cover),
        ItemMap("DeSalle",R.drawable.desalle_cover)
    )
    var selectedItem by remember {
        mutableStateOf("")
    }
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
            MapDetailScreen(mapName = selectedItem)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            itemsIndexed(maps) { _, item ->
                MapsItem(image = item.img, name = item.name){
                    selectedItem = item.name
                    showBottomSheet = true
                   /* val mapName = Uri.encode(item.name)
                    val route = "${Route.MAPDETAILS}/mapName=${mapName}"
                    navController.navigate(route)*/
                }
            }
        }

    }
}



@Preview
@Composable
fun MapsScreenPreview(){
    val navController = rememberNavController()
    MapsScreen(navController)
}