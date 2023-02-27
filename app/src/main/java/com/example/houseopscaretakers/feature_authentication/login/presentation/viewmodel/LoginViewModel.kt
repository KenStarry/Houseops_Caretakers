package com.example.houseopscaretakers.feature_authentication.login.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.feature_authentication.login.domain.model.*
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUseCases
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation.LoginValidateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCases,
    private val validateUseCases: LoginValidateUseCases
) : ViewModel() {

    private var response by mutableStateOf(false)

    var formState by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //  login events
    fun onEvent(event: LoginEvents) {
        when (event) {

            is LoginEvents.LoginUser -> {
                viewModelScope.launch {
                    useCase.loginUser(
                        email = event.email,
                        password = event.password,
                        response = { event.response(it) }
                    )
                }
            }
        }
    }

    //  login form event
    fun onFormEvent(event: LoginFormEvent) {
        when (event) {

            is LoginFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.pass)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

        val emailResult: ValidationResult =
            validateUseCases.loginValidateEmail.execute(formState.email)

        val passwordResult: ValidationResult =
            validateUseCases.loginValidatePassword.execute(formState.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            formState = formState.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

}


































