package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.use_case

import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.repository.LndAddApartmentRepository

class AddApartment(
    private val repository: LndAddApartmentRepository
) {

    suspend operator fun invoke(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    ) = repository.addApartmentToFirestore(
        apartment = apartment,
        response = { response(it) }
    )
}