package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.repository

import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment

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

    suspend fun getApartments(
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}






















