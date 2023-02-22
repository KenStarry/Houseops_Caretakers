package com.example.houseopscaretakers.feature_caretaker.feature_houses.house_add_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.feature_caretaker.feature_houses.house_add_screen.presentation.components.HouseAddAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseAddScreen(
    navHostController: NavHostController
) {


    Scaffold(
        topBar = {
            HouseAddAppBar()
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(contentPadding)
            ) {



            }

        }

    }


}