package com.example.houseopscaretakers.core.domain.use_cases

import com.example.houseopscaretakers.core.domain.repository.CoreRepository
import com.google.firebase.auth.FirebaseUser

class CurrentUser(
    private val repo: CoreRepository
) {

    suspend operator fun invoke(): FirebaseUser? {
        return repo.currentUser()
    }
}