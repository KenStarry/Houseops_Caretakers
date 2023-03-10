package com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository

import android.content.Context
import android.net.Uri
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response

typealias CreateUserResponse = Response<Boolean>

interface SignupRepository {

    //  add user to authentication api in Firebase
    suspend fun createUserWithEmailAndPass(
        email: String,
        password: String,
        response: (response: Response<*>) -> Unit
    )

    //  create user collection
    suspend fun addCaretakerToCollection(
        caretaker: Caretaker,
        response: (response: Response<*>) -> Unit
    )

    suspend fun addLandlordToCollection(
        landlord: Landlord,
        response: (response: Response<*>) -> Unit
    )

    //  add caretaker image to firebase storage
    suspend fun uploadCaretakerImageToCloudStorage(
        caretaker: Caretaker,
        imageUri: Uri?,
        context: Context,
        response: (response: Response<*>) -> Unit
    )

    //  add landlord image to firebase storage
    suspend fun uploadLandlordImageToCloudStorage(
        landlord: Landlord,
        imageUri: Uri?,
        context: Context,
        response: (response: Response<*>) -> Unit
    )
}