package com.wekomodo.huntshowdownwiki.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wekomodo.huntshowdownwiki.ui.screens.ArsenalScreen
import com.wekomodo.huntshowdownwiki.ui.screens.Dashboard
import com.wekomodo.huntshowdownwiki.ui.screens.MapsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.NewsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.TraitsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.DASHBOARD) {
        composable(route = Route.DASHBOARD){
            Dashboard(onEvent = { navigate(it, navController) })
        }
        composable(route = Route.NEWS) {
            NewsScreen()
        }
        composable(
            route = Route.ARSENAL,
            arguments = listOf(navArgument("userId") { defaultValue = "" })
        ) {
            ArsenalScreen()
           // SettingScreen(onEvent = { navigate(it, navController) })
        }
        composable(route = Route.TRAITS) {
            TraitsScreen()
        }
        composable(route = Route.MAPS) {
            MapsScreen()
        }

    }
}

fun navigate(event: Events, navController: NavHostController) {
    when (event) {
        Events.OnNavigateToNewsEvent -> navController.navigate(Route.NEWS)
        Events.OnNavigateToArsenalEvent -> navController.navigate(Route.ARSENAL)
        Events.OnNavigateToTraitsEvent -> navController.navigate(Route.TRAITS)
        Events.OnNavigateToMapsEvent -> navController.navigate(Route.MAPS)
    }
}


object Route {
    const val DASHBOARD = "DASHBOARD"
    const val NEWS = "News"
    const val ARSENAL = "Arsenal"
    const val TRAITS = "Traits"
    const val MAPS = "Maps"
}
