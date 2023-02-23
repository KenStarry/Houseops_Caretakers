package com.example.houseopscaretakers.core.domain.model

import android.net.Uri

data class Landlord(
    val landlordName: String?,
    val landlordImage: String?,
    val landlordEmail: String?,
    val landlordPassword: String?,
    val isLandlordVerified: Boolean
) {
    constructor() : this ("", null, "", "", false)
}
