package com.example.houseopscaretakers.feature_authentication.login.domain.model

sealed class LoginFormEvent {

    data class EmailChanged(val email: String) : LoginFormEvent()

    data class PasswordChanged(val pass: String) : LoginFormEvent()

    object Submit : LoginFormEvent()
}
