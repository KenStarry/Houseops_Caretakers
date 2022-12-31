package com.example.houseopscaretakers.navigation

sealed class Screen(
    val route: String
) {
    object Home : Screen("home_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
}
