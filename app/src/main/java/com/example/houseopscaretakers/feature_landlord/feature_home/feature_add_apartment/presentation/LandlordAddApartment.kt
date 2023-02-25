package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.LndApartmentDetails
import com.example.houseopscaretakers.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordAddApartment(
    navHostController: NavHostController
) {

    val direction = Direction(navHostController)

    Scaffold(
        topBar = {
            BackPressTopBar(
                startIcon = Icons.Outlined.ArrowBack,
                title = "Add Apartment",
                onBackPressed = {
                    direction.navigateUp()
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {

                //  apartment details
                LndApartmentDetails(
                    modifier = Modifier
                        .fillMaxSize()
                )

            }

        }

    }

}