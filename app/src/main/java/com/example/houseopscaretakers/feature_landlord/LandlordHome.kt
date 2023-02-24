package com.example.houseopscaretakers.feature_landlord

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel

@Composable
fun LandlordHome(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    
    Text(text = "Welcome Landlord")
    
}