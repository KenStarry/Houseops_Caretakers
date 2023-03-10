package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.validation

import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.utils.AuthConstants

class ValidateUserName {

    fun execute(
        userName: String
    ) : ValidationResult {

        val containsDigitsOnly = userName.all { char ->
            char.isDigit()
        }

        if (userName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_USERNAME_ERROR
            )
        }

        if (userName.length < 2) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.SHORT_USERNAME_ERROR
            )
        }

        if (containsDigitsOnly) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.DIGITS_ONLY_USERNAME_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}




















