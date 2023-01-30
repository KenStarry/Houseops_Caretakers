package com.example.houseopscaretakers.feature_houses.home_screen.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.TrackChanges
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.houseopscaretakers.core.domain.model.ConnectionStatus
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.presentation.components.*
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.data.HomeConstants
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseEvents
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.*
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet.AddHouseBottomSheet
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet.ProfileBottomSheet
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet.SortBottomSheet
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.home_content.GreetingsText
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.home_content.StatsCards
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item.HouseItem
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.navigation.Screen
import com.example.houseopscaretakers.ui.theme.LimeGreen
import com.example.houseopscaretakers.ui.theme.LimeGreenDull
import com.example.houseopscaretakers.ui.theme.RedOrange
import com.example.houseopscaretakers.ui.theme.RedOrangeDull
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val coreViewModel: CoreViewModel = hiltViewModel()
    val homeviewModel: HomeViewModel = hiltViewModel()

    val context = LocalContext.current
    val status by coreViewModel.connectionStatus.collectAsState(
        initial = ConnectionStatus.Available
    )

    val caretaker = coreViewModel.getCaretakerDetails(
        email = coreViewModel.currentUser()?.email ?: "no user"
    )

    //  getting all houses
    homeviewModel.onHomeScreenEvent(
        HouseEvents.GetHouses(
            apartmentName = caretaker?.caretakerApartment ?: "none"
        )
    )

    when (status) {

        is ConnectionStatus.Available -> {

            //  Bottom Sheet as the root composable
            BottomSheet(
                sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                sheetContent = { state, scope ->
                    when (homeviewModel.bottomSheetType) {

                        HomeConstants.PROFILE_BOTTOM_SHEET -> {
                            ProfileBottomSheet(
                                caretaker = caretaker,
                                context = context,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.onPrimary)
                                    .wrapContentHeight()
                                    .padding(24.dp)
                            )
                        }

                        HomeConstants.FAB_BOTTOM_SHEET -> {
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
                                homeVM = homeviewModel,
                                apartmentName = caretaker?.caretakerApartment ?: "none",
                                onHouseAdd = { house ->

                                    // upload images to firestore
                                    coreViewModel.onEvent(
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
                        }

                        HomeConstants.SORT_BOTTOM_SHEET -> {
                            //  open sort bottomsheet
                            SortBottomSheet(
                                onOptionSelected = {
                                    //  sort a house based on the options given
                                    homeviewModel.onBottomSheetEvent(
                                        BottomSheetEvents.SortHouseCategories(it)
                                    )
                                }
                            )
                        }

                        else -> {
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
                                homeVM = homeviewModel,
                                apartmentName = caretaker?.caretakerApartment ?: "none",
                                onHouseAdd = { house ->

                                    // upload images to firestore
                                    coreViewModel.onEvent(
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
                        }
                    }
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
                                onClickNotifications = {},
                                onClickWatchlist = {},
                                onClickImage = {
                                    //  open profile bottom sheet
                                    homeviewModel.onBottomSheetEvent(
                                        BottomSheetEvents.OpenBottomSheet(
                                            state,
                                            scope,
                                            HomeConstants.PROFILE_BOTTOM_SHEET
                                        )
                                    )
                                }
                            )
                        },
                        floatingActionButton = {
                            HomeFab(
                                icon = Icons.Rounded.Add,
                                onClick = {
                                    //  open fab bottom sheet
                                    homeviewModel.onBottomSheetEvent(
                                        BottomSheetEvents.OpenBottomSheet(
                                            state,
                                            scope,
                                            HomeConstants.FAB_BOTTOM_SHEET
                                        )
                                    )
                                }
                            )
                        }
                    ) { contentPadding ->

                        //  show content
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

                                LottieLoader(isPlaying = true)

                                Spacer(modifier = Modifier.height(24.dp))

                                //  greetings text
                                GreetingsText(
                                    homeViewModel = homeviewModel,
                                    caretaker = caretaker
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                //  Apartment Statistics cards
                                StatsCards(statsCardsList = Constants.statsCardList)

                                Spacer(modifier = Modifier.height(24.dp))

                                //  My Houses
                                MyHousesTitle(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(horizontal = 16.dp),
                                    onSort = {
                                        //  open sort bottomsheet
                                        homeviewModel.onBottomSheetEvent(
                                            BottomSheetEvents.OpenBottomSheet(
                                                state = state,
                                                scope = scope,
                                                bottomSheetType = HomeConstants.SORT_BOTTOM_SHEET
                                            )
                                        )
                                    }
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

                                    //  Lazy column to display house categories
                                    LazyColumn(
                                        content = {
                                            items(
                                                items = homeviewModel.housesState
                                            ) { house ->

                                                //  Alert Dialogs
                                                if (homeviewModel.openDeleteDialog) {
                                                    CustomAlertDialog(
                                                        onDismiss = {
                                                            //  close dialog
                                                            homeviewModel.onHomeScreenEvent(
                                                                HouseEvents.CloseDeleteDialog
                                                            )
                                                        },
                                                        content = {
                                                            HouseDeleteDialog(
                                                                house = house,
                                                                onCancel = {
                                                                    //  close dialog
                                                                    homeviewModel.onHomeScreenEvent(
                                                                        HouseEvents.CloseDeleteDialog
                                                                    )
                                                                },
                                                                onConfirm = {

                                                                    //  toast message
                                                                    Toast.makeText(
                                                                        context,
                                                                        "${house.houseCategory} category deleted.",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()

                                                                    //  delete house
                                                                    caretaker?.caretakerApartment?.let {
                                                                        homeviewModel.onHomeScreenEvent(
                                                                            HouseEvents.DeleteHouse(
                                                                                apartmentName = it,
                                                                                houseModel = house
                                                                            )
                                                                        )
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    )
                                                }

                                                //  delete action for swipeable component
                                                val delete = customSwipeAction(
                                                    icon = Icons.Outlined.DeleteForever,
                                                    iconTint = RedOrange,
                                                    background = RedOrangeDull,
                                                    onSwipe = {
                                                        //  open dialog
                                                        homeviewModel.onHomeScreenEvent(HouseEvents.OpenDeleteDialog)
                                                    }
                                                )

                                                //  watchlist action for swipeable component
                                                val watchlist = customSwipeAction(
                                                    icon = Icons.Outlined.TrackChanges,
                                                    iconTint = LimeGreen,
                                                    background = LimeGreenDull,
                                                    onSwipe = {
                                                        Log.d("Swipe", "Watchlist")
                                                    }
                                                )

                                                SwipeableActionsBox(
                                                    startActions = listOf(watchlist),
                                                    endActions = listOf(delete),
                                                    swipeThreshold = 20.dp,
                                                    backgroundUntilSwipeThreshold = MaterialTheme.colorScheme.onPrimary,
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(16.dp))
                                                ) {

                                                    HouseItem(
                                                        house = house,
                                                        onViewClick = {
                                                            //  navigate to house view activity
                                                            navHostController.navigate(
                                                                route = Screen.HouseView.passHouseCategoryAndApartment(
                                                                    apartment = caretaker?.caretakerApartment
                                                                        ?: "Apartments",
                                                                    category = house.houseCategory
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

        is ConnectionStatus.Unavailable -> {

            //  show error message
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //  oops text
                Text(
                    text = "Oops",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Image(
                    painter = painterResource(id = R.drawable.undraw_signal_searching_re_yl8n),
                    contentDescription = "Signal lost",
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                )

            }
        }

        is ConnectionStatus.Losing -> {

            //  show yellow warning
        }
        is ConnectionStatus.Lost -> {

            //  show error message
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                //  oops text
                Text(
                    text = "Oops",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Image(
                    painter = painterResource(id = R.drawable.undraw_signal_searching_re_yl8n),
                    contentDescription = "Signal lost",
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                )

            }
        }

    }
}













