package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screen.Home.route,
        route = Constants.HOME_ROUTE
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navHostController)
        }

    }
}






























