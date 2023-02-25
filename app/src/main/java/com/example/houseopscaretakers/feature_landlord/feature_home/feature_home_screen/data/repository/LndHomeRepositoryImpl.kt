package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.data.repository

import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
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
}