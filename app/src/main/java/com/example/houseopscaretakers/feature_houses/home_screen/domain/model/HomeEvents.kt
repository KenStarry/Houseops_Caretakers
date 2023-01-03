package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope

sealed class HomeEvents {

    //  open bottom sheet
    data class OpenBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : HomeEvents()

    //  close bottom sheet
    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : HomeEvents()

    //  change pill category
    data class TogglePillCategory(val category: String) : HomeEvents()
}
