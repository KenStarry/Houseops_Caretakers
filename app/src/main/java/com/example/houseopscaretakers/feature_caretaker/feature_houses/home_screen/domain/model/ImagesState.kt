package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model

import android.net.Uri

data class ImagesState(
    val listOfSelectedImages: List<Uri> = emptyList()
)
