package com.example.houseopscaretakers.feature_caretaker.core.domain.use_cases

import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.CoreRepository
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel

class GetHouse(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    ) {

        repository.getCurrentHouse(
            category, apartmentName
        ) {
            currentHouse(it)
        }
    }
}