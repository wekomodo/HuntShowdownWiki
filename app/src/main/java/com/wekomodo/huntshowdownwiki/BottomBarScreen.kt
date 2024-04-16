package com.wekomodo.huntshowdownwiki

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object News : BottomBarScreen(
        route = "NEWS",
        title = "NEWS",
        icon = R.drawable.ic_news
    )

    data object Arsenal : BottomBarScreen(
        route = "ARSENAL",
        title = "ARSENAL",
        icon = R.drawable.ic_equipment
    )

    data object Traits : BottomBarScreen(
        route = "TRAITS",
        title = "TRAITS",
        icon = R.drawable.ic_traits
    )
    data object Maps : BottomBarScreen(
        route = "MAPS",
        title = "MAPS",
        icon = R.drawable.ic_traits
    )
}