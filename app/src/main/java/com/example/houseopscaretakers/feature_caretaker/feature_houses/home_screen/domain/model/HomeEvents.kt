package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model

import com.example.houseopscaretakers.core.domain.model.Response

sealed class HomeEvents {

    object OpenDeleteDialog : HomeEvents()
    object CloseDeleteDialog : HomeEvents()

    data class ToggleAlertDialog(
        val isVisible: Boolean,
        val dialogType: String
    ) : HomeEvents()

    data class GetHouses(val apartmentName: String) : HomeEvents()

    data class GetHouse(val category: String) : HomeEvents()

    data class DeleteHouse(
        val apartmentName: String,
        val houseModel: HouseModel
    ) : HomeEvents()

    data class GetApartments(
        val response: (response: Response<*>) -> Unit
    ) : HomeEvents()
}
