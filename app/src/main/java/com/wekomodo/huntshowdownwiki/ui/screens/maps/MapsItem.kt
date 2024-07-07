package com.wekomodo.huntshowdownwiki.ui.screens.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wekomodo.huntshowdownwiki.R

@Composable
fun MapsItem(image: Int, name: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {

        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.hunt_logo_black), contentDescription = "",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Text(text = name, style = MaterialTheme.typography.displaySmall)

        }
        Image(
            painter = painterResource(id = image),
            contentScale = ContentScale.Inside,
            contentDescription = name,
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }

}


@Preview
@Composable
fun MapsItemPreview() {
    MapsItem(R.drawable.lawson_delta_cover, "Lawson Delta") {

    }
}