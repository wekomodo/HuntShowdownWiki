package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wekomodo.huntshowdownwiki.R
import com.wekomodo.huntshowdownwiki.navigation.Events
import com.wekomodo.huntshowdownwiki.navigation.Route


@Composable
fun NavBar(onEvent : (Events) -> Unit) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val icons =
        listOf(R.drawable.ic_news, R.drawable.ic_equipment, R.drawable.ic_traits, R.drawable.ic_map)
    val items = listOf("News", "Equipment", "Traits", "Maps")
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        modifier = Modifier.size(24.dp),
                        contentDescription = item
                    )
                },
               /*  // to add custom shapes
               modifier = Modifier.background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(10.dp)
                ),
                */
                alwaysShowLabel = false,
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    when(items[index]){
                        Route.NEWS ->  onEvent(Events.OnNavigateToNewsEvent)
                        Route.ARSENAL ->  onEvent(Events.OnNavigateToArsenalEvent)
                        Route.TRAITS ->  onEvent(Events.OnNavigateToTraitsEvent)
                        Route.MAPS ->  onEvent(Events.OnNavigateToMapsEvent)

                    }
                    selectedItem = index
                }
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    NavBar(onEvent = {})
}