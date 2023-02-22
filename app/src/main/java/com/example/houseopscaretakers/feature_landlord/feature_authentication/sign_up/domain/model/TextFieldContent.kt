package com.example.houseopscaretakers.feature_landlord.feature_authentication.sign_up.domain.model

import android.text.InputType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

data class TextFieldContent(
    val placeholder: String,
    val icon: ImageVector,
    val inputType: KeyboardType
)
