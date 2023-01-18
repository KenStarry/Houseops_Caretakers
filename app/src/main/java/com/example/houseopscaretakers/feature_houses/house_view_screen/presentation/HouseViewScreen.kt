package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components.HousePager
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components.HouseViewAppbar
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components.HouseViewContent
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(
    ExperimentalPagerApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    houseCategory: String,
    apartmentName: String
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val coreViewModel: CoreViewModel = hiltViewModel()

    //  get the current house
    coreViewModel.onEvent(
        CoreEvents.GetHouse(
            apartmentName = apartmentName,
            category = houseCategory
        )
    )
    val currentHouse = coreViewModel.currentHouse

    Scaffold(
        topBar = {
            HouseViewAppbar(
                category = houseCategory,
                onBackPressed = { /*TODO*/ },
                onHouseEdit = {}
            )
        }
    ) { contentPadding ->

        //  Box to hold our contents
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            //  Image & Navigation icons
            currentHouse?.let {

                //  our house pager
                HousePager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f),
                    currentHouseImages = it.houseImageUris
                )

            }


        }

    }
}























