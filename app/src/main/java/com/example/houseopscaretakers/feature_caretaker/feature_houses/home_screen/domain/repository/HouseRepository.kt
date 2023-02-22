package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.repository

import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel

interface HouseRepository {

    //  add a house to firestore
    suspend fun addHouseToFirestore(
        apartmentName: String,
        houseModel: HouseModel
    )

    //  get all houses from firestore
    suspend fun getHousesFromFirestore(
        apartmentName: String,
        houseList: (List<HouseModel>) -> Unit
    )

    //  delete house from firestore
    suspend fun deleteHouseFromFirestore(
        apartmentName: String,
        houseModel: HouseModel
    )
}