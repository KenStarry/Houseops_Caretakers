package com.example.houseopscaretakers.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.houseopscaretakers.R
import androidx.compose.runtime.*
import com.airbnb.lottie.compose.*

@Composable
fun MyLottie(
    modifier: Modifier = Modifier,
    lottieRaw: Int,
    isPlaying: Boolean = false,
    restartOnPlay: Boolean = false,
    iterations: Int = 1
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieRaw)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations,
        isPlaying = isPlaying,
        restartOnPlay = restartOnPlay
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}























