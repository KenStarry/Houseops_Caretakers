package com.example.houseopscaretakers.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseopscaretakers.core.Constants

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean
) {

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) com.example.houseopscaretakers.core.Constants.HOME_ROUTE else com.example.houseopscaretakers.core.Constants.AUTHENTICATION_ROUTE,
        route = com.example.houseopscaretakers.core.Constants.ROOT_ROUTE
    ) {

        homeNavGraph(navHostController)
        authNavGraph(navHostController)

    }

}