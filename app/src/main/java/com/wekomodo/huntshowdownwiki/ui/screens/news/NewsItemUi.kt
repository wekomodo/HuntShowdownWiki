package com.wekomodo.huntshowdownwiki.ui.screens.news

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wekomodo.huntshowdownwiki.data.model.steam.Newsitem

@Composable
fun NewsItemUi(newsitem: Newsitem, onclick: () -> Unit) {
    val defaultSteamImagesUrl = "https://clan.akamai.steamstatic.com/images/"
    val imageUrl = defaultSteamImagesUrl + newsitem.contents.substringAfter("/")
        .substringBefore(".jpg") + ".jpg"
    Log.d("url", imageUrl)
    Row(
        modifier = Modifier.clickable { onclick() },
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.height(120.dp).clip(RoundedCornerShape(10.dp)),
                model = imageUrl,
                contentDescription = "newsImage",
            )
            Text(modifier = Modifier.padding(10.dp), text = newsitem.title)
        }

    }

}


@Preview
@Composable
fun NewsItemUiPreview() {
    NewsItemUi(newsitem = Newsitem()) {}
}