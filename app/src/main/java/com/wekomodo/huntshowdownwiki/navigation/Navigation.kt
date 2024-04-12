package com.wekomodo.huntshowdownwiki.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wekomodo.huntshowdownwiki.ui.screens.ItemScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.NEWS) {
        composable(route = Route.NEWS) {
            ItemScreen(onEvent = { navigate(it, navController) })
        }
        composable(
            route = Route.EQUIPMENT,
            arguments = listOf(navArgument("userId") { defaultValue = "" })
        ) {
           // SettingScreen(onEvent = { navigate(it, navController) })
        }

    }
}

fun navigate(event: Events, navController: NavHostController) {
    when (event) {
        Events.OnNavigateToNewsEvent -> navController.navigate(Route.NEWS)
        Events.OnNavigateToEquipmentEvent -> navController.navigate(Route.EQUIPMENT)
        Events.OnNavigateToTraitsEvent -> navController.navigate(Route.TRAITS)
        Events.OnNavigateToMapsEvent -> navController.navigate(Route.MAPS)
    }
}


object Route {
    const val NEWS = "News"
    const val EQUIPMENT = "Equipment"
    const val TRAITS = "Traits"
    const val MAPS = "Maps"
}
