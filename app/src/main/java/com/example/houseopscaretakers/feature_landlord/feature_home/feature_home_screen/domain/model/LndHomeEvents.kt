package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response

sealed class LndHomeEvents {

    data class GetLandlordDetails(
        val email: String
    ) : LndHomeEvents()

    data class FilterGreetingsText(
        val currentHour: Int,
        val greetings: (
            greetingText: String,
            greetingIcon: ImageVector
        ) -> Unit,
    ) : LndHomeEvents()
}
