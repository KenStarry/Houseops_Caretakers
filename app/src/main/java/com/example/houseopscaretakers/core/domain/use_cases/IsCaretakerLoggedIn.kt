package com.example.houseopscaretakers.core.domain.use_cases

import com.example.houseopscaretakers.core.domain.repository.CoreRepository

class IsCaretakerLoggedIn(
    private val repo: CoreRepository
) {

    suspend operator fun invoke(): Boolean {
        return repo.isUserLoggedIn()
    }
}