package com.example.houseopscaretakers.feature_houses.home_screen.data.repository

import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.repository.HouseRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class HouseRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : HouseRepository {

    override suspend fun addHouseToFirestore(apartmentName: String, houseModel: HouseModel) {

        db.collection(Constants.APARTMENTS_COLLECTION)
            .document(apartmentName).collection(Constants.HOUSES_SUB_COLLECTION)
            .document(houseModel.houseCategory)
            .set(houseModel)
            .addOnCompleteListener { }
            .addOnFailureListener { }

    }

    override suspend fun getHousesFromFirestore(): HouseModel {
        TODO("Not yet implemented")
    }
}