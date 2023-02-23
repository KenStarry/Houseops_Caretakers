package com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation

import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.utils.AuthConstants

class LoginValidatePassword {

    fun execute(password: String): ValidationResult {

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_PASSWORD_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}