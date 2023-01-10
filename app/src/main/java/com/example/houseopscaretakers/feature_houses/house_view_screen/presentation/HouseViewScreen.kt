package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    houseCategory: String
) {
    
    //  title
    Text(text = houseCategory)

}