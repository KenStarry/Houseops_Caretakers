package com.example.houseopscaretakers.feature_caretaker.core.domain.use_cases

import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.CoreRepository
import com.google.firebase.auth.FirebaseUser

class CurrentUser(
    private val repo: CoreRepository
) {

    suspend operator fun invoke(): FirebaseUser? {
        return repo.currentUser()
    }
}