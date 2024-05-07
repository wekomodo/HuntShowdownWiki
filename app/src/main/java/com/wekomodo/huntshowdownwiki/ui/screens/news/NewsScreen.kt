package com.wekomodo.huntshowdownwiki.ui.screens.news

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wekomodo.huntshowdownwiki.data.model.steam.Newsitem
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.util.Status

@Composable
fun NewsScreen(
    viewModel: SteamNewsViewModel = hiltViewModel()
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    var loading by remember { mutableStateOf(false) }
    viewModel.getNews("594650", "10", "500", "json")
    val result = viewModel.steamNews.collectAsStateWithLifecycle()
    val newsList = remember {
        mutableListOf(Newsitem())
    }

    when (result.value.status) {
        Status.SUCCESS -> {
            loading = false
            result.value.data?.let {
                newsList.clear()
                newsList.addAll(it.appnews.newsitems)
            }
        }
        Status.LOADING -> {
            loading = true
        }

        Status.ERROR -> {
            Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /* if(loading)
         {
             CircularProgressIndicator(
                 modifier = Modifier.width(64.dp),
                 color = MaterialTheme.colorScheme.secondary,
                 trackColor = MaterialTheme.colorScheme.surfaceVariant,
             )
         }*/
        LazyColumn {
            itemsIndexed(newsList) { _, item ->
                NewsItemUi(
                    item
                ) {
                    uriHandler.openUri(item.url)
                }
            }
        }

    }
}