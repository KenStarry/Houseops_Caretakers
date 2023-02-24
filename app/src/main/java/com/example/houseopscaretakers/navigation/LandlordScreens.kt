package com.example.houseopscaretakers.navigation

sealed class LandlordScreens(
    val route: String
) {

    object Main : LandlordScreens("landlord_home")
}
