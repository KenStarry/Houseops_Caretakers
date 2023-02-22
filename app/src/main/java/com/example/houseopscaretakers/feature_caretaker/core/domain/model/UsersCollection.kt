package com.example.houseopscaretakers.feature_caretaker.core.domain.model

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userLikedHouses: List<LikedHouse>?,
    var userBookmarks: List<String>?,
    var userBookedHouses: List<String>?
) {
    //  empty constructor
    constructor() : this("", "", "",
        "", listOf(), listOf(), listOf()
    )
}
