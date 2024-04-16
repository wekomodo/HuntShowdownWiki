package com.wekomodo.huntshowdownwiki.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.data.model.local.ItemMap
import com.wekomodo.huntshowdownwiki.ui.components.MapsItem

@Composable
fun MapsScreen() {
    val maps = listOf(ItemMap("Lawson Delta", R.drawable.lawson_delta_cover),
        ItemMap("Stillwater Bayou",R.drawable.still_water_bayou_cover),
        ItemMap("DeSalle",R.drawable.desalle_cover)
    )

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

                }
            }
        }

    }
}



@Preview
@Composable
fun MapsScreenPreview(){
    MapsScreen()
}