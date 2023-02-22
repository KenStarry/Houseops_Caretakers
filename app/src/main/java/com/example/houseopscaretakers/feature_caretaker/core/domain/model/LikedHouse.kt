package com.example.houseopscaretakers.feature_caretaker.core.domain.model

data class LikedHouse(
    val apartmentName: String,
    val houseCategory: String
) {
    constructor() : this("", "")
}
