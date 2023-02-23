package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.validation

data class SignUpValidateUseCases(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateUserName: ValidateUserName
)
