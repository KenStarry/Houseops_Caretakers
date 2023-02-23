package com.example.houseopscaretakers.feature_authentication.sign_up.domain.model

sealed class SignUpFormEvent {

    data class EmailChanged(val email: String) : SignUpFormEvent()

    data class UserNameChanged(val username: String) : SignUpFormEvent()

    data class PasswordChanged(val password: String) : SignUpFormEvent()

    data class RepeatedPasswordChanged(val repeatedPassword: String) : SignUpFormEvent()

    object Submit : SignUpFormEvent()
}
