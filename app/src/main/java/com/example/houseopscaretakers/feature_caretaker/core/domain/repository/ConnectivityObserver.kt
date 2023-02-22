package com.example.houseopscaretakers.feature_caretaker.core.domain.repository

import com.example.houseopscaretakers.feature_caretaker.core.domain.model.ConnectionStatus
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observeConnectivity() : Flow<ConnectionStatus>

}