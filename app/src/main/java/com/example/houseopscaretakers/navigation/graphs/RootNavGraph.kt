package com.example.houseopscaretakers.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseopscaretakers.feature_caretaker.core.Constants

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean
) {

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) Constants.HOME_ROUTE else Constants.AUTHENTICATION_ROUTE,
        route = Constants.ROOT_ROUTE
    ) {

        homeNavGraph(navHostController)
        authNavGraph(navHostController)

    }

}