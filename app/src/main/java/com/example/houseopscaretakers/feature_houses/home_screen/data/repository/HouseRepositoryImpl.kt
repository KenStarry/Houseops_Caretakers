package com.example.houseopscaretakers.feature_houses.home_screen.data.repository

import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.repository.HouseRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class HouseRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : HouseRepository {

    override suspend fun addHouseToFirestore(apartmentName: String, houseModel: HouseModel) {

        db.collection(Constants.APARTMENTS_COLLECTION)
            .document(apartmentName).collection(Constants.HOUSES_SUB_COLLECTION)
            .document(houseModel.houseCategory)
            .set(houseModel)
            .addOnSuccessListener {

            }
            .addOnFailureListener { }

    }

    override suspend fun getHousesFromFirestore(
        apartmentName: String,
        houseList: (List<HouseModel>) -> Unit
    ) {

        db.collection(Constants.APARTMENTS_COLLECTION).document(apartmentName)
            .collection(Constants.HOUSES_SUB_COLLECTION)
            .addSnapshotListener { querySnapshot, error ->

                if (error != null)
                    return@addSnapshotListener

                val houses = ArrayList<HouseModel>()

                querySnapshot?.let {
                    for (snapshot in it) {
                        val house = snapshot.toObject(HouseModel::class.java)
                        houses.add(house)
                    }
                }

                houseList(houses)
            }
    }
}





















