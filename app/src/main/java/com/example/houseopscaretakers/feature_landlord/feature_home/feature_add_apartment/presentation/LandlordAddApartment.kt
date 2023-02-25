package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.BuildConfig
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.ApartmentFeature
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.LndApartmentMain
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets.FeaturesBottomSheet
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets.PlacesBottomSheet
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.utils.LndApartmentConstants
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.example.houseopscaretakers.navigation.Direction
import com.google.android.libraries.places.api.Places

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

                LndApartmentConstants.FEATURES_BOTTOM_SHEET -> {
                    FeaturesBottomSheet(
                        lndAddApartmentVM = lndAddApartmentVM,
                        onDone = {title, description ->

                            //  add the feature to viewmodel
                            lndAddApartmentVM.onEvent(LndApartmentEvents.AddFeature(
                                apartmentFeature = ApartmentFeature(
                                    title = title,
                                    description = description
                                )
                            ))

                            lndAddApartmentVM.onEvent(LndApartmentEvents.CloseBottomSheet(
                                state = modalSheetState,
                                scope = scope
                            ))

                            lndAddApartmentVM.featureTitle.value = ""
                            lndAddApartmentVM.featureDescription.value = ""
                        },
                        onCancel = {
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
                },
                onHouseFeaturesClicked = {
                    lndAddApartmentVM.onEvent(
                        LndApartmentEvents.OpenBottomSheet(
                            state = modalSheetState,
                            scope = scope,
                            bottomSheetType = LndApartmentConstants.FEATURES_BOTTOM_SHEET
                        )
                    )
                }
            )
        }
    )


}