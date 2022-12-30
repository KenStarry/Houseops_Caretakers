package com.example.houseopscaretakers.feature_authentication.login.data.repository

import android.util.Log
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.domain.model.StateResponse
import com.example.houseopscaretakers.feature_authentication.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    override suspend fun loginUser(email: String, password: String): StateResponse? {

        var response: StateResponse? = null

        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    response = StateResponse.Success
                    Log.d("LOGIN", "Login Successful : $response")
                }
                .addOnFailureListener {
                    response = StateResponse.Failure
                    Log.d("LOGIN", "Login Failed : $response")
                }

        } catch (e: Exception) {
            response = StateResponse.Failure
        }

        return response
    }
}


















