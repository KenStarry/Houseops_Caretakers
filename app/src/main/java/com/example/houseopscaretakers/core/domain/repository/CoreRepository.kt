package com.example.houseopscaretakers.core.domain.repository

import com.example.houseopscaretakers.core.domain.model.Caretaker
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

}