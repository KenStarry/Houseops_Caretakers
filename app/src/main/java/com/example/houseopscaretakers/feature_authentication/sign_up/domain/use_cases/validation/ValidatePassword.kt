package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.validation

import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationResult
import com.example.houseopscaretakers.feature_authentication.utils.AuthConstants

class ValidatePassword {

    fun execute(
        password: String
    ): ValidationResult {

        val passwordContainsLetters = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_PASSWORD_ERROR
            )
        }

        if (password.length < AuthConstants.PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_LENGTH_ERROR
            )
        }

        if (!passwordContainsLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_LETTERS_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}