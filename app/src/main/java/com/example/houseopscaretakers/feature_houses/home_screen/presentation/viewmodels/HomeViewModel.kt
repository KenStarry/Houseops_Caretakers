package com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels

import androidx.compose.animation.core.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    //  validate if the apartment name has 'apartments' ahead of it
    fun addApartmentSuffix(
        apartmentName: String
    ): String {

        //  list of split names
        val splitName = apartmentName.lowercase().split(" ")

        val newName = if (splitName[splitName.lastIndex] != "apartment" ||
            splitName[splitName.lastIndex] != "apartments"
        ) {
            "$apartmentName Apartments"
        } else {
            apartmentName
        }

        return newName

    }

    //  open bottom sheet
    @OptIn(ExperimentalMaterialApi::class)
    fun openBottomSheet(state: ModalBottomSheetState, scope: CoroutineScope) {

        scope.launch {
            //  open bottomsheet
            state.animateTo(
                ModalBottomSheetValue.Expanded,
                tween(
                    durationMillis = 300,
                    easing = Ease
                )
            )
        }
    }

    //  close bottom sheet
    @OptIn(ExperimentalMaterialApi::class)
    fun closeBottomSheet(state: ModalBottomSheetState, scope: CoroutineScope) {
        scope.launch {

            state.animateTo(
                ModalBottomSheetValue.Hidden,
                tween(
                    durationMillis = 300,
                    easing = Ease
                )
            )
        }
    }
}




















