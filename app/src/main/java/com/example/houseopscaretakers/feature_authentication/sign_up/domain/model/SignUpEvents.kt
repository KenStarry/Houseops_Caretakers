package com.example.houseopscaretakers.feature_authentication.sign_up.domain.model

import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response

sealed class SignUpEvents {

    data class CreateUserInFirebase(
        val email: String,
        val password: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : SignUpEvents()

    data class CreateLandlordCollection(
        val landlord: Landlord,
        val response: (response: Response<*>) -> Unit
    ) : SignUpEvents()

    data class CreateCaretakerCollection(
        val caretaker: Caretaker,
        val response: (response: Response<*>) -> Unit
    ) : SignUpEvents()
}
