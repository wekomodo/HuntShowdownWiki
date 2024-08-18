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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.R
import java.util.Locale

@Composable
fun TraitsItem(link: String, name: String, desc: String, cost: Int, rank: Int, event_effect : String, pact : String) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
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
                    Text(
                        text = buildAnnotatedString {
                            append(name+"\n")
                            withStyle(
                                SpanStyle(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color.Yellow, Color.Red)
                                    )
                                )
                            ){
                                if(pact != "NONE")
                                    append(pact.lowercase(Locale.ROOT))
                            }
                        },
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = cost.toString(),
                            color = Color(0xFFfff5dc),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Icon(
                            painterResource(id = R.drawable.ic_upgrade_points),
                            contentDescription = "upgrade_points",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = "Rank : $rank",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
            Text(
                modifier = Modifier.padding(start = 18.dp, bottom = 10.dp, end = 18.dp),
                text = buildAnnotatedString {
                    append(desc+"\n")
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Yellow,Color.Red)
                            )
                        )
                    ) {
                        if(event_effect.isNotEmpty())
                            append(event_effect)
                    }

                },
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                )
        }

    }



}


@Preview
@Composable
fun TraitsItemPreview() {
    val link =
        "https://upload.wikimedia.org/wikipedia/en/thumb/1/14/WELNetworks-logo.svg/1200px-WELNetworks-logo.svg.png"
    TraitsItem(link, "Assiliant", "BLEH BLEH BLEH", 1, 42, " YEA STOOPID", " DEMENTED PACT")
}