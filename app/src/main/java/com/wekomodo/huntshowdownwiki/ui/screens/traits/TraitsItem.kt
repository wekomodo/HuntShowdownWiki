package com.wekomodo.huntshowdownwiki.ui.screens.traits

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R

@Composable
fun TraitsItem(link: String, name: String, desc: String, cost: Int, rank: Int) {

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
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(100.dp),
                        model = link,
                        contentDescription = ""
                    )
                    Column {
                        Text(text = name, color = Color.White, style = MaterialTheme.typography.titleLarge)
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = cost.toString(), color = Color(0xFFfff5dc))
                            Icon(
                                painterResource(id = R.drawable.ic_upgrade_points),
                                contentDescription = "upgrade_points",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(text = "Rank : $rank", color = Color.White)

                    }

                }
                Text(
                    modifier = Modifier.padding(start = 18.dp, bottom = 10.dp, end = 18.dp),
                    text = desc, color = Color.White, style = MaterialTheme.typography.bodySmall

                )
            }

        }
    }
}


@Preview
@Composable
fun TraitsItemPreview() {
    val link =
        "https://upload.wikimedia.org/wikipedia/en/thumb/1/14/WELNetworks-logo.svg/1200px-WELNetworks-logo.svg.png"
    TraitsItem(link, "Assiliant", "BLEH BLEH BLEH", 1, 42)
}