package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.houseopscaretakers.core.presentation.components.LoadingScreen
import com.example.houseopscaretakers.core.presentation.components.path_screen.PathScreen
import com.example.houseopscaretakers.feature_authentication.login.presentation.LoginScreen
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.SignUpScreen
import com.example.houseopscaretakers.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screen.Login.route,
        route = com.example.houseopscaretakers.core.Constants.AUTHENTICATION_ROUTE
    ) {

        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navHostController = navHostController)
        }

        composable(
            route = Screen.SignUp.route,
            arguments = listOf(
                navArgument("user") {
                    type = NavType.StringType
                }
            )
        ) {
            SignUpScreen(
                navHostController = navHostController,
                userType = it.arguments?.getString("user") ?: "none"
            )
        }

        composable(route = Screen.Path.route) {
            PathScreen(navHostController = navHostController)
        }

    }
}




















