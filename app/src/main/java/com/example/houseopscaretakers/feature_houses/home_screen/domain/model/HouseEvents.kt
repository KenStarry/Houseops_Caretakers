package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

sealed class HouseEvents {

    data class GetHouses(val apartmentName: String) : HouseEvents()
}
