package com.wekomodo.huntshowdownwiki

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.navigation.Navigation
import com.wekomodo.huntshowdownwiki.ui.components.NavBar
import com.wekomodo.huntshowdownwiki.ui.components.TopBar
import com.wekomodo.huntshowdownwiki.ui.theme.HuntShowdownWikiTheme
import com.wekomodo.huntshowdownwiki.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    private val viewModel: SteamNewsViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // appId is huntShowdown SteamApp id
        viewModel.getNews("594650", "5", "300", "json")
        lifecycleScope.launch {
            viewModel.steamNews.collect {
                if (it.status == Status.SUCCESS) {
                    Log.d("data",it.data.toString())
                   // Toast.makeText(this@ComposeActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        setContent {
            HuntShowdownWikiTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                // nav bar, scaffold
                Scaffold(
                    topBar = {
                             TopBar(title = R.string.app_name, Icons.Rounded.Menu){
                             }
                    },
                    bottomBar = { NavBar(navController) }
                ) {
                    Column(modifier = Modifier.padding(it)) {
                        Navigation(navController)
                        /*BackHandler(
                            enabled = true
                        ) {
                            // Navigate where ever you want.
                            //Example;
                            finish()
                        }*/
                    }
                }

            }
        }
    }

}