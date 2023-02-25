package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel

import android.util.Log
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.PlacesAPIResult
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LndAddApartmentViewModel : ViewModel() {

    lateinit var placesClient: PlacesClient

    val locationAutoFill = mutableStateListOf<PlacesAPIResult>()

    //  bottomsheet type
    var bottomSheetType by mutableStateOf("none")
        private set

    private var job: Job? = null

    @OptIn(ExperimentalMaterialApi::class)
    fun onEvent(event: LndApartmentEvents) {

        when (event) {
            is LndApartmentEvents.SearchPlaces -> {
                job?.cancel()
                locationAutoFill.clear()

                job = viewModelScope.launch {
                    val request = FindAutocompletePredictionsRequest
                        .builder()
                        .setQuery(event.query)
                        .build()

                    placesClient
                        .findAutocompletePredictions(request)
                        .addOnSuccessListener { response ->
                            locationAutoFill += response.autocompletePredictions.map {
                                PlacesAPIResult(
                                    it.getFullText(null).toString(),
                                    it.placeId
                                )
                            }
                        }
                        .addOnFailureListener {
                            Log.d("places", "message : ${it.message}")
                            Log.d("places", "cause : ${it.cause}")
                        }
                }

            }
            is LndApartmentEvents.CloseBottomSheet -> {
                //  close bottom sheet
                event.scope.launch {
                    event.state.animateTo(
                        ModalBottomSheetValue.Hidden
                    )
                }
            }
            is LndApartmentEvents.OpenBottomSheet -> {
                //  open bottom sheet
                event.scope.launch {

                    bottomSheetType = event.bottomSheetType

                    event.state.animateTo(
                        ModalBottomSheetValue.Expanded
                    )
                }
            }
        }
    }
}




















