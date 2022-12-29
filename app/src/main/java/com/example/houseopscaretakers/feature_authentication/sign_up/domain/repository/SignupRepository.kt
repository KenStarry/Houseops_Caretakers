package com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository

import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Response

typealias CreateUserResponse = Response<Boolean>

interface SignupRepository {

    //  add user to authentication api in Firebase
    suspend fun createCaretakerWithEmailAndPass(email: String, password: String): CreateUserResponse

    //  create user collection
    suspend fun addCaretakerToCollection(caretaker: Caretaker): CreateUserResponse
}