package com.wekomodo.huntshowdownwiki

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.navigation.Navigation
import com.wekomodo.huntshowdownwiki.navigation.navigate
import com.wekomodo.huntshowdownwiki.ui.components.NavBar
import com.wekomodo.huntshowdownwiki.ui.theme.HuntShowdownWikiTheme
import com.wekomodo.huntshowdownwiki.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    private val viewModel: SteamNewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNews("594650", "5", "300", "json")
        lifecycleScope.launch {
            viewModel.steamNews.collect {
                if (it.status == Status.SUCCESS) {
                    Log.d("data",it.data.toString())
                    Toast.makeText(this@ComposeActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        setContent {
            HuntShowdownWikiTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                // nav bar, scaffold
                Scaffold(
                    bottomBar = { NavBar(onEvent = {navigate(it, navController)}) }
                ) {
                    Column(modifier = Modifier.padding(it)) {
                        Navigation(navController)
                    }
                    
                }
            }
        }
    }
}