package com.example.houseopscaretakers.navigation

sealed class LandlordScreens(
    val route: String
) {

    object Main : LandlordScreens(NavConstants.LANDLORD_MAIN_SCREEN_ROUTE)
}
