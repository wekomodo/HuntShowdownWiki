package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterChipComp(
    enabled : Boolean,
    name : String,
    isEnabled : (Boolean) -> Unit

){
    var isSelected by remember {
        mutableStateOf(enabled)
    }
    FilterChip(
        onClick = { isSelected = !isSelected
                  isEnabled(isSelected)},
        label = {
            Text(text = name)
        },
        selected = isSelected,
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}


@Preview
@Composable
fun FilterChipCompPreview(){
    FilterChipComp(false,"Traits"){

    }
}