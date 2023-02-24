package com.example.houseopscaretakers.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.components.LoadingScreen
import com.example.houseopscaretakers.navigation.Screen

fun NavGraphBuilder.loadingNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screen.Loading.route,
        route = Constants.LOADING_ROUTE
    ) {
        //  loading screen
        composable(route = Screen.Loading.route) {
            LoadingScreen()
        }
    }
}