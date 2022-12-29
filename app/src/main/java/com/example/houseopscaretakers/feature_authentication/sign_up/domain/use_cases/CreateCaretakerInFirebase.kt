package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository

class CreateCaretakerInFirebase(
    private val repo: SignupRepository
) {

    //  create user with email and password
    suspend operator fun invoke(
        email: String,
        password: String
    ): CreateUserResponse = repo.createCaretakerWithEmailAndPass(email, password)

}