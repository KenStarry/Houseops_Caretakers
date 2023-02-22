package com.example.houseopscaretakers.feature_caretaker.core.domain.use_cases

import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.ConnectivityObserver

class Connection(
    private val repo: ConnectivityObserver
) {

    operator fun invoke() = repo.observeConnectivity()
}