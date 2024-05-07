package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R


@Composable
fun ArsenalItem(name: String, image: String,desc : String) {
    val grayScaleMatrix = ColorMatrix(
        floatArrayOf(
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.items_bg),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(100.dp),
                        model = image,
                        contentDescription = "",
                       // colorFilter = ColorFilter.colorMatrix(grayScaleMatrix)
                    )
                    Column {
                        Text(text = name, color = Color.White, fontSize = 24.sp)
                    }

                }
                Text(
                    modifier = Modifier.padding(start = 12.dp, bottom = 10.dp, end = 12.dp),
                    text = desc, color = Color.White, fontSize = 10.sp,
                    maxLines = 3

                )
            }

        }
    }

}


@Preview
@Composable
fun ArsenalItemPreview() {
    ArsenalItem("Sparks", ""," BLEH BLEH BLEH")
}