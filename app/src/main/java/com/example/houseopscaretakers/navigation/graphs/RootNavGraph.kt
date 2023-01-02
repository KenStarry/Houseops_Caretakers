package com.example.houseopscaretakers.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_authentication.login.presentation.LoginScreen
import com.example.houseopscaretakers.feature_authentication.login.presentation.viewmodel.LoginViewModel
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.SignUpScreen
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.navigation.Screen

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    startDestinationRoute: String
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestinationRoute,
        route = Constants.ROOT_ROUTE
    ) {

        homeNavGraph(navHostController)
        authNavGraph(navHostController)

    }

}