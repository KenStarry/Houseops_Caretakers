package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.repository

import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment

interface LndAddApartmentRepository {

    suspend fun addApartmentToFirestore(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    )
}