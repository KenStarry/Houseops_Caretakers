package com.example.houseopscaretakers.feature_splash_screen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.houseopscaretakers.R

@Composable
fun SplashImage() {

    Image(
        painter = painterResource(
            id = if (isSystemInDarkTheme()) {
                R.drawable.houseops_dark_final
            } else {
                R.drawable.houseops_light_final
            }
        ),
        contentDescription = "Houseops logo"
    )
}