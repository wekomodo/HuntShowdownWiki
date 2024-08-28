package com.wekomodo.huntshowdownwiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.wekomodo.huntshowdownwiki.navigation.Navigation
import com.wekomodo.huntshowdownwiki.ui.components.NavBar
import com.wekomodo.huntshowdownwiki.ui.components.TopBar
import com.wekomodo.huntshowdownwiki.ui.theme.HuntShowdownWikiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //testing ads
       /* val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.edit().putBoolean("ADS_ENABLED",false).commit()*/
        MobileAds.initialize(this) {}
        setContent {
            HuntShowdownWikiTheme {
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                // nav bar, scaffold
                Scaffold(
                    topBar = { TopBar(title = R.string.app_name, Icons.Rounded.Menu, scrollBehavior = scrollBehavior){}
                    },
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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