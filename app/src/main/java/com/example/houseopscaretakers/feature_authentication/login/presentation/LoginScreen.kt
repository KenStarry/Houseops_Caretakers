package com.example.houseopscaretakers.feature_authentication.login.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.LoginButtons
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.LoginSVG
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.LoginTextFields
import com.example.houseopscaretakers.feature_authentication.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  SVG Image
        LoginSVG(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )

        //  Sign in text
        Text(
            text = "Sign In",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        //  Login text fields
        LoginTextFields(
            onEmailInput = { emailInput = it },
            onPasswordInput = { passwordInput = it },
            onForgotPassword = {},
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )

        //  Login buttons
        LoginButtons(
            onLoginWithEmail = {
                //   verify details are valid
                if (viewModel.areDetailsValid(emailInput, passwordInput)) {

                    //  login user
                    viewModel.loginUser(
                        emailInput,
                        passwordInput,
                        onSuccess = {
                            Toast.makeText(context, "Welcome Back User!", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = {
                            Toast.makeText(context, "Oops, couldn't log you in", Toast.LENGTH_SHORT).show()
                        },
                        onLoading = {
                            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            },
            onLoginWithGoogle = {},
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )

    }

}


























