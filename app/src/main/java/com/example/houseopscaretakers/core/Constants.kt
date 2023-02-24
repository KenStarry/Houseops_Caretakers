package com.example.houseopscaretakers.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardType
import com.example.houseopscaretakers.core.domain.model.PillBtnModel
import com.example.houseopscaretakers.core.presentation.model.AccentColor
import com.example.houseopscaretakers.core.presentation.model.RoutePath
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.TextFieldContent
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.model.StatsCardModel
import com.example.houseopscaretakers.ui.theme.BabyBlue
import com.example.houseopscaretakers.ui.theme.BlueAccent
import com.example.houseopscaretakers.ui.theme.RedOrange
import com.example.houseopscaretakers.ui.theme.Violet

object Constants {

    //  route paths
    val routePaths = listOf(
        RoutePath("Landlord", Icons.Outlined.Person),
        RoutePath("Caretaker", Icons.Outlined.AdminPanelSettings),
    )

    //  Routes
    const val AUTHENTICATION_ROUTE = "authentication"
    const val HOME_ROUTE = "home"
    const val ROOT_ROUTE = "root"
    const val ROUTE = "route"
    const val LANDLORD_ROUTE = "landlord"
    const val LOADING_ROUTE = "loading"

    //  screen routes
    const val MAIN_SCREEN_ROUTE = "main_screen"
    const val HOME_SCREEN_ROUTE = "home_screen"
    const val HOUSE_VIEW_SCREEN_ROUTE = "house_view_screen"
    const val LOGIN_SCREEN_ROUTE = "login_screen"
    const val SIGN_UP_SCREEN_ROUTE = "sign_up_screen"
    const val HOUSE_ADD_SCREEN_ROUTE = "house_add_screen"
    const val PATH_SCREEN_ROUTE = "path_screen"
    const val LOADING_SCREEN_ROUTE = "loading_screen"

    //  ------------------  LOGIN SCREEN    ----------------
    const val LOGIN_TITLE = "Login"

    //  Splash Title
    const val SPLASH_TITLE = "For Caretakers"

    //  Firestore
    const val CARETAKER_COLLECTION = "caretakers"
    const val LANDLORD_COLLECTION = "landlords"
    const val APARTMENTS_COLLECTION = "apartments"
    const val HOUSES_SUB_COLLECTION = "houses"

    //  Firebase storage
    const val CARETAKER_IMAGES = "caretaker_images"
    const val LANDLORD_IMAGES = "caretaker_images"
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

    val priceCategories = listOf(
        "month",
        "year",
        "quarter",
        "6 months",
    )

    val statsCardList = listOf(
        StatsCardModel(Icons.Outlined.Apartment, RedOrange, "10", "Users Booked"),
        StatsCardModel(Icons.Outlined.ThumbUp, BabyBlue, "0", "Likes"),
        StatsCardModel(Icons.Outlined.Comment, BlueAccent, "10", "Comments"),
        StatsCardModel(Icons.Outlined.Pending, Violet, "10", "Pending Issues")
    )

    //  colors
    val primaryCol = mutableStateOf(Color(0xFF3DB2EC))
    val tertiaryCol = mutableStateOf(Color(0xFF3DB2EC).copy(alpha = 0.1f))

    val accentColors = listOf(
        AccentColor(
            darkColor = Color(0xFF3DB2EC).toArgb(),
            lightColor = Color(0xFF3DB2EC).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF2AABEB).toArgb(),
            lightColor = Color(0xFF2AABEB).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF11A0E7).toArgb(),
            lightColor = Color(0xFF11A0E7).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF00A3F3).toArgb(),
            lightColor = Color(0xFF00A3F3).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF4309).toArgb(),
            lightColor = Color(0xFFFF4309).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF5825).toArgb(),
            lightColor = Color(0xFFFF5825).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF6C40).toArgb(),
            lightColor = Color(0xFFFF6C40).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF8C69).toArgb(),
            lightColor = Color(0xFFFF8C69).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF288B0D).toArgb(),
            lightColor = Color(0xFF288B0D).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF3ED515).toArgb(),
            lightColor = Color(0xFF3ED515).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF54CF32).toArgb(),
            lightColor = Color(0xFF54CF32).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF74D15B).toArgb(),
            lightColor = Color(0xFF74D15B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF0A5B).toArgb(),
            lightColor = Color(0xFFFF0A5B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF3A7B).toArgb(),
            lightColor = Color(0xFFFF3A7B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF5990).toArgb(),
            lightColor = Color(0xFFFF5990).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF80AA).toArgb(),
            lightColor = Color(0xFFFF80AA).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF012BFF).toArgb(),
            lightColor = Color(0xFF012BFF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF2247FF).toArgb(),
            lightColor = Color(0xFF2247FF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF4A68FF).toArgb(),
            lightColor = Color(0xFF4A68FF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF7088FF).toArgb(),
            lightColor = Color(0xFF7088FF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDDEE0B).toArgb(),
            lightColor = Color(0xFFDDEE0B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDEEC34).toArgb(),
            lightColor = Color(0xFFDEEC34).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDDE952).toArgb(),
            lightColor = Color(0xFFDDE952).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDDE676).toArgb(),
            lightColor = Color(0xFFDDE676).copy(alpha = 0.1f).toArgb()
        ),
    )
}












