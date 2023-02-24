package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_landlord.feature_caretakers.LandlordCaretakers
import com.example.houseopscaretakers.feature_landlord.feature_home.LandlordHome
import com.example.houseopscaretakers.feature_landlord.feature_main.presentation.LandlordMain
import com.example.houseopscaretakers.feature_landlord.feature_settings.LandlordSettings
import com.example.houseopscaretakers.navigation.LandlordBottomNavScreens
import com.example.houseopscaretakers.navigation.LandlordScreens

fun NavGraphBuilder.landlordNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = LandlordScreens.Main.route,
        route = Constants.LANDLORD_ROUTE
    ) {

        //  landlord home screen
        composable(route = LandlordScreens.Main.route) {
            LandlordMain(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Home.route) {
            LandlordHome(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Caretakers.route) {
            LandlordCaretakers(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Settings.route) {
            LandlordSettings(navHostController = navHostController)
        }
    }

}