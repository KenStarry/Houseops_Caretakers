package com.example.houseopscaretakers.feature_landlord.feature_authentication.login.domain.repository

import com.example.houseopscaretakers.feature_caretaker.core.domain.model.StateResponse

interface LoginRepository {

    //  login user with email and password
    suspend fun loginUser(
        email: String,
        password: String,
        onSuccess: (res: StateResponse?) -> Unit
    )
}