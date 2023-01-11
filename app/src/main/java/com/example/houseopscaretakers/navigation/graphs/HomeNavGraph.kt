package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.HouseViewScreen
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

        //  house view screen
        composable(
            route = Screen.HouseView.route,
            arguments = listOf(
                navArgument("apartment") {
                    type = NavType.StringType
                },
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            HouseViewScreen(
                navHostController = navHostController,
                houseCategory = it.arguments?.getString("category") ?: "none",
                apartmentName = it.arguments?.getString("apartment") ?: "none"
            )
        }

    }
}






























