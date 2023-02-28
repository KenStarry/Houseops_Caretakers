package com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.components.CustomTextField
import com.example.houseopscaretakers.core.presentation.components.FormTextField
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.ErrorMessage
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel

@Composable
fun SignUpForms(
    signUpVM: SignUpViewModel = hiltViewModel(),
    onUserNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  User name
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                textFieldValue = signUpVM.formState.username,
                startIcon = Constants.textFieldsList[0].icon,
                endIcon = null,
                placeholder = Constants.textFieldsList[0].placeholder,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                onInput = { onUserNameChanged(it) }
            )

            AnimatedVisibility(visible = signUpVM.formState.usernameError != null) {
                ErrorMessage(message = signUpVM.formState.usernameError)
            }
        }


        //  Email Address
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                textFieldValue = signUpVM.formState.email,
                startIcon = Constants.textFieldsList[1].icon,
                endIcon = null,
                placeholder = Constants.textFieldsList[1].placeholder,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                onInput = { onEmailChanged(it) }
            )

            AnimatedVisibility(visible = signUpVM.formState.emailError != null) {
                ErrorMessage(message = signUpVM.formState.emailError)
            }
        }

        //  New Password
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                textFieldValue = signUpVM.formState.password,
                startIcon = Constants.textFieldsList[3].icon,
                endIcon = null,
                placeholder = Constants.textFieldsList[3].placeholder,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                isPassword = true,
                onInput = { onPasswordChanged(it) }
            )

            AnimatedVisibility(visible = signUpVM.formState.passwordError != null) {
                ErrorMessage(message = signUpVM.formState.passwordError)
            }
        }


        //  confirm Pass
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                textFieldValue = signUpVM.formState.repeatedPassword,
                startIcon = Constants.textFieldsList[4].icon,
                endIcon = null,
                placeholder = Constants.textFieldsList[4].placeholder,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                isPassword = true,
                onInput = { onRepeatPasswordChanged(it) }
            )

            AnimatedVisibility(visible = signUpVM.formState.repeatedPasswordError != null) {
                ErrorMessage(message = signUpVM.formState.repeatedPasswordError)
            }
        }

    }
}