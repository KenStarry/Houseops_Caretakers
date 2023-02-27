package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.use_cases

import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.repository.HouseRepository
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment

class GetApartments(
    private val repository: HouseRepository
) {
    suspend operator fun invoke(
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getApartments(
        apartments = { apartments(it) },
        response = { response(it) }
    )
}