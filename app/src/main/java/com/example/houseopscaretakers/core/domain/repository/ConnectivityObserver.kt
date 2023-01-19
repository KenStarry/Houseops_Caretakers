package com.example.houseopscaretakers.core.domain.repository

import com.example.houseopscaretakers.core.domain.model.ConnectionStatus
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observeConnectivity() : Flow<ConnectionStatus>

}