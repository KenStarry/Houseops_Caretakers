package com.example.houseopscaretakers.feature_caretaker.feature_main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            MainBottomBar(navHostController = navHostController)
        },
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            RootNavGraph(
                navHostController = navHostController,
                isLoggedIn = coreVM.isUserLoggedIn()
            )
        }
    }

}