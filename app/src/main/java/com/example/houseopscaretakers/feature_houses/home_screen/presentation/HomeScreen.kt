package com.example.houseopscaretakers.feature_houses.home_screen.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.presentation.components.BottomSheet
import com.example.houseopscaretakers.core.presentation.components.customSwipeAction
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseEvents
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeApartmentTitle
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeFab
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.HomeTopAppBar
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.StatsCard
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet.AddHouseBottomSheet
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item.HouseItem
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.navigation.Screen
import com.example.houseopscaretakers.ui.theme.RedOrange
import com.example.houseopscaretakers.ui.theme.RedOrangeDull
import me.saket.swipe.SwipeableActionsBox

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
                onHouseAdd = { house ->

                    // upload images to firestore
                    viewModel.onEvent(
                        CoreEvents.UploadImageEvent(
                            imageUriList = homeviewModel.selectedImagesState.listOfSelectedImages,
                            context = context,
                            houseModel = house,
                            apartmentName = caretaker?.caretakerApartment ?: "none"
                        )
                    )

                    //  add house to apartments collection
                    homeviewModel.onBottomSheetEvent(
                        BottomSheetEvents.AddHouseToFirestore(
                            caretaker?.caretakerApartment ?: "none", house
                        )
                    )

                    //  close bottom sheet
                    homeviewModel.onBottomSheetEvent(
                        BottomSheetEvents.CloseBottomSheet(
                            state,
                            scope
                        )
                    )
                }
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
                            .verticalScroll(rememberScrollState())
                    ) {

                        Spacer(modifier = Modifier.height(24.dp))

                        //  greetings text
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = "Hello",
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                            )

                            Text(
                                text = caretaker?.caretakerName ?: "",
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                            )

                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        //  Apartment Statistics cards
                        LazyRow(
                            content = {
                                itemsIndexed(
                                    Constants.statsCardList
                                ) { index, card ->

                                    StatsCard(
                                        title = card.title,
                                        icon = card.icon,
                                        iconColor = card.iconColor,
                                        value = card.value,
                                        onClick = { title ->
                                            //  navigate to card page
                                        }
                                    )
                                }
                            },
                            state = rememberLazyListState(),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        )


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

                        //  toggle visibility of the houses accordingly
                        if (homeviewModel.housesState.isEmpty()) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize(0.8f)
                                    .padding(24.dp)
                                    .align(Alignment.CenterHorizontally),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                //  show svg
                                Image(
                                    painter = painterResource(id = R.drawable.undraw_new_ideas_re_asn4),
                                    contentDescription = "No houses",
                                    contentScale = ContentScale.Fit
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = "Add Houses to see them here...",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    )
                                )
                            }

                        } else {

                            //  delete action for swipeable component
                            val delete = customSwipeAction(
                                icon = Icons.Outlined.DeleteForever,
                                iconTint = RedOrange,
                                background = RedOrangeDull,
                                onSwipe = {
                                    Log.d("Swipe", "Deleted")
                                }
                            )

                            //  Lazy column to display house categories
                            LazyColumn(
                                content = {
                                    items(
                                        items = homeviewModel.housesState
                                    ) {

                                        SwipeableActionsBox(
                                            startActions = listOf(),
                                            endActions = listOf(delete)
                                        ) {

                                            HouseItem(
                                                house = it,
                                                onViewClick = {
                                                    //  navigate to house view activity
                                                    navHostController.navigate(
                                                        route = Screen.HouseView.passHouseCategoryAndApartment(
                                                            apartment = caretaker?.caretakerApartment
                                                                ?: "Apartments",
                                                            category = it.houseCategory
                                                        )
                                                    )
                                                },
                                                context = context
                                            )

                                        }

                                    }
                                },
                                state = rememberLazyListState(),
                                verticalArrangement = Arrangement.spacedBy(24.dp),
                                userScrollEnabled = true,
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .height(500.dp)
                            )

                        }

                    }

                }
            }

        },
    )
}













