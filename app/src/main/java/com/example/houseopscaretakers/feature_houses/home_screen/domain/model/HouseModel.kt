package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

data class HouseModel(
    val houseCategory: String,
    val houseImageUris: List<String>,
    val houseUnits: String,
    val houseFeatures: List<String>,
    val houseDescription: String,
    val houseLikes: String,
    val houseApartmentName: String,
    val housePrice: String,
    val houseComments: String
) {

    constructor() : this ("", emptyList(), "", emptyList(), "", "0",
    "Blessing", "23,000", "")
}
