package com.example.houseopscaretakers.feature_caretaker.core.domain.model

import android.net.Uri

data class Caretaker(
    val caretakerName: String?,
    val caretakerImage: String?,
    val caretakerApartment: String?,
    val caretakerId: String?,
    val caretakerEmail: String?,
    val caretakerPassword: String?
) {
    constructor() : this ("", null, "", "", "", "")
}
