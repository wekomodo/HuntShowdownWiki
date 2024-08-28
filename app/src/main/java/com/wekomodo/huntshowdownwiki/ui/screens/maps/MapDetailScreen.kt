package com.wekomodo.huntshowdownwiki.ui.screens.maps

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.ui.components.MapLegend
import com.wekomodo.huntshowdownwiki.ui.components.MapLegendButton
import com.wekomodo.huntshowdownwiki.ui.components.ZoomableBox
import com.wekomodo.huntshowdownwiki.ui.components.ZoomableImage

@Composable
fun MapDetailScreen(
    mapName: String
) {
    val context = LocalContext.current
    var mapImage: Int = 0
    var locationsImage: Int = 0
    var spawnsImage: Int = 0
    var towersImage: Int = 0
    var beetleImage: Int = 0
    var legend = true
    when (mapName) {
        "Lawson Delta" -> {
            mapImage = R.drawable.lawson_delta
            locationsImage = R.drawable.lawson_delta_locations
            spawnsImage = R.drawable.lawson_delta_spawn_locations
            towersImage = R.drawable.lawson_delta_towers
            beetleImage = R.drawable.lawson_delta_beetle
            legend = true
        }

        "StillWater Bayou" -> {
            mapImage = R.drawable.still_water_bayou
            locationsImage = R.drawable.still_water_bayou_locations
            spawnsImage = R.drawable.still_water_bayou_spawn_locations
            towersImage = R.drawable.still_water_bayou_towers
            beetleImage = R.drawable.still_water_bayou_beetle
            legend = true
        }

        "DeSalle" -> {
            mapImage = R.drawable.desalle
            locationsImage = R.drawable.desalle_locations
            spawnsImage = R.drawable.desalle_spawn_locations
            towersImage = R.drawable.desalle_towers
            beetleImage = R.drawable.desalle_beetle
            legend = true
        }

        "Mammon's Gulch" -> {
            mapImage = R.drawable.mammons_gulch
            legend = false
        }


        else -> {
            Toast.makeText(context, "some error occurred", Toast.LENGTH_SHORT).show()
            return
        }

    }
    var names by rememberSaveable {
        mutableStateOf(true)
    }
    var beetle by rememberSaveable {
        mutableStateOf(true)
    }
    var spawnLocation by rememberSaveable {
        mutableStateOf(true)
    }
    var towers by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = mapName, style = MaterialTheme.typography.titleLarge)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card {
                Row(Modifier.padding(8.dp)) {
                    Icon(imageVector = Icons.Rounded.Info, contentDescription = "tip Icon")
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "pinch to zoom in & out on map"
                    )
                }
            }


        }
        Card(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(

            ) {
                ZoomableBox(
                ) {
                    ZoomableImage(
                        image = mapImage, scale = scale, offsetX = offsetX, offsetY = offsetY
                    )
                    if (legend) {
                        /*ZoomableImage(
                            image = when {
                                names -> locationsImage
                                spawnLocation -> spawnsImage
                                towers -> towersImage
                                beetle -> beetleImage
                                else -> R.drawable.app_icon
                            }, scale = scale, offsetX = offsetX, offsetY = offsetY
                        )*/
                        if (names) ZoomableImage(
                            image = locationsImage,
                            scale = scale,
                            offsetX = offsetX,
                            offsetY = offsetY
                        )
                        if (spawnLocation) ZoomableImage(
                            image = spawnsImage, scale = scale, offsetX = offsetX, offsetY = offsetY
                        )
                        if (towers) ZoomableImage(
                            image = towersImage, scale = scale, offsetX = offsetX, offsetY = offsetY
                        )
                        if (beetle) ZoomableImage(
                            image = beetleImage, scale = scale, offsetX = offsetX, offsetY = offsetY
                        )
                    }

                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            if (legend) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Map Legend",
                    style = MaterialTheme.typography.titleLarge
                )
                MapLegendButton(names, R.drawable.ic_location_names, "Locations") { names = it }
                MapLegendButton(
                    spawnLocation, R.drawable.ic_spawn_location, "Spawn Locations"
                ) { spawnLocation = it }
                MapLegendButton(towers, R.drawable.ic_towers, "Tower Locations") { towers = it }
                MapLegendButton(beetle, R.drawable.ic_beetle, "Beetle Locations") { beetle = it }
                MapLegend(icon = R.drawable.ic_resupply, name = "Resupply")
            } else Text(
                modifier = Modifier.padding(8.dp),
                text = "Still in progress!",
                style = MaterialTheme.typography.titleLarge
            )
        }


    }


}


@Preview
@Composable
fun MapDetailScreenPreview() {
    MapDetailScreen(
        "Lawson Delta"
    )
}