package com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases

import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.repository.HouseRepository

class DeleteHouse(
    val repository: HouseRepository
) {

    suspend operator fun invoke(
        apartmentName: String,
        houseModel: HouseModel
    ) = repository.deleteHouseFromFirestore(apartmentName, houseModel)
}