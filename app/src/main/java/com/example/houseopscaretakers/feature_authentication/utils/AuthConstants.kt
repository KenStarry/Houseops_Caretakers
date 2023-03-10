package com.example.houseopscaretakers.feature_authentication.utils

object AuthConstants {

    //  email validation
    const val BLANK_EMAIL_ERROR = "Email cannnot be blank"
    const val INVALID_EMAIL_ERROR = "That's not a valid email"

    //  password validation
    const val PASSWORD_LENGTH = 8

    const val PASSWORD_LENGTH_ERROR =
        "The password needs to contain at least $PASSWORD_LENGTH characters"

    const val PASSWORD_LETTERS_ERROR =
        "The password needs to contain at least one letter and digit"

    const val PASSWORD_MATCH_ERROR = "The passwords don't match"

    const val BLANK_PASSWORD_ERROR = "Password cannot be blank"

    //  username validation
    const val BLANK_USERNAME_ERROR = "Username cannot be blank."
    const val SHORT_USERNAME_ERROR = "Username is too short."
    const val DIGITS_ONLY_USERNAME_ERROR = "Username must have at least one alphabet."

}