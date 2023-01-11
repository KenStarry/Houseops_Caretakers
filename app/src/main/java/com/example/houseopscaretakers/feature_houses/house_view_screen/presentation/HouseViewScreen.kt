package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components.HousePager

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

    //  Box to hold our contents
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
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

            //  Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                //  toolbar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                }

                //  Main scrollable content
                Column(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .fillMaxWidth()
                        .weight(4f)
                        .background(MaterialTheme.colorScheme.onPrimary)
                ) {

                }

            }

        }


    }
}























