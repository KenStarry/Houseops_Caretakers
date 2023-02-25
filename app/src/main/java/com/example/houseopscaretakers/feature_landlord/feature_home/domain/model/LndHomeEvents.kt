package com.example.houseopscaretakers.feature_landlord.feature_home.domain.model

import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response

sealed class LndHomeEvents {

    data class GetLandlordDetails(
        val email: String
    ) : LndHomeEvents()

    data class FilterGreetingsText(
        val time: Int,
        val greetings: (greeting: String) -> Unit
    )
}
