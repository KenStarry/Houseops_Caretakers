package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model

data class UserBooked(
    val userEmail: String,
    val dateBooked: String
) {
    constructor() : this("", "")
}
