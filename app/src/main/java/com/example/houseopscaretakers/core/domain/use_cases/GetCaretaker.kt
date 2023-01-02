package com.example.houseopscaretakers.core.domain.use_cases

import androidx.compose.runtime.mutableStateOf
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.repository.CoreRepository

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