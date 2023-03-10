package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model

data class HouseModel(
    val houseId: String,
    val houseCategory: String,
    val housePurchaseType: String,
    val houseImageUris: List<String>,
    val houseUnits: String,
    val houseFeatures: List<String>,
    val houseDescription: String,
    val houseLikes: String,
    val houseApartmentName: String,
    val housePrice: String,
    val housePriceCategory: String,
    val houseComments: String,
    val houseUsersBooked: List<UserBooked>
) {

    constructor() : this ("", "", "For Rent", emptyList(), "", emptyList(), "", "0",
    "Blessing", "23,000", "month", "", emptyList()
    )
}
