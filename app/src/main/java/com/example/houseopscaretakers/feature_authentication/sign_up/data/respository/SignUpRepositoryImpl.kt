package com.example.houseopscaretakers.feature_authentication.sign_up.data.respository

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(

    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore

) : SignupRepository {

    //  create our caretaker
    override suspend fun createCaretakerWithEmailAndPass(
        email: String,
        password: String
    ): CreateUserResponse {

        return try {

            auth.createUserWithEmailAndPassword(email, password)
            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    //  add caretaker to collection
    override suspend fun addCaretakerToCollection(caretaker: Caretaker): CreateUserResponse {

        var response = Response.Success(false)

        return try {
            db.collection(Constants.CARETAKER_COLLECTION).document(caretaker.caretakerEmail!!)
                .set(caretaker)
                .addOnSuccessListener {
                    response = Response.Success(true)
                }
                .addOnFailureListener { e ->
                    response = Response.Success(false)
                }

            response

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}


































