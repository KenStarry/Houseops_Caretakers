package com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels

import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import kotlinx.coroutines.launch

class HomeViewModel () : ViewModel() {

    //  pill name
    private val _pillName = mutableStateOf("House Category")
    val pillName: State<String> = _pillName

    //  houses arraylist
    private val _housePicsList = mutableStateListOf<Uri>()
    val housePicsList: SnapshotStateList<Uri> = _housePicsList

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
    fun onEvent(event: BottomSheetEvents) {

        when (event) {

            is BottomSheetEvents.OpenBottomSheet -> {
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

            is BottomSheetEvents.CloseBottomSheet -> {
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

            is BottomSheetEvents.TogglePillCategory -> {
                _pillName.value = event.category
            }

            is BottomSheetEvents.AddGalleryImages -> {
                //  add all images from gallery
                _housePicsList.addAll(event.uris)
            }
        }
    }
}




















