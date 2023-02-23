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
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationEvent
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.SignUpEvents
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.SignUpFormEvent
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.SignUpFormState
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.SignUpUseCases
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.validation.SignUpValidateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases,
    private val validateUseCases: SignUpValidateUseCases
) : ViewModel() {

    //  create user response
    private var createUserResponse by mutableStateOf<CreateUserResponse>(Response.Success(false))
    private var validDetailsResponse by mutableStateOf("")

    var formState by mutableStateOf(SignUpFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

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
            signUpUseCases.createUserInFirebase(
                email,
                password,
                response = {
                    when (it) {
                        is Response.Success -> { onSuccess() }
                        is Response.Failure -> { onFailure() }
                        Response.Loading -> {}
                    }
                }
            )
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

    fun onEvent(event: SignUpEvents) {
        when (event) {

            is SignUpEvents.CreateUserInFirebase -> {
                viewModelScope.launch {

                    signUpUseCases.createUserInFirebase(
                        event.email,
                        event.password,
                        response = {
                            event.onResponse(it)
                        }
                    )
                }
            }
        }
    }

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {

            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is SignUpFormEvent.RepeatedPasswordChanged -> {
                formState = formState.copy(repeatedPassword = event.repeatedPassword)
            }

            is SignUpFormEvent.Submit -> {
                submitData()
            }

            is SignUpFormEvent.UserNameChanged -> {
                formState = formState.copy(username = event.username)
            }
        }
    }

    private fun submitData() {

        val emailResult: ValidationResult =
            validateUseCases.validateEmail.execute(formState.email)

        val passwordResult: ValidationResult =
            validateUseCases.validatePassword.execute(formState.password)

        val repeatedPasswordResult: ValidationResult =
            validateUseCases.validateRepeatedPassword.execute(
                formState.password, formState.repeatedPassword
            )

        val userNameResult: ValidationResult =
            validateUseCases.validateUserName.execute(formState.username)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            userNameResult
        ).any { !it.successful }

        if (hasError) {
            formState = formState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                usernameError = userNameResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
}






































