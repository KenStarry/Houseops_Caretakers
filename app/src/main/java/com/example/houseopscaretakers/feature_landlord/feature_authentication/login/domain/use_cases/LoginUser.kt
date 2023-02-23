package com.example.houseopscaretakers.feature_landlord.feature_authentication.login.domain.use_cases

import android.util.Log
import com.example.houseopscaretakers.core.domain.model.StateResponse
import com.example.houseopscaretakers.feature_landlord.feature_authentication.login.domain.repository.LoginRepository

class LoginUser(
    private val repo: LoginRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        onSuccess: (res: StateResponse?) -> Unit,
    ) {
        repo.loginUser(
            email,
            password,
            onSuccess = {
                onSuccess(it)
                Log.d("LOGIN_REPO", it.toString())
            }
        )
    }

}