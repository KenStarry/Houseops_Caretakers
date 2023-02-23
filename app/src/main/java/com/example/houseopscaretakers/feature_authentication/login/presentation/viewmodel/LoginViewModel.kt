package com.example.houseopscaretakers.feature_authentication.login.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.feature_authentication.login.domain.model.LoginFormEvent
import com.example.houseopscaretakers.feature_authentication.login.domain.model.LoginFormState
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationEvent
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUseCases
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation.LoginValidateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val useCase: LoginUseCases,
    val validateUseCases: LoginValidateUseCases
) : ViewModel() {

    private var response by mutableStateOf(false)

    var formState by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //  verify login details of user
    fun areDetailsValid(
        email: String,
        password: String
    ): Boolean {

        viewModelScope.launch {

            response = if (email.isBlank() || !email.endsWith("com") || !email.contains(
                    "@",
                    ignoreCase = true
                )
            ) {
                false
            } else password.isNotBlank()
        }

        return response

    }

    //  login user
    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        onLoading: () -> Unit
    ) {

        viewModelScope.launch {

            useCase.loginUser(
                email,
                password,
                onSuccess = {
                    Log.d("LOGIN_MODEL", it.toString())
                    onSuccess()
//                    when (it) {
//                        is StateResponse.Loading -> {
//                            onLoading()
//                        }
//                        is StateResponse.Success -> {
//                            onSuccess()
//                        }
//                        is StateResponse.Failure -> {
//                            onFailure()
//                        }
//                        else -> {
//                            onFailure()
//                        }
//                    }
                }
            )

            //  login user and check the state

            Log.d("LOGIN", useCase.loginUser(email, password, onSuccess = {}).toString())
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
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

}


































