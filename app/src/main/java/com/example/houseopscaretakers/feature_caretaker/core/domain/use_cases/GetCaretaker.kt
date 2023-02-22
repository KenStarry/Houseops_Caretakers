package com.example.houseopscaretakers.feature_caretaker.core.domain.use_cases

import com.example.houseopscaretakers.feature_caretaker.core.domain.model.Caretaker
import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.CoreRepository

class GetCaretaker(
    private val repo: CoreRepository
) {

    suspend operator fun invoke(
        email: String,
        currentCaretaker: (caretaker: Caretaker) -> Unit
    ) {
        repo.getCurrentCaretaker(
            email = email,
            currentCaretaker = {
                currentCaretaker(it)
            }
        )
    }
}