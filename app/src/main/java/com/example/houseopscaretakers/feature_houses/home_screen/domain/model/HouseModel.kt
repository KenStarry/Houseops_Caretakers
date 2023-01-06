package com.example.houseopscaretakers.feature_houses.home_screen.domain.model

data class HouseModel(
    val houseCategory: String,
    val houseImageUris: List<String>,
    val houseUnits: String,
    val houseFeatures: List<String>,
    val houseDescription: String
) {

    constructor() : this ("", emptyList(), "", emptyList(), "")
}
