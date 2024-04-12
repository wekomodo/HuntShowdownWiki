package com.wekomodo.huntshowdownwiki.navigation

sealed class Events {
    data object OnNavigateToNewsEvent : Events()
    data object OnNavigateToEquipmentEvent : Events()
    data object OnNavigateToTraitsEvent : Events()
    data object OnNavigateToMapsEvent : Events()
}