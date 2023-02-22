package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.viewmodels

import androidx.compose.animation.core.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.*
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.use_cases.HouseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HouseUseCases
) : ViewModel() {

    //  house id
    val _houseID = mutableStateOf("")
    val houseID: State<String> = _houseID

    //  houses list
    var housesState by mutableStateOf<List<HouseModel>>(emptyList())
        private set

    //  bottomsheet type
    var bottomSheetType by mutableStateOf("none")
        private set

    //  current house
    var currentHouse by mutableStateOf<HouseModel?>(null)
        private set

    //  open delete dialog
    var openDeleteDialog by mutableStateOf(false)
        private set

    var areDetailsValid by mutableStateOf(false)
        private set

    private val _validationMessage = mutableStateOf("")
    val validationMessage: State<String> = _validationMessage

    //  pill name
    private val _pillName = mutableStateOf("Choose house category")
    val pillName: State<String> = _pillName

    //  price category
    private val _priceCategory = mutableStateOf(com.example.houseopscaretakers.feature_caretaker.core.Constants.priceCategories[0])
    val priceCategory: State<String> = _priceCategory

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

    fun getCurrentHouse(category: String): HouseModel? {
        //  loop through all houses
        housesState.forEach { house ->
            if (house.houseCategory == category) {
                currentHouse = house
            }
        }

        return currentHouse
    }

    fun generateHouseID(
        apartmentName: String,
        houseCategory: String
    ): String {

        viewModelScope.launch {

            //  get first 2 letters of the apartmentName
            val apartmentNameTrim = apartmentName.take(2)
            val categoryTrim = houseCategory.take(2)

            var randomDigits = ""

            (1..5).forEach {
                randomDigits += (0..9).random().toString()
            }

            _houseID.value = "$apartmentNameTrim-$categoryTrim-$randomDigits"
        }

        return _houseID.value
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun onBottomSheetEvent(event: BottomSheetEvents) {

        when (event) {

            is BottomSheetEvents.AddGalleryImages -> {}

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

                    bottomSheetType = event.bottomSheetType

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

            is BottomSheetEvents.TogglePriceCategory -> {
                _priceCategory.value = event.category
            }

            is BottomSheetEvents.SortHouseCategories -> {

                //  check the type of sort that has taken place
                when (event.selectedOption) {
                    //  custom sort
                    com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.data.HomeConstants.sortOptions[0] -> {

                    }
                    //  alphabetical ascending sort
                    com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.data.HomeConstants.sortOptions[1] -> {
                        housesState = housesState.sortedBy { it.houseCategory }
                    }
                    //  alphabetical descending sort
                    com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.data.HomeConstants.sortOptions[2] -> {
                        housesState = housesState.sortedByDescending { it.houseCategory }
                    }
                    //  units ascending sort
                    com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.data.HomeConstants.sortOptions[3] -> {
                        housesState = housesState.sortedBy { it.houseUnits.toInt() }
                    }
                    //  units descending sort
                    com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.data.HomeConstants.sortOptions[4] -> {
                        housesState = housesState.sortedByDescending { it.houseUnits.toInt() }
                    }
                }
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

            is BottomSheetEvents.ValidateDetails -> {

                viewModelScope.launch {
                    if (event.house.houseCategory == "Choose house category") {
                        areDetailsValid = false
                        _validationMessage.value = "Please choose a house category"

                    } else if (event.house.housePrice == "") {
                        areDetailsValid = false
                        _validationMessage.value = "Specify a price for your house."

                    } else if (selectedImagesState.listOfSelectedImages.isEmpty()) {
                        areDetailsValid = false
                        _validationMessage.value = "Please add at least 1 image."

                    } else {
                        areDetailsValid = true
                        _validationMessage.value = "House Added successfully"
                    }
                }

            }
        }
    }

    //  events for the home screen
    fun onHomeScreenEvent(event: HouseEvents) {

        when (event) {

            is HouseEvents.OpenDeleteDialog -> {
                openDeleteDialog = true
            }

            is HouseEvents.CloseDeleteDialog -> {
                openDeleteDialog = false
            }

            is HouseEvents.GetHouses -> {
                viewModelScope.launch {

                    useCases.getHouses(
                        event.apartmentName,
                        houseList = {
                            housesState = it
                        }
                    )
                }
            }

            is HouseEvents.GetHouse -> {
                viewModelScope.launch {
                    //  loop through all houses
                    housesState.forEach { house ->

                        if (house.houseCategory == event.category) {
                            currentHouse = house
                        }
                    }
                }
            }

            is HouseEvents.DeleteHouse -> {
                //  delete house from firestore
                viewModelScope.launch {
                    useCases.deleteHouse(
                        apartmentName = event.apartmentName,
                        houseModel = event.houseModel
                    )
                }
            }
        }
    }
}




















