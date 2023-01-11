package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components.HousePager

@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    houseCategory: String
) {

    val viewModel: HomeViewModel = hiltViewModel()

    //  Box to hold our contents
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {

        viewModel.getCurrentHouse(houseCategory)?.let {

            //  Image & Navigation icons
            HousePager(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                currentHouse = it
            )
        }

        //  Main Content


    }
}























