package com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components

import android.text.InputType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTextField(
    modifier: Modifier = Modifier,
    placeholder: String? = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    inputType: KeyboardType,
    onValueChange: (input: String) -> Unit
) {

    var input by remember {
        mutableStateOf("")
    }

    TextField(
        value = input,
        onValueChange = {
            input = it
            onValueChange(input)
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon ?: Icons.Sharp.Person,
                contentDescription = "Leading Icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
            )
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(imageVector = it, contentDescription = "Trailing Icon")
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType
        ),
        placeholder = {
            Text(
                text = placeholder ?: "Enter Value",
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = BlueAccentTransparent,
            unfocusedIndicatorColor = BlueAccentTransparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
        maxLines = 1
    )

}
























