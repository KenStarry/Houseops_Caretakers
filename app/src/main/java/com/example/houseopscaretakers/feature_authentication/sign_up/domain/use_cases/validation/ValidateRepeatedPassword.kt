package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.validation

import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.utils.AuthConstants

class ValidateRepeatedPassword {

    fun execute(
        password: String,
        repeatedPassword: String
    ) : ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_MATCH_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}