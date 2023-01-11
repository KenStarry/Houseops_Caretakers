package com.example.houseopscaretakers.feature_houses.home_screen.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class StatsCardModel(
    val icon: ImageVector,
    val iconCol: Color,
    val value: String,
    val title: String
)
