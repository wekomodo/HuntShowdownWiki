package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wekomodo.huntshowdownwiki.R

@Composable
fun LoadingUiState( loading : Boolean){
    AnimatedVisibility(visible = loading,
        exit = scaleOut()
    )
    {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }

}

@Composable
fun ErrorUiState(error : Boolean, refresh: ()-> Unit){
    AnimatedVisibility(visible = error,
        exit = scaleOut()
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Mr Frog has some wisdom for you", style = MaterialTheme.typography.titleLarge)
            AnimatedPreloader(rawRes = R.raw.frog_bones, Modifier.size(150.dp))
            Text(text = "Check your internet connection?")
            Button(onClick = refresh) {
                Text(text = stringResource(id = R.string.refresh))
            }
        }

    }

}

@Preview
@Composable
fun LoadingUiStatePreview(){
    LoadingUiState(loading = true)
}

@Preview
@Composable
fun ErrorUiStatePreview(){
    ErrorUiState(error = true)
    {

    }}
