package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class HouseFeatures(
    val featureName: String,
    val featureIcon: ImageVector,
    val featureSelected: Boolean
)
