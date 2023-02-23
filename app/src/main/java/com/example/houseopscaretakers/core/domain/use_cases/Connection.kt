package com.example.houseopscaretakers.core.domain.use_cases

import com.example.houseopscaretakers.core.domain.repository.ConnectivityObserver

class Connection(
    private val repo: ConnectivityObserver
) {

    operator fun invoke() = repo.observeConnectivity()
}