package com.example.houseopscaretakers.core.domain.repository

import com.example.houseopscaretakers.core.domain.model.Caretaker

interface CoreRepository {

    //  Check if user is logged in
    suspend fun isUserLoggedIn(): Boolean

    //  return caretaker details
    suspend fun getCurrentCaretaker(): Caretaker?

}