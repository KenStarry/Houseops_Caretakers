package com.example.houseopscaretakers.feature_landlord.core.model

import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.ApartmentFeature
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.PlacesAPIResult

data class Apartment(
    val apartmentName: String,
    val apartmentLocation: PlacesAPIResult,
    val apartmentCaretakerId: String,
    val apartmentFeatures: List<ApartmentFeature>
)
