package com.wekomodo.huntshowdownwiki.ui.screens.arsenal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R


@Composable
fun ArsenalItem(name: String, image: String) {

    Column(modifier = Modifier.fillMaxWidth().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.items_bg),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                AsyncImage(modifier = Modifier.height(190.dp),model = image, contentDescription = "Item Image", contentScale = ContentScale.FillBounds)
            }
            Column(
                modifier = Modifier.fillMaxWidth().height(200.dp).padding(25.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = name, fontSize = 24.sp, color = Color.White)
            }

        }

    }

}


@Preview
@Composable
fun ArsenalItemPreview() {
    ArsenalItem("Sparks", "")
}