package com.example.houseopscaretakers.core.domain.use_cases

import com.example.houseopscaretakers.core.domain.model.ConnectionStatus
import com.example.houseopscaretakers.core.domain.repository.ConnectivityObserver
import kotlinx.coroutines.flow.Flow

class Connection(
    private val repo: ConnectivityObserver
) {

    suspend operator fun invoke(): Flow<ConnectionStatus> {
        return repo.observeConnectivity()
    }
}