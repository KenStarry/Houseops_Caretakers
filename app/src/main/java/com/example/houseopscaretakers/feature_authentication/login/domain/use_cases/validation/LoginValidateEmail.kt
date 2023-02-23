package com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation

import android.util.Patterns
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.utils.AuthConstants

class LoginValidateEmail {

    fun execute(email: String): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_EMAIL_ERROR
            )
        }

        //  check if email is a valid email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}