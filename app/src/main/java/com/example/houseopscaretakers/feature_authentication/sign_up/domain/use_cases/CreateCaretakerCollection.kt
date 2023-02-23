package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository

class CreateCaretakerCollection(
    private val repo: SignupRepository
) {

    suspend operator fun invoke(
        caretaker: Caretaker
    ): CreateUserResponse = repo.addCaretakerToCollection(caretaker)

}