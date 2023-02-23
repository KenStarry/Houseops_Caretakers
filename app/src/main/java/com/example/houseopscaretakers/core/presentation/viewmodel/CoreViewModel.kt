package com.example.houseopscaretakers.core.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.data.datastore.preferences.UserDetailsPreference
import com.example.houseopscaretakers.feature_caretaker.feature_settings.data.datastore.AccentPreference
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.domain.use_cases.CoreUseCases
import com.example.houseopscaretakers.core.presentation.model.RoutePath
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCase: CoreUseCases,
    private val accentPreference: AccentPreference,
    private val userDetailsPreference: UserDetailsPreference
) : ViewModel() {

    //  get primary and tertiary colors
    val primaryAccentFlow: Flow<Int?> get() = accentPreference.getPrimaryAccent
    val tertiaryAccentFlow: Flow<Int?> get() = accentPreference.getTertiaryAccent

    val userTypeFlow: Flow<String?> get() = userDetailsPreference.getUserType

//    var connectionStatus by mutableStateOf<ConnectionStatus>(ConnectionStatus.Available)
    var connectionStatus by mutableStateOf(useCase.connection())

    var isSplashOpened by mutableStateOf(false)

    private var loggedInState by mutableStateOf(false)
    var caretaker by mutableStateOf<Caretaker?>(null)
    var currentHouse by mutableStateOf<HouseModel?>(null)
    private var currentUser by mutableStateOf<FirebaseUser?>(null)

    private val _pathSelected = mutableStateOf<RoutePath?>(null)
    val pathSelected: State<RoutePath?> = _pathSelected

    //  is user logged in
    fun isUserLoggedIn(): Boolean {

        viewModelScope.launch {
            loggedInState = useCase.isCaretakerLoggedIn()
        }

        return loggedInState
    }

    //  get caretaker details
    fun getCaretakerDetails(
        email: String,
    ): Caretaker? {

        viewModelScope.launch {
            useCase.getCaretaker(email = email) {
                caretaker = it
            }
            Log.d("CR", caretaker?.caretakerName ?: "Nothing")
        }

        return caretaker
    }

    fun currentUser(): FirebaseUser? {
        viewModelScope.launch {
            currentUser = useCase.currentUser()
        }

        return currentUser
    }

    fun onEvent(event: CoreEvents) {
        when (event) {

            is CoreEvents.UploadImageEvent -> {
                viewModelScope.launch {
                    useCase.coreUploadImages(
                        event.imageUriList,
                        event.context,
                        event.houseModel,
                        event.apartmentName
                    )
                }
            }

            is CoreEvents.GetHouse -> {

                viewModelScope.launch {
                    useCase.getHouse(
                        category = event.category,
                        apartmentName = event.apartmentName,
                        currentHouse = {
                            currentHouse = it
                        }
                    )
                }
            }

            is CoreEvents.ChangeAccent -> {
                viewModelScope.launch {
                    accentPreference.setAccent(event.accentColor)
                }
            }

            is CoreEvents.SelectPath -> {
                _pathSelected.value = event.path
            }

            is CoreEvents.DatastoreSaveUserType -> {
                viewModelScope.launch {
                    userDetailsPreference.setUserType(event.userType)
                }
            }
        }
    }
}