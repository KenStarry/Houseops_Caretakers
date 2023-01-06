package com.example.houseopscaretakers.feature_houses.home_screen.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.BottomSheet
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseEvents
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeApartmentTitle
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeFab
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeTopAppBar
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet.AddHouseBottomSheet
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item.HouseItem
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val viewModel: CoreViewModel = hiltViewModel()
    val homeviewModel: HomeViewModel = hiltViewModel()

    val context = LocalContext.current

    val caretaker = viewModel.getCaretakerDetails(
        email = viewModel.currentUser()?.email ?: "no user"
    )

    //  getting all houses
    homeviewModel.onHomeScreenEvent(
        HouseEvents.GetHouses(
            apartmentName = caretaker?.caretakerApartment ?: "none"
        )
    )

    //  Bottom Sheet as the root composable
    BottomSheet(
        sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->
            AddHouseBottomSheet(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 4.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .verticalScroll(rememberScrollState()),
                viewModel = homeviewModel,
                apartmentName = caretaker?.caretakerApartment ?: "none"
            )
        },
        closeBottomSheet = { state, scope ->
            homeviewModel.onBottomSheetEvent(BottomSheetEvents.CloseBottomSheet(state, scope))
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
                            homeviewModel.onBottomSheetEvent(
                                BottomSheetEvents.OpenBottomSheet(
                                    state,
                                    scope
                                )
                            )
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
                    ) {

                        Spacer(modifier = Modifier.height(24.dp))

                        //  Apartment Name
                        HomeApartmentTitle(
                            viewModel = homeviewModel,
                            apartmentName = caretaker?.caretakerApartment ?: "Apartments",
                            onFilter = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        //  Lazy column to display house categories
                        LazyColumn(
                            content = {
                                items(
                                    items = homeviewModel.housesState
                                ) {
                                    HouseItem(
                                        house = it,
                                        onViewClick = {
                                            //  navigate to house view activity
                                        }
                                    )
                                }
                            },
                            state = rememberLazyListState(),
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            userScrollEnabled = true,
                            contentPadding = PaddingValues(16.dp),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )

                    }

                }
            }

        },
    )
}













