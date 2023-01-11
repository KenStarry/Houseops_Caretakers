package com.example.houseopscaretakers.core.domain.repository

import android.content.Context
import android.net.Uri
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.google.firebase.auth.FirebaseUser

interface CoreRepository {

    //  Return current user
    suspend fun currentUser(): FirebaseUser?

    //  Check if user is logged in
    suspend fun isUserLoggedIn(): Boolean

    //  return caretaker details
    suspend fun getCurrentCaretaker(
        email: String,
        currentCaretaker: (caretaker: Caretaker) -> Unit
    )

    //  upload images to firestore
    suspend fun uploadImagesToStorage(
        imageUriList: List<Uri>,
        context: Context,
        houseModel: HouseModel,
        apartmentName: String
    )

    suspend fun getCurrentHouse(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    )

}









