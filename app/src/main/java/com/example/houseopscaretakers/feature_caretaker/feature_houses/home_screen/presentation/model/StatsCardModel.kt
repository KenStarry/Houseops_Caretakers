package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class StatsCardModel(
    val icon: ImageVector,
    val iconColor: Color,
    val value: String,
    val title: String
)
