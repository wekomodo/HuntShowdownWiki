package com.wekomodo.huntshowdownwiki.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wekomodo.huntshowdownwiki.ui.screens.ArsenalScreen
import com.wekomodo.huntshowdownwiki.ui.screens.NewsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.TraitsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.maps.MapDetailScreen
import com.wekomodo.huntshowdownwiki.ui.screens.maps.MapsScreen

@Composable
fun Navigation(navController : NavHostController) {

    NavHost(navController = navController, startDestination = Route.NEWS) {
        composable(route = Route.NEWS) {
            NewsScreen()
        }
        composable(
            route = Route.ARSENAL
        ) {
            ArsenalScreen()
        }
        composable(route = Route.TRAITS) {
            TraitsScreen()
        }
        composable(route = Route.MAPS) {
            MapsScreen(navController)
        }
        composable(route = "${Route.MAPDETAILS}/mapName={mapName}",
            arguments = listOf(navArgument("mapName") { defaultValue = "Lawson Delta" })) {
            val arguments = requireNotNull(it.arguments)
            val mapName = arguments.getString("mapName") ?: error("Missing mapName argument")
            MapDetailScreen(mapName = mapName)
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
    const val NEWS = "NEWS"
    const val ARSENAL = "ARSENAL"
    const val TRAITS = "TRAITS"
    const val MAPS = "MAPS"
    const val MAPDETAILS = "MAPDETAILS"
}
