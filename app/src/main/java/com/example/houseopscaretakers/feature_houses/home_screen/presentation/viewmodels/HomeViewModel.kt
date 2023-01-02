package com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel

class HomeViewModel() : ViewModel() {

    //  validate if the apartment name has 'apartments' ahead of it
    fun addApartmentSuffix(
        apartmentName: String
    ): String {

        //  list of split names
        val splitName = apartmentName.lowercase().split(" ")

        val newName = if (splitName[splitName.lastIndex] != "apartment" ||
            splitName[splitName.lastIndex] != "apartments"
        ) {
            "$apartmentName Apartments"
        } else {
            apartmentName
        }

        return newName

    }
}