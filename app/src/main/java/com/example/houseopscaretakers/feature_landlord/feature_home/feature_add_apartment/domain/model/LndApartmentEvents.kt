package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.BottomSheetEvents
import kotlinx.coroutines.CoroutineScope

sealed class LndApartmentEvents {

    data class SearchPlaces(
        val query: String
    ) : LndApartmentEvents()

    //  add feature to viewmodel
    data class AddFeature(
        val apartmentFeature: ApartmentFeature
    ) : LndApartmentEvents()

    //  open bottom sheet
    data class OpenBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (
        val state: ModalBottomSheetState,
        val scope: CoroutineScope,
        val bottomSheetType: String
    ) : LndApartmentEvents()

    //  close bottom sheet
    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : LndApartmentEvents()
}
