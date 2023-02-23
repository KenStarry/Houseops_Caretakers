package com.example.houseopscaretakers.navigation

import com.example.houseopscaretakers.core.Constants

sealed class Screen(
    val route: String
) {

    object Path : Screen(com.example.houseopscaretakers.core.Constants.PATH_SCREEN_ROUTE)
    object Main : Screen(com.example.houseopscaretakers.core.Constants.MAIN_SCREEN_ROUTE)
    object Login : Screen(com.example.houseopscaretakers.core.Constants.LOGIN_SCREEN_ROUTE)
    object SignUp : Screen(com.example.houseopscaretakers.core.Constants.SIGN_UP_SCREEN_ROUTE)

    object HouseAdd : Screen(com.example.houseopscaretakers.core.Constants.HOUSE_ADD_SCREEN_ROUTE)

    object HouseView : Screen(
        route = "${com.example.houseopscaretakers.core.Constants.HOUSE_VIEW_SCREEN_ROUTE}/{apartment}/{category}"
    ) {
        fun passHouseCategoryAndApartment(
            apartment: String,
            category: String
        ): String =
            "${com.example.houseopscaretakers.core.Constants.HOUSE_VIEW_SCREEN_ROUTE}/$apartment/${category}"
    }
}
