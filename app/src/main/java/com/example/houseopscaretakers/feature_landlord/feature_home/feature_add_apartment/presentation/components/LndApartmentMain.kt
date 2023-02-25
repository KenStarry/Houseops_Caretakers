package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.example.houseopscaretakers.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LndApartmentMain(
    direction: Direction,
    lndAddApartmentVM: LndAddApartmentViewModel,
    onLocationClicked: () -> Unit,
    onHouseFeaturesClicked: () -> Unit
) {
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
                    .verticalScroll(rememberScrollState())
            ) {

                //  apartment details
                LndApartmentDetails(
                    modifier = Modifier
                        .fillMaxSize(),
                    lndAddApartmentVM = lndAddApartmentVM,
                    onLocationClicked = {
                        //  open places api
                        onLocationClicked()
                    },
                    onHouseFeaturesClicked = {
                        onHouseFeaturesClicked()
                    }
                )

            }

        }

    }
}