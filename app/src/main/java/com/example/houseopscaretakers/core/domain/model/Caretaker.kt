package com.example.houseopscaretakers.core.domain.model

import android.net.Uri

data class Caretaker(
    val caretakerName: String?,
    val caretakerImage: String?,
    val caretakerEmail: String?,
    val caretakerPassword: String?,
    val isCaretakerVerified: Boolean
) {
    constructor() : this ("", null, "", "", false)
}
