package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.domain.repository

import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response

interface LndHomeRepository {

    suspend fun getLandlordDetails(
        email: String,
        landlord: (landlord: Landlord) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}