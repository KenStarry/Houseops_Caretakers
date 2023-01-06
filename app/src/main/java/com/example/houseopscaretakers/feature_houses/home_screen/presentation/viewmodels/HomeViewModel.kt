package com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels

import android.net.Uri
import androidx.compose.animation.core.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.ImagesState
import com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases.HouseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HouseUseCases
) : ViewModel() {

    //  pill name
    private val _pillName = mutableStateOf("House Category")
    val pillName: State<String> = _pillName

    //  house features list
    var houseFeatures by mutableStateOf(
        listOf(
            HouseFeatures("Security", Icons.Outlined.Security, false),
            HouseFeatures("Water", Icons.Outlined.Water, false),
            HouseFeatures("Electricity", Icons.Outlined.Thunderstorm, false),
            HouseFeatures("Parking", Icons.Outlined.Park, false),
            HouseFeatures("Shops", Icons.Outlined.Shop, false),
            HouseFeatures("Rooftop", Icons.Outlined.Roofing, false)
        )
    )
        private set

    var selectedImagesState by mutableStateOf(ImagesState())
        private set

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

            is BottomSheetEvents.AddHouseToFirestore -> {
                //  add house to firestore
                viewModelScope.launch {



                    useCases.addHouse(
                        event.apartmentName,
                        event.houseModel
                    )
                }
            }

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

            //  update images
            is BottomSheetEvents.UpdateGalleryImages -> {

                val updatedImageList = selectedImagesState.listOfSelectedImages.toMutableList()

                viewModelScope.launch {
                    updatedImageList += event.uris
                    selectedImagesState = selectedImagesState.copy(
                        listOfSelectedImages = updatedImageList.distinct()
                    )
                }
            }

            //  delete an image
            is BottomSheetEvents.DeleteImageFromList -> {

                val updatedImageList = selectedImagesState.listOfSelectedImages.toMutableList()

                viewModelScope.launch {
                    updatedImageList.removeAt(event.index)
                    selectedImagesState = selectedImagesState.copy(
                        listOfSelectedImages = updatedImageList.distinct()
                    )
                }
            }
        }
    }
}




















