package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        query = text,
        onQueryChange = onValueChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = {
            Text(text = "lookin for something?")
        },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = "Search") },
        trailingIcon = {
            if (active) {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onValueChange("")
                        onSearch("")
                    } else
                        onActiveChange(false)
                }) {
                    Icon(
                        imageVector = trailingIcon, contentDescription = "Trailing Icon"
                    )
                }
            }
        }
    ) {

    }
}