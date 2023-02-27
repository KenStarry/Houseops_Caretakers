package com.example.houseopscaretakers.feature_authentication.login.domain.model

import com.example.houseopscaretakers.core.domain.model.Response

sealed class LoginEvents {

    data class LoginUser(
        val email: String,
        val password: String,
        val response: (res: Response<*>) -> Unit
    ) : LoginEvents()
}
