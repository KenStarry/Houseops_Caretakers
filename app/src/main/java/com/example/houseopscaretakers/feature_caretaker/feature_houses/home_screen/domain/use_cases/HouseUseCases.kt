package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.use_cases

data class HouseUseCases(
    val addHouse: AddHouse,
    val getHouses: GetHouses,
    val deleteHouse: DeleteHouse,
    val getApartments: GetApartments
)
