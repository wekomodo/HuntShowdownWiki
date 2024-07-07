package com.wekomodo.huntshowdownwiki.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.wekomodo.huntshowdownwiki.R

@Composable
fun AnimatedPreloader(rawRes : Int,modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            rawRes
        )
    )
    val progress by animateLottieCompositionAsState(composition)


    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}


@Preview
@Composable
fun AnimationPreloaderPreview(){
    AnimatedPreloader(R.raw.frog_bones, Modifier.size(100.dp))
}