package com.wekomodo.huntshowdownwiki.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wekomodo.huntshowdownwiki.navigation.Events
import com.wekomodo.huntshowdownwiki.ui.components.NavBar


@Composable
fun Dashboard(onEvent : (Events) -> Unit){

    Scaffold(bottomBar = { NavBar(onEvent) }, modifier = Modifier.fillMaxSize()) { it->
        val padding = it
        NewsScreen()
    }
}



@Preview
@Composable
fun DashboardPreview(){
    Dashboard {

    }
}