package com.example.houseopscaretakers.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.ui.text.input.KeyboardType
import com.example.houseopscaretakers.core.domain.model.PillBtnModel
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.TextFieldContent
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.model.StatsCardModel
import com.example.houseopscaretakers.ui.theme.*

object Constants {

    //  Routes
    const val AUTHENTICATION_ROUTE = "authentication"
    const val HOME_ROUTE = "home"
    const val ROOT_ROUTE = "root"
    const val ROUTE = "route"
    //  screen routes
    const val HOME_SCREEN_ROUTE = "home_screen"
    const val HOUSE_VIEW_SCREEN_ROUTE = "house_view_screen"
    const val LOGIN_SCREEN_ROUTE = "login_screen"
    const val SIGN_UP_SCREEN_ROUTE = "sign_up_screen"

    //  ------------------  LOGIN SCREEN    ----------------
    const val LOGIN_TITLE = "Login"

    //  Splash Title
    const val SPLASH_TITLE = "For Caretakers"

    //  Firestore
    const val CARETAKER_COLLECTION = "caretakers"
    const val APARTMENTS_COLLECTION = "apartments"
    const val HOUSES_SUB_COLLECTION = "houses"

    //  Firebase storage
    const val CARETAKER_IMAGES = "caretaker_images"
    const val HOUSE_IMAGES = "house_images"

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



    //  ------------------  HOME SCREEN    ----------------
    val houseCategories = listOf(
        PillBtnModel("One Bedroom", Icons.Outlined.Apartment),
        PillBtnModel("Two Bedroom", Icons.Outlined.Hotel),
        PillBtnModel("Bedsitter", Icons.Outlined.LocalCafe),
        PillBtnModel("Single", Icons.Outlined.LocalHotel),
    )

    val statsCardList = listOf(
        StatsCardModel(Icons.Outlined.Apartment, RedOrange, "10", "Users Booked"),
        StatsCardModel(Icons.Outlined.ThumbUp, BabyBlue, "0", "Liked Houses"),
        StatsCardModel(Icons.Outlined.Comment, BlueAccent, "10", "Comments"),
        StatsCardModel(Icons.Outlined.Pending, LightGreen, "10", "Pending Approval")
    )
}












