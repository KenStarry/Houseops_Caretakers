package com.example.houseopscaretakers.feature_authentication.login.domain.model

sealed class ValidationEvent {
    object Success : ValidationEvent()
    object Failure : ValidationEvent()
}
