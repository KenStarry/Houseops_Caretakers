package com.example.houseopscaretakers.feature_landlord.feature_authentication.login.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.houseopscaretakers.feature_caretaker.core.domain.model.StateResponse
import com.example.houseopscaretakers.feature_landlord.feature_authentication.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
        onSuccess: (res: StateResponse?) -> Unit
    ) {

        var response = mutableStateOf<StateResponse?>(null)

        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    response.value = StateResponse.Success
                    onSuccess(response.value)
                }
                .addOnFailureListener {
                    response.value = StateResponse.Failure
                    Log.d("LOGIN", "Login Failed : ${response.value}")
                }

        } catch (e: Exception) {
            response.value = StateResponse.Failure
        }
    }
}


















