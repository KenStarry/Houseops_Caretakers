package com.example.houseopscaretakers.feature_houses.home_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val navHostController = rememberNavController()

    Scaffold() { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            RootNavGraph(navHostController = navHostController)
            Text(text = "Hello")
        }
    }
}