package com.wekomodo.huntshowdownwiki.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    @StringRes
    title: Int,
    icon : ImageVector,
    scrollBehavior: TopAppBarScrollBehavior,
    onClick: () -> Unit,
)  {
    //val context = LocalContext.current
    Column {
        CenterAlignedTopAppBar(
            /*navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Menu Icon"
                    )
                }
            },*/
            scrollBehavior = scrollBehavior,
            title = {
                Text(
                    stringResource(id = title),
                    fontSize = 24.sp
                )
            }
        )
    }
}