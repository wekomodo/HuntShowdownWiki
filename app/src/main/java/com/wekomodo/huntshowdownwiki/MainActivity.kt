package com.wekomodo.huntshowdownwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.wekomodo.huntshowdownwiki.domain.steam.SteamNewsViewModel
import com.wekomodo.huntshowdownwiki.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: SteamNewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getNews("594650", "5", "300", "json")
        lifecycleScope.launch {
            viewModel.steamNews.collect {
                if (it.status == Status.SUCCESS) {
                    Log.d("data",it.data.toString())
                    Toast.makeText(this@MainActivity, it.data.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}