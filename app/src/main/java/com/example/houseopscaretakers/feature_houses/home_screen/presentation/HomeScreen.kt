package com.example.houseopscaretakers.feature_houses.home_screen.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
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
import com.example.houseopscaretakers.core.presentation.components.BottomSheet
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeApartmentTitle
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeFab
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeTopAppBar
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet.AddHouseBottomSheet
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val viewModel: CoreViewModel = hiltViewModel()
    val homeviewModel = HomeViewModel()

    val context = LocalContext.current

    val caretaker = viewModel.getCaretakerDetails(
        email = viewModel.currentUser()?.email ?: "starrycodes@gmail.com"
    )

    //  Bottom Sheet as the root composable
    BottomSheet(
        sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,
        sheetContent = {state, scope ->
            AddHouseBottomSheet(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(
                        top = 4.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            )
        },
        closeBottomSheet = { state, scope ->
            homeviewModel.closeBottomSheet(state, scope)
        },
        sheetScope = { state, scope ->

            //  Main Content
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
                            //  open bottom sheet
                            homeviewModel.openBottomSheet(state, scope)
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

                        //  Lazy column to display house categories

                    }

                }
            }

        },
    )
}













