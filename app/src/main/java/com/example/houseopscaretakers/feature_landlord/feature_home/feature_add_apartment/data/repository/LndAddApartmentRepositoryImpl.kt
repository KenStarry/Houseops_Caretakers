package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.data.repository

import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.repository.LndAddApartmentRepository
import com.google.firebase.firestore.FirebaseFirestore

class LndAddApartmentRepositoryImpl(
    private val db: FirebaseFirestore
) : LndAddApartmentRepository {

    override suspend fun addApartmentToFirestore(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    ) {

        try {

            val documentRef = db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartment.apartmentName)

            documentRef.set(apartment)
                .addOnSuccessListener { response(Response.Success(true)) }
                .addOnFailureListener { response(Response.Failure(it)) }

        } catch (e: Exception) {
            response(Response.Failure(e))
        }
    }
}