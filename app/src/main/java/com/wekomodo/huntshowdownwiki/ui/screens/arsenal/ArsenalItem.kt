package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R


@Composable
fun ArsenalItem(name: String, image: String, desc: String) {
    val grayScaleMatrix = ColorMatrix(
        floatArrayOf(
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )
    Column(modifier = Modifier.padding(10.dp).clip(shape = RoundedCornerShape(10.dp))) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .paint(
                    painter = painterResource(id = R.drawable.items_bg),
                    contentScale = ContentScale.FillBounds
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = image,
                contentDescription = "",
                // colorFilter = ColorFilter.colorMatrix(grayScaleMatrix)
            )
            Text(
                modifier = Modifier.padding(10.dp),
                text = name,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

    }
}
/*   Text(
       modifier = Modifier.padding(start = 18.dp, bottom = 10.dp, end = 18.dp),
       text = desc, color = Color.White, style = MaterialTheme.typography.bodySmall,
       maxLines = 3

   )*/


@Preview
@Composable
fun ArsenalItemPreview() {
    ArsenalItem("Sparks", "", " BLEH BLEH BLEH")
}