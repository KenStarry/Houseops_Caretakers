package com.example.houseopscaretakers.core.domain.use_cases

import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.repository.CoreRepository

class GetCaretaker(
    private val repo: CoreRepository
) {

    suspend operator fun invoke(): Caretaker? {
        return repo.getCurrentCaretaker()
    }
}