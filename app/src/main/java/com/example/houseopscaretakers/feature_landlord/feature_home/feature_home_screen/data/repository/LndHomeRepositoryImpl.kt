package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.data.repository

import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.domain.repository.LndHomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LndHomeRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : LndHomeRepository {

    override suspend fun getLandlordDetails(
        email: String,
        landlord: (landlord: Landlord) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {

        try {
            db.collection(Constants.LANDLORD_COLLECTION)
                .document(email)
                .addSnapshotListener { snaphot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    snaphot?.toObject(Landlord::class.java)?.let {
                        landlord(it)

                        response(Response.Success(it))
                    }
                }
        } catch (e: Exception) {

            response(Response.Failure(e))
        }
    }

    override suspend fun getLandlordApartments(
        email: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .whereEqualTo("apartmentLandlordEmail", email)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    val apartmentsList = ArrayList<Apartment>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot.toObject(Apartment::class.java).let { apartment ->
                            apartmentsList.add(apartment)
                        }
                    }

                    apartments(apartmentsList)
                    response(Response.Success(true))
                }

        } catch (e: Exception) {
            response(Response.Failure(e))
        }
    }
}