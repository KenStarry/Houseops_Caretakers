package com.example.houseopscaretakers.feature_authentication.login.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseopscaretakers.core.domain.model.StateResponse
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val useCase: LoginUseCases,
) : ViewModel() {

    private var response by mutableStateOf(false)

    //  verify login details of user
    fun areDetailsValid(
        email: String,
        password: String
    ): Boolean {

        viewModelScope.launch {

            response = if (email.isBlank() || !email.endsWith("com") || !email.contains(
                    "@",
                    ignoreCase = true
                )
            ) {
                false
            } else password.isNotBlank()
        }

        return response

    }

    //  login user
    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        onLoading: () -> Unit
    ) {

        viewModelScope.launch {

            //  login user and check the state
            when (useCase.loginUser(email, password)) {
                is StateResponse.Loading -> {
                    onLoading()
                }
                is StateResponse.Success -> {
                    onSuccess()
                }
                is StateResponse.Failure -> {
                    onFailure()
                }
                else -> {}
            }
        }

    }

}


































