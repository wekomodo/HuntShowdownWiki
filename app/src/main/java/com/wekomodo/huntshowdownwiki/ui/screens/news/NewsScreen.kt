package com.wekomodo.huntshowdownwiki.ui.screens.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wekomodo.huntshowdownwiki.data.model.steam.Newsitem
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.ui.components.ErrorUiState
import com.wekomodo.huntshowdownwiki.ui.components.LoadingUiState
import com.wekomodo.huntshowdownwiki.util.Status


@Composable
fun NewsScreen(
    viewModel: SteamNewsViewModel = hiltViewModel(),
) {
    var refresh = false
    val context = LocalContext.current
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    LaunchedEffect(Unit, refresh) {
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

       LoadingUiState(loading = loading)
        ErrorUiState(error = error){
            refresh=!refresh
        }
        LazyColumn {
            if(newsList.size>1)
            itemsIndexed(newsList) { _, item ->
                if(item.feedlabel == "Community Announcements")
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