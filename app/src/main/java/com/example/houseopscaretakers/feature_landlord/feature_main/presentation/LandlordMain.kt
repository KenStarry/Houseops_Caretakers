package com.example.houseopscaretakers.feature_landlord.feature_main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.presentation.components.ExtendedFab
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordMain(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()

    Scaffold(
        floatingActionButton = {
            ExtendedFab(
                icon = Icons.Outlined.Apartment,
                text = "Add Apartment",
                onClick = {}
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

        }

    }

}