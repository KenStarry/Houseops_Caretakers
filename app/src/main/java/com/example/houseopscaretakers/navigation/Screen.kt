package com.example.houseopscaretakers.navigation

import com.example.houseopscaretakers.core.Constants

sealed class Screen(
    val route: String
) {

    object Path : Screen(Constants.PATH_SCREEN_ROUTE)

    object Loading : Screen(Constants.LOADING_SCREEN_ROUTE)

    object Main : Screen(Constants.MAIN_SCREEN_ROUTE)
    object Login : Screen(Constants.LOGIN_SCREEN_ROUTE)
    object SignUp : Screen("${Constants.SIGN_UP_SCREEN_ROUTE}/{user}"){
        fun passUserType(user: String) = "${Constants.SIGN_UP_SCREEN_ROUTE}/$user"
    }

    object HouseAdd : Screen(Constants.HOUSE_ADD_SCREEN_ROUTE)

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
