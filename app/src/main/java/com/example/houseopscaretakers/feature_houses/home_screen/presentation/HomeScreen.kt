package com.example.houseopscaretakers.feature_houses.home_screen.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.SetupNavGraph
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeApartmentTitle
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeFab
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeTopAppBar
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val viewModel: CoreViewModel = hiltViewModel()

    val context = LocalContext.current

    val caretaker = viewModel.getCaretakerDetails(
        email = viewModel.currentUser()?.email ?: "starrycodes@gmail.com"
    )

    Scaffold(
        topBar = {
            HomeTopAppBar(
                context = context,
                title = "Home",
                userImageUri = caretaker?.caretakerImage?.toUri(),
                placeholderImage = R.drawable.houseops_light_final,
                onClickMore = {},
                onClickFilter = {}
            )
        },
        floatingActionButton = {
            HomeFab(
                icon = Icons.Rounded.Add,
                onClick = {
                    navHostController.navigate(Constants.AUTHENTICATION_ROUTE)
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            //  Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {

                //  Apartment Name
                HomeApartmentTitle(
                    viewModel = HomeViewModel(),
                    apartmentName = caretaker?.caretakerApartment ?: "Apartments",
                    onFilter = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

            }

        }
    }
}













