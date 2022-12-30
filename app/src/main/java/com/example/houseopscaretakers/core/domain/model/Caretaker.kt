package com.example.houseopscaretakers.core.domain.model

import android.net.Uri

data class Caretaker(
    val caretakerName: String?,
    val caretakerImage: Uri?,
    val caretakerApartment: String?,
    val caretakerId: String?,
    val caretakerEmail: String?,
    val caretakerPassword: String?
)
