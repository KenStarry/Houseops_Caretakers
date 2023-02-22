package com.example.houseopscaretakers.feature_caretaker.core.domain.model

sealed class ConnectionStatus {

    object Available : ConnectionStatus()
    object Unavailable : ConnectionStatus()
    object Losing : ConnectionStatus()
    object Lost : ConnectionStatus()

}
