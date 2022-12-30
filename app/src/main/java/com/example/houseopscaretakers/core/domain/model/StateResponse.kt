package com.example.houseopscaretakers.core.domain.model

sealed class StateResponse {
    object Loading : StateResponse()
    object Success : StateResponse()
    object Failure : StateResponse()
}
