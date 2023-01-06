package com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases

import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.repository.HouseRepository

class GetHouses(
    private val repository: HouseRepository
) {

    suspend operator fun invoke(
        apartmentName: String,
        houseList: (List<HouseModel>) -> Unit
    ) {
        repository.getHousesFromFirestore(
            apartmentName, houseList
        )
    }
}