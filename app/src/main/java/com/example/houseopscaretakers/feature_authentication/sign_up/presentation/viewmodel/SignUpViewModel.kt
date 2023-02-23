package com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel

import android.content.Context
import android.net.Uri
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
    private val signUpUseCases: SignUpUseCases
) : ViewModel() {

    //  create user response
    private var createUserResponse by mutableStateOf<CreateUserResponse>(Response.Success(false))
    private var validDetailsResponse by mutableStateOf("")

    //  verify user details
    fun verifyCaretakerDetails(
        userName: String,
        email: String,
        apartment: String,
        id: String,
        newPassword: String,
        confirmPassword: String
    ): String {

        viewModelScope.launch {

            validDetailsResponse = if (userName.isBlank()) {
                com.example.houseopscaretakers.core.Constants.USERNAME_ERROR

            } else if (email.isBlank() && !email.contains("@", ignoreCase = true)) {
                com.example.houseopscaretakers.core.Constants.EMAIL_ERROR

            } else if (apartment.isBlank()) {
                com.example.houseopscaretakers.core.Constants.APARTMENT_ERROR

            } else if (id.length < 8) {
                com.example.houseopscaretakers.core.Constants.ID_ERROR

            } else if (newPassword != confirmPassword) {
                com.example.houseopscaretakers.core.Constants.USERNAME_ERROR

            } else {
                com.example.houseopscaretakers.core.Constants.AUTH_SUCCESSFUL
            }

        }

        return validDetailsResponse

    }

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

    //  upload caretaker image to firestore
    fun uploadImageToStorage(
        caretaker: Caretaker,
        imageUri: Uri?,
        context: Context,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {

            val response = signUpUseCases.uploadCaretakerImage(
                caretaker, imageUri, context
            )

            when (response) {
                is Response.Success -> onSuccess()
                else -> onFailure()
            }
        }
    }
}






































