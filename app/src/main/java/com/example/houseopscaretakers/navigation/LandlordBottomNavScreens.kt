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
        route = "landlord_home",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Caretakers : LandlordBottomNavScreens(
        route = "landlord_caretakers",
        title = "Caretakers",
        icon = Icons.Outlined.AdminPanelSettings
    )

    object Settings : LandlordBottomNavScreens(
        route = "landlord_settings",
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
}
