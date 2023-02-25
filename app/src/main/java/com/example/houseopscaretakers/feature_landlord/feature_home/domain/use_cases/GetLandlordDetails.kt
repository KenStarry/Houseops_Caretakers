package com.example.houseopscaretakers.feature_landlord.feature_home.domain.use_cases

import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_landlord.feature_home.domain.repository.LndHomeRepository

class GetLandlordDetails(
    private val repository: LndHomeRepository
) {
    suspend operator fun invoke(
        email: String,
        landlord: (landlord: Landlord) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getLandlordDetails(
        email = email,
        landlord = { landlord(it) },
        response = { response(it) }
    )
}