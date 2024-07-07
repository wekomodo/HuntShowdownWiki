package com.wekomodo.huntshowdownwiki.ui.screens.news

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.data.model.steam.Newsitem
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.ui.components.AnimatedPreloader
import com.wekomodo.huntshowdownwiki.util.Status


@Composable
fun NewsScreen(
    viewModel: SteamNewsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.getNews("594650", "10", "500", "json")
    }
    val result = viewModel.steamNews.collectAsStateWithLifecycle()
    val newsList = remember {
        mutableListOf(Newsitem())
    }
    when (result.value.status) {
        Status.SUCCESS -> {
            loading = false
            error = false
            result.value.data?.let {
                newsList.clear()
                newsList.addAll(it.appnews.newsitems)
            }
        }
        Status.LOADING -> {
            loading = true
            error = false
        }

        Status.ERROR -> {
            error = true
            loading = false
           // Toast.makeText(context, "Some error occurred", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = loading,
            exit = scaleOut()
        )
         {
             CircularProgressIndicator(
                 modifier = Modifier.width(64.dp),
                 color = MaterialTheme.colorScheme.secondary,
                 trackColor = MaterialTheme.colorScheme.surfaceVariant,
             )
         }
        AnimatedVisibility(visible = error,
            exit = scaleOut()
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Mr Frog has some wisdom for you", style = MaterialTheme.typography.titleLarge)
                AnimatedPreloader(rawRes = R.raw.frog_bones, Modifier.size(150.dp))
                Text(text = "Check your internet connection?")
            }

        }
        LazyColumn {
            if(newsList.size>1)
            itemsIndexed(newsList) { _, item ->
                NewsItemUi(
                    item
                ) {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                              item.url
                            )
                        )
                    )
                 //   uriHandler.openUri(item.url)
                }
            }
        }

    }
}