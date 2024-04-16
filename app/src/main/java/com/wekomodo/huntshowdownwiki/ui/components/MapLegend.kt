package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wekomodo.huntshowdownwiki.R


@Composable
fun MapLegendButton(
    checked : Boolean,
    icon : Int,
    name : String,
    onSwitchChanged : (Boolean) -> Unit
) {
    val modifier = Modifier.padding(4.dp)
    Column {
        Row( modifier = modifier,
             verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = icon),
                tint= Color.Unspecified,
                contentDescription = "spawn location switch"
            )
            Text(modifier = modifier,text = name)
            Switch(
                checked = checked,
                onCheckedChange = onSwitchChanged
            )
        }

    }

}

@Composable
fun MapLegend(
    icon : Int,
    name : String,
){
    val modifier = Modifier.padding(4.dp)
    Column {
        Row( modifier = modifier,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = icon),
                tint= Color.Unspecified,
                contentDescription = "spawn location switch"
            )
            Text(modifier = modifier,text = name)
        }

    }
}


@Composable
@Preview
fun MapLegendPreview() {
    Column {
        MapLegendButton(true,R.drawable.ic_spawn_location,"Spawn Locations",{})
        MapLegend(R.drawable.ic_spawn_location,"Spawn Locations")
    }
}