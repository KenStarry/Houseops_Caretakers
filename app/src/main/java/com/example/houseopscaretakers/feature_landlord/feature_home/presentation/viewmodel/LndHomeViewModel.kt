package com.example.houseopscaretakers.feature_landlord.feature_home.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_landlord.feature_home.domain.model.LndHomeEvents
import com.example.houseopscaretakers.feature_landlord.feature_home.domain.use_cases.LndHomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LndHomeViewModel @Inject constructor(
    private val useCases: LndHomeUseCases
) : ViewModel() {

    private val _landlordDetails = mutableStateOf<Landlord?>(null)
    val landlordDetails: State<Landlord?> = _landlordDetails

    fun onEvent(event: LndHomeEvents) {
        when (event) {

            is LndHomeEvents.GetLandlordDetails -> {
                viewModelScope.launch {
                    useCases.getLandlordDetails(
                        email = event.email,
                        landlord = {

                        },
                        response = {
                            when (it) {
                                is Response.Success -> {
                                    _landlordDetails.value = it.data as Landlord
                                }
                                is Response.Failure -> {}
                                is Response.Loading -> {}
                            }
                        }
                    )
                }
            }

            is LndHomeEvents.FilterGreetingsText -> {
                when (event.currentHour) {
                    in 0..12 -> {
                        event.greetings("Good Morning", Icons.Outlined.WbSunny)
                    }
                    in 12..16 -> {
                        event.greetings("Good Afternoon", Icons.Outlined.WbCloudy)
                    }
                    else -> {
                        event.greetings("Good Evening", Icons.Outlined.ModeNight)
                    }
                }
            }
        }
    }
}



























