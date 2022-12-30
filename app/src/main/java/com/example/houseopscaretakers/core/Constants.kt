package com.example.houseopscaretakers.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.ui.text.input.KeyboardType
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.TextFieldContent

object Constants {

    //  Splash Title
    const val SPLASH_TITLE = "For Caretakers"

    //  Firestore
    const val CARETAKER_COLLECTION = "caretakers"

    //  Firebase storage
    const val CARETAKER_IMAGES = "caretaker_images"

    //  Sign Up
    val textFieldsList = listOf(
        TextFieldContent("Username", Icons.Sharp.Person, KeyboardType.Text),
        TextFieldContent("Email Address", Icons.Sharp.AlternateEmail, KeyboardType.Email),
        TextFieldContent("ID Number", Icons.Sharp.ConfirmationNumber, KeyboardType.Number),
        TextFieldContent("New Password", Icons.Sharp.Key, KeyboardType.Password),
        TextFieldContent("Confirm Password", Icons.Sharp.Key, KeyboardType.Password),
        TextFieldContent("Apartment Name", Icons.Sharp.Apartment, KeyboardType.Text)
    )

    const val USERNAME_ERROR = "Check Username"
    const val EMAIL_ERROR = "Check Email Address"
    const val APARTMENT_ERROR = "Check Apartment Name"
    const val ID_ERROR = "Check User ID"
    const val PASSWORD_ERROR = "Passwords don't match"
    const val AUTH_SUCCESSFUL = "Account Created Successfully"
}