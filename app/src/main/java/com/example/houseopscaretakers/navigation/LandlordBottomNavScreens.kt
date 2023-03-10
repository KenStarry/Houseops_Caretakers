package com.example.houseopscaretakers.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class LandlordBottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : LandlordBottomNavScreens(
        route = NavConstants.LANDLORD_HOME_SCREEN_ROUTE,
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Caretakers : LandlordBottomNavScreens(
        route = NavConstants.LANDLORD_CARETAKERS_SCREEN_ROUTE,
        title = "Caretakers",
        icon = Icons.Outlined.AdminPanelSettings
    )

    object Settings : LandlordBottomNavScreens(
        route = NavConstants.LANDLORD_SETTINGS_SCREEN_ROUTE,
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
}
