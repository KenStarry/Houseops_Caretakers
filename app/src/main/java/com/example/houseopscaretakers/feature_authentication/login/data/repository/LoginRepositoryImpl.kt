package com.example.houseopscaretakers.feature_authentication.login.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.domain.model.StateResponse
import com.example.houseopscaretakers.feature_authentication.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
        response: (res: Response<*>) -> Unit
    ) {
        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    response(Response.Success(true))
                }
                .addOnFailureListener {
                    response(Response.Failure(it))
                }

        } catch (e: Exception) {
            response(Response.Failure(e))
        }
    }
}


















