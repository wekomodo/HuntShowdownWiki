package com.wekomodo.huntshowdownwiki

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.navigation.Navigation
import com.wekomodo.huntshowdownwiki.navigation.navigate
import com.wekomodo.huntshowdownwiki.ui.components.NavBar
import com.wekomodo.huntshowdownwiki.ui.screens.ItemScreen
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
                // A surface container using the 'background' color from the theme
                Scaffold(bottomBar = { NavBar() }) { it ->
                    val padding = it
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HuntShowdownWikiTheme {
        Greeting("Android")
    }
}