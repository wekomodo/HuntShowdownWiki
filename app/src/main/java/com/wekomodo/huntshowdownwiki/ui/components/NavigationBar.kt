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


@Composable
fun NavBar() {
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
                onClick = { selectedItem = index }
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    NavBar()
}