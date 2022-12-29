package com.example.houseopscaretakers.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ConfirmationNumber
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Key
import androidx.compose.material.icons.sharp.Person
import androidx.compose.ui.text.input.KeyboardType
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.TextFieldContent

object Constants {

    //  Splash Title
    const val SPLASH_TITLE = "For Caretakers"

    //  Firestore
    const val CARETAKER_COLLECTION = "caretakers"

    //  Sign Up
    val textFieldsList = listOf(
        TextFieldContent("Username", Icons.Sharp.Person, KeyboardType.Text),
        TextFieldContent("Email", Icons.Sharp.Email, KeyboardType.Email),
        TextFieldContent("ID Number", Icons.Sharp.ConfirmationNumber, KeyboardType.Number),
        TextFieldContent("New Password", Icons.Sharp.Key, KeyboardType.Password),
        TextFieldContent("Confirm Password", Icons.Sharp.Key, KeyboardType.Password),
    )
}