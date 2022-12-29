package com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val signUpUseCases: SignUpUseCases
) : ViewModel() {

    //  create user response
    var createUserResponse by mutableStateOf<CreateUserResponse>(Response.Success(false))

    //  create user in firebase using email and password
    fun createCaretakerWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {

            createUserResponse = signUpUseCases.createCaretakerInFirebase(email, password)

            when (createUserResponse) {
                is Response.Success -> onSuccess()
                is Response.Failure -> onFailure()
                is Response.Loading -> {}
            }
        }
    }

    //  create caretaker collection in firestore
    fun createCaretakerCollection(
        caretaker: Caretaker,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {

        viewModelScope.launch {

            createUserResponse = signUpUseCases.createCaretakerCollection(
                caretaker = caretaker
            )

            when (createUserResponse) {
                is Response.Success -> onSuccess()
                is Response.Failure -> onFailure()
                is Response.Loading -> {}
            }
        }
    }
}






































