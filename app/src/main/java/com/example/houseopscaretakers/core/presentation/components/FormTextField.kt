package com.example.houseopscaretakers.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    placeholder: String? = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    imeAction: ImeAction? = ImeAction.Done,
    inputType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (input: String) -> Unit
) {

    var input by remember {
        mutableStateOf("")
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = leadingIcon ?: Icons.Sharp.Person,
            contentDescription = "Leading Icon",
            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
        )

        TextField(
            value = input,
            onValueChange = {
                input = it
                onValueChange(input)
            },
            trailingIcon = {
                trailingIcon?.let {
                    Icon(imageVector = it, contentDescription = "Trailing Icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = inputType,
                imeAction = imeAction ?: ImeAction.Done
            ),
            placeholder = {
                Text(
                    text = placeholder ?: "Enter Value",
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedIndicatorColor = BlueAccentTransparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1,
            visualTransformation = visualTransformation
        )

    }

}
























