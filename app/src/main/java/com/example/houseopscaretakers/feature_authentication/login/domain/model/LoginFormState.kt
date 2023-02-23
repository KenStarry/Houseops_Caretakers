package com.example.houseopscaretakers.feature_authentication.login.domain.model

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
