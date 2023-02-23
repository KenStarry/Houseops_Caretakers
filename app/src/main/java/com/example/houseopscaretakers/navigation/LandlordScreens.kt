package com.example.houseopscaretakers.navigation

sealed class LandlordScreens(
    val route: String
) {

    object Home : LandlordScreens("landlord_home")
}
