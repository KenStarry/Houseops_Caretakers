package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

import android.net.Uri
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope

sealed class BottomSheetEvents {

    //  open bottom sheet
    data class OpenBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : BottomSheetEvents()

    //  close bottom sheet
    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : BottomSheetEvents()

    //  change pill category
    data class TogglePillCategory(val category: String) : BottomSheetEvents()

    //  Toggle Feature

    //  pick images from gallery
    data class AddGalleryImages(val uris: List<Uri>) : BottomSheetEvents()

    //  update selected images list
    data class UpdateGalleryImages(val uris: List<Uri>) : BottomSheetEvents()

    //  delete image from list
    data class DeleteImageFromList(val index: Int) : BottomSheetEvents()

    //  Firestore
    data class AddHouseToFirestore(val apartmentName: String, val houseModel: HouseModel) : BottomSheetEvents()
}











