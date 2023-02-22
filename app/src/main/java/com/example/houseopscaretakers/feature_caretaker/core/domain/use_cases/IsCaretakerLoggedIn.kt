package com.example.houseopscaretakers.feature_caretaker.core.domain.use_cases

import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.CoreRepository

class IsCaretakerLoggedIn(
    private val repo: CoreRepository
) {

    suspend operator fun invoke(): Boolean {
        return repo.isUserLoggedIn()
    }
}