package com.example.houseopscaretakers.core.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.example.houseopscaretakers.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*

@Composable
fun LottieLoader(
    modifier: Modifier = Modifier,
    isPlaying: Boolean
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading1)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = true
    )

    LottieAnimation(
        composition = composition,
        progress = progress
    )

//    val context = LocalContext.current
//
//    val animationSpec = remember {
//        LottieAnimationSpec.RawRes(R.raw.loading1)
//    }
////    val composition = LottieCompositionFactory.fromAssetSync(context, "loading1.json")
//    val animationState = rememberLottieAnimationState(
//        autoPlay = true,
//        repeatCount = Integer.MAX_VALUE
//    )
//
//    Box(
//        modifier = modifier
//    ) {
//
//        LottieAnimation(
//            spec = animationSpec,
//            animationState = animationState
//        )
//
//    }
}























