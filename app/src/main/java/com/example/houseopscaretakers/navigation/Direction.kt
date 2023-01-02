package com.example.houseopscaretakers.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    val navigateToRoute: (route: String) -> Unit = {
        navHostController.navigate(route = it)
    }
}