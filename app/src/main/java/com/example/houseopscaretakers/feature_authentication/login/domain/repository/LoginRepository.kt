package com.example.houseopscaretakers.feature_authentication.login.domain.repository

import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.domain.model.StateResponse

interface LoginRepository {

    //  login user with email and password
    suspend fun loginUser(
        email: String,
        password: String,
        response: (res: Response<*>) -> Unit
    )
}