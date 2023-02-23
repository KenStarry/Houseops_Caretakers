package com.example.houseopscaretakers.feature_authentication.login.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
