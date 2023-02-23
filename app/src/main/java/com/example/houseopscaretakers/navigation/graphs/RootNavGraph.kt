package com.example.houseopscaretakers.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseopscaretakers.core.Constants

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean,
    userType: String?
) {

    NavHost(
        navController = navHostController,

        startDestination = if (isLoggedIn && userType == Constants.routePaths[0].title) {
            //  landlord
            Constants.LANDLORD_ROUTE
        } else if (isLoggedIn && userType == Constants.routePaths[1].title) {
            //  caretaker
            Constants.HOME_ROUTE
        } else {
            //  no user logged in
            Constants.AUTHENTICATION_ROUTE
        },

        route = Constants.ROOT_ROUTE
    ) {

        homeNavGraph(navHostController)
        authNavGraph(navHostController)
        landlordNavGraph(navHostController)

    }

}