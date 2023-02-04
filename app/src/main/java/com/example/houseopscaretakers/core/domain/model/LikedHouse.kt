package com.example.houseopscaretakers.core.domain.model

data class LikedHouse(
    val apartmentName: String,
    val houseCategory: String
) {
    constructor() : this("", "")
}
