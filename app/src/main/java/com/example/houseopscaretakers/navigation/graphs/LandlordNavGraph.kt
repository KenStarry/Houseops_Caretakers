package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_landlord.LandlordHome
import com.example.houseopscaretakers.navigation.LandlordScreens

fun NavGraphBuilder.landlordNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = LandlordScreens.Home.route,
        route = Constants.LANDLORD_ROUTE
    ) {

        //  landlord home screen
        composable(route = LandlordScreens.Home.route) {
            LandlordHome(navHostController = navHostController)
        }
    }

}