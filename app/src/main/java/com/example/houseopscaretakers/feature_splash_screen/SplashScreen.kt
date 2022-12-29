package com.example.houseopscaretakers.feature_splash_screen

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_splash_screen.presentation.components.SplashImage

@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SplashImage()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = Constants.SPLASH_TITLE,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

    }
}


























