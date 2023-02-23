package com.example.houseopscaretakers.feature_authentication.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.sharp.AlternateEmail
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.CustomTextField
import com.example.houseopscaretakers.core.presentation.components.FormTextField

@Composable
fun LoginTextFields(
    modifier: Modifier = Modifier,
    onEmailInput: (input: String) -> Unit,
    onPasswordInput: (input: String) -> Unit,
    onForgotPassword: () -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        //  email address
        CustomTextField(
            startIcon = Icons.Outlined.AlternateEmail,
            endIcon = null,
            placeholder = "Email Address",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            onInput = { onEmailInput(it) }
        )

        //  password
        CustomTextField(
            startIcon = Icons.Outlined.Key,
            endIcon = null,
            placeholder = "Password",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            onInput = { onPasswordInput(it) }
        )

        //  Row for the forgot password text
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Text(
                text = "Forgot password?",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { onForgotPassword() }
            )

        }

    }
}






















