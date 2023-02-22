package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.use_cases

import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.repository.HouseRepository

class AddHouse (
    private val repo: HouseRepository
) {

    suspend operator fun invoke(
        apartmentName: String,
        houseModel: HouseModel
    ) {
        repo.addHouseToFirestore(
            apartmentName, houseModel
        )
    }

}