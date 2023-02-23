package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository

class CreateLandlordCollection(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(
        landlord: Landlord,
        response: (response: Response<*>) -> Unit
    ) = repository.addLandlordToCollection(
        landlord = landlord,
        response = { response(it) }
    )
}