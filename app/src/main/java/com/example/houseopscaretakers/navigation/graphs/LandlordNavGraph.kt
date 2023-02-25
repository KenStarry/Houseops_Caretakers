package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_landlord.feature_main.presentation.LandlordMain
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
    }

}