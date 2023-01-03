package com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels

import androidx.compose.animation.core.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HomeEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel () : ViewModel() {

    private val _pillName = mutableStateOf("House Category")
    val pillName: State<String> = _pillName

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

    @OptIn(ExperimentalMaterialApi::class)
    fun onEvent(event: HomeEvents) {

        when (event) {

            is HomeEvents.OpenBottomSheet -> {
                //  open bottom sheet
                event.scope.launch {
                    event.state.animateTo(
                        ModalBottomSheetValue.Expanded,
                        tween(
                            durationMillis = 300,
                            easing = Ease
                        )
                    )
                }
            }

            is HomeEvents.CloseBottomSheet -> {
                //  close bottom sheet
                event.scope.launch {
                    event.state.animateTo(
                        ModalBottomSheetValue.Hidden,
                        tween(
                            durationMillis = 300,
                            easing = Ease
                        )
                    )
                }
            }

            is HomeEvents.TogglePillCategory -> {
                _pillName.value = event.category
            }
        }
    }
}




















