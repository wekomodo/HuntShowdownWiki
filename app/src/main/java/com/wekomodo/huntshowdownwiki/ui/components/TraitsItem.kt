package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
fun TraitsItem(link: String, name: String, desc: String,cost : Int) {

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.TopStart
        ) {
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
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(110.dp),
                        model = link,
                        contentDescription = ""
                    )
                    Column {
                        Text(text = name, color = Color.White, fontSize = 24.sp)
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = cost.toString(),color = Color(0xFFfff5dc))
                            Icon(
                                painterResource(id = R.drawable.ic_upgrade_points),
                                contentDescription = "upgrade_points",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 20.dp),
                ) {
                    Text(
                        text = desc, color = Color.White, fontSize = 10.sp,
                        maxLines = 3
                    )
                }
            }

        }
    }
}


@Preview
@Composable
fun TraitsItemPreview() {
    val link =
        "https://upload.wikimedia.org/wikipedia/en/thumb/1/14/WELNetworks-logo.svg/1200px-WELNetworks-logo.svg.png"
    TraitsItem(link, "Assiliant", "BLEH BLEH BLEH",1)
}