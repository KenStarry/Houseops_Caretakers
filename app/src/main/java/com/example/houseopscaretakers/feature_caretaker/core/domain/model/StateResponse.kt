package com.example.houseopscaretakers.feature_caretaker.core.domain.model

sealed class StateResponse {
    object Loading : StateResponse()
    object Success : StateResponse()
    object Failure : StateResponse()
}
