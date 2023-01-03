package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope

sealed class HouseEvents {

    //  open bottom sheet
    data class OpenBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : HouseEvents()

    //  close bottom sheet
    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor
        (val state: ModalBottomSheetState, val scope: CoroutineScope) : HouseEvents()

    //  change pill category
    data class TogglePillCategory(val category: String) : HouseEvents()
}
