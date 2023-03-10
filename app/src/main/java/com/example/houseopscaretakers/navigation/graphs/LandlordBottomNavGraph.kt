package com.example.houseopscaretakers.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.houseopscaretakers.feature_landlord.feature_caretakers.LandlordCaretakers
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.LandlordAddApartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.LandlordHome
import com.example.houseopscaretakers.feature_landlord.feature_settings.LandlordSettings
import com.example.houseopscaretakers.navigation.LandlordBottomNavScreens
import com.example.houseopscaretakers.navigation.LandlordScreens

@Composable
fun LandlordBottomNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = LandlordBottomNavScreens.Home.route
    ) {

        composable(route = LandlordBottomNavScreens.Home.route) {
            LandlordHome(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Caretakers.route) {
            LandlordCaretakers(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Settings.route) {
            LandlordSettings(navHostController = navHostController)
        }

        //  landlord add apartment screen
        composable(route = LandlordScreens.AddApartment.route) {
            LandlordAddApartment(navHostController = navHostController)
        }
    }

}