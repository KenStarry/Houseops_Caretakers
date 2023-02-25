package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.BuildConfig
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.LndApartmentDetails
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.LndApartmentMain
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets.PlacesBottomSheet
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.utils.LndApartmentConstants
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.example.houseopscaretakers.navigation.Direction
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LandlordAddApartment(
    navHostController: NavHostController
) {

    val direction = Direction(navHostController)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lndAddApartmentVM = LndAddApartmentViewModel()

    //  initialize places client
    Places.initialize(context, BuildConfig.MAPS_API_KEY)
    lndAddApartmentVM.placesClient = Places.createClient(context)

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    ModalBottomSheetLayout(
        sheetContent = {

            when (lndAddApartmentVM.bottomSheetType) {
                LndApartmentConstants.PLACES_BOTTOM_SHEET -> {
                    //  open places bottomsheet
                    PlacesBottomSheet(
                        lndAddApartmentVM = lndAddApartmentVM,
                        onInput = {

                            lndAddApartmentVM.pickedLocation.value = it

                            lndAddApartmentVM.onEvent(LndApartmentEvents.CloseBottomSheet(
                                state = modalSheetState,
                                scope = scope
                            ))
                        }
                    )
                }

                else -> {
                    PlacesBottomSheet(
                        lndAddApartmentVM = lndAddApartmentVM,
                        onInput = {

                        }
                    )
                }
            }
        },
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetBackgroundColor = MaterialTheme.colorScheme.onPrimary,
        sheetContentColor = MaterialTheme.colorScheme.onPrimary,
        content = {
            LndApartmentMain(
                direction = direction,
                lndAddApartmentVM = lndAddApartmentVM,
                onLocationClicked = {
                    lndAddApartmentVM.onEvent(
                        LndApartmentEvents.OpenBottomSheet(
                            state = modalSheetState,
                            scope = scope,
                            bottomSheetType = LndApartmentConstants.PLACES_BOTTOM_SHEET
                        )
                    )
                }
            )
        }
    )


}