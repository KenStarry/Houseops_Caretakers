package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_authentication.login.presentation.LoginScreen
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.SignUpScreen
import com.example.houseopscaretakers.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screen.Login.route,
        route = Constants.AUTHENTICATION_ROUTE
    ) {

        composable(route = Screen.Login.route) {
            LoginScreen(navHostController = navHostController)
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(navHostController = navHostController)
        }

    }
}




















