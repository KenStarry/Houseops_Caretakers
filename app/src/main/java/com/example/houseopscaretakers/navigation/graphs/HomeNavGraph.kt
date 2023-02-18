package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_main_screen.MainScreen
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.feature_houses.house_add_screen.presentation.HouseAddScreen
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.HouseViewScreen
import com.example.houseopscaretakers.feature_settings.SettingsScreen
import com.example.houseopscaretakers.feature_statistics.StatisticsScreen
import com.example.houseopscaretakers.feature_tenants.TenantsScreen
import com.example.houseopscaretakers.navigation.BottomNavScreens
import com.example.houseopscaretakers.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = BottomNavScreens.Home.route,
        route = Constants.HOME_ROUTE
    ) {

        //  main screen
        composable(route = Screen.Main.route) {
            MainScreen(navHostController = navHostController)
        }

        //  Home screen
        composable(route = BottomNavScreens.Home.route) {
            HomeScreen(navHostController)
        }

        //  Booked screen
        composable(route = BottomNavScreens.Tenants.route) {
            TenantsScreen(navHostController)
        }

        //  Wishlist screen
        composable(route = BottomNavScreens.Statistics.route) {
            StatisticsScreen(navHostController = navHostController)
        }

        //  Settings screen
        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen(navHostController)
        }
        
        composable(route = Screen.HouseAdd.route) {
            HouseAddScreen(navHostController = navHostController)
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






























