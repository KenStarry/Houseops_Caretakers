package com.example.houseopscaretakers.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomNavScreens(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Tenants : BottomNavScreens(
        route = "tenants_screen",
        title = "Tenants",
        icon = Icons.Outlined.VerifiedUser
    )

    object Statistics : BottomNavScreens(
        route = "statistics_screen",
        title = "Statistics",
        icon = Icons.Outlined.BubbleChart
    )

    object Settings : BottomNavScreens(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Outlined.Settings
    )

}
