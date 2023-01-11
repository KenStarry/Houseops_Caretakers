package com.example.houseopscaretakers.navigation

import com.example.houseopscaretakers.core.Constants

sealed class Screen(
    val route: String
) {
    object Home : Screen(Constants.HOME_SCREEN_ROUTE)
    object Login : Screen(Constants.LOGIN_SCREEN_ROUTE)
    object SignUp : Screen(Constants.SIGN_UP_SCREEN_ROUTE)

    object HouseView : Screen(
        route = "${Constants.HOUSE_VIEW_SCREEN_ROUTE}/{apartment}/{category}"
    ) {
        fun passHouseCategoryAndApartment(
            apartment: String,
            category: String
        ): String =
            "${Constants.HOUSE_VIEW_SCREEN_ROUTE}/$apartment/${category}"
    }
}
