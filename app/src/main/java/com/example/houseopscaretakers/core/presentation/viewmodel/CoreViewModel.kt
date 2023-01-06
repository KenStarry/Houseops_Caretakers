package com.example.houseopscaretakers.core.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.domain.use_cases.CoreUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCase: CoreUseCases
) : ViewModel() {

    private var loggedInState by mutableStateOf(false)
    var caretaker by mutableStateOf<Caretaker?>(null)
    private var currentUser by mutableStateOf<FirebaseUser?>(null)

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
        }
    }
}