package com.wekomodo.huntshowdownwiki.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wekomodo.huntshowdownwiki.domain.firebase.FirebaseViewModel
import com.wekomodo.huntshowdownwiki.ui.screens.arsenal.ArsenalScreen
import com.wekomodo.huntshowdownwiki.ui.screens.arsenal.ArsenalUiState
import com.wekomodo.huntshowdownwiki.ui.screens.maps.MapDetailScreen
import com.wekomodo.huntshowdownwiki.ui.screens.maps.MapsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.news.NewsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.traits.TraitsScreen
import com.wekomodo.huntshowdownwiki.ui.screens.traits.TraitsUiState
import com.wekomodo.huntshowdownwiki.util.Resource
import com.wekomodo.huntshowdownwiki.util.Status

var refresh = false

@Composable
fun Navigation(navController: NavHostController, viewModel: FirebaseViewModel = viewModel()) {
    val arsenalUiState = readArsenalData(viewModel)
    val traitsUiState = readTraitsData(viewModel)
    NavHost(navController = navController, startDestination = Route.NEWS) {
        composable(route = Route.NEWS) {
            NewsScreen()
        }
        composable(
            route = Route.ARSENAL
        ) {
            ArsenalScreen(arsenalUiState) {
                refresh=!refresh
            }
        }
        composable(route = Route.TRAITS) {
            TraitsScreen(traitsUiState) {
                refresh=!refresh
            }
        }
        composable(route = Route.MAPS) {
            MapsScreen(navController)
        }
        composable(route = "${Route.MAPDETAILS}/mapName={mapName}",
            arguments = listOf(navArgument("mapName") { defaultValue = "Lawson Delta" })
        ) {
            val arguments = requireNotNull(it.arguments)
            val mapName = arguments.getString("mapName") ?: error("Missing mapName argument")
            MapDetailScreen(mapName = mapName)
        }

    }
}

@Composable
fun readTraitsData(viewModel: FirebaseViewModel): TraitsUiState {
    var uiState by remember {
        mutableStateOf(TraitsUiState())
    }
    val traits by viewModel.traits.collectAsStateWithLifecycle(
        initialValue = Resource.Loading(
            null
        )
    )
    LaunchedEffect(traits, refresh) {
        when (traits.status) {
            Status.SUCCESS -> {
                val data = traits.data?.toList() ?: emptyList()
                uiState = uiState.copy(
                    traitList = data,
                    displayedList = data,
                    loading = false,
                    error = false
                )
            }

            Status.LOADING -> {
                uiState = uiState.copy(
                    loading = true,
                    error = false
                )
            }

            Status.ERROR -> {
                uiState = uiState.copy(
                    loading = false,
                    error = true
                )
            }
        }
    }
    return uiState
}

@Composable
fun readArsenalData(viewModel: FirebaseViewModel): ArsenalUiState {
    var uiState by remember {
        mutableStateOf(ArsenalUiState())
    }
    val arsenal by viewModel.arsenal.collectAsStateWithLifecycle(
        initialValue = Resource.Loading(
            null
        )
    )
    LaunchedEffect(arsenal,refresh) {
        when (arsenal.status) {
            Status.LOADING -> {
                uiState = uiState.copy(
                    loading = true,
                    error = false
                )
            }

            Status.ERROR -> {
                uiState = uiState.copy(
                    loading = false,
                    error = true
                )
            }

            Status.SUCCESS -> {
                val data = arsenal.data
                data?.let {
                    uiState = uiState.copy(
                        displayedList = data.arsenal,
                        cacheList = data.arsenal,
                        weaponsList = data.weapons,
                        toolsList = data.tools,
                        consumablesList = data.consumables,
                        error = false,
                        loading = false
                    )
                }
            }
        }
    }
    return uiState
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
