package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

sealed class HouseEvents {

    data class GetHouses(val apartmentName: String) : HouseEvents()

    data class GetHouse(val category: String) : HouseEvents()

    data class DeleteHouse(
        val apartmentName: String,
        val houseModel: HouseModel
    ) : HouseEvents()
}
