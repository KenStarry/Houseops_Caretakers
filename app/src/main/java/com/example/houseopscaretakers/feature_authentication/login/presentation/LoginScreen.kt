package com.example.houseopscaretakers.feature_authentication.login.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.components.HomePillBtns
import com.example.houseopscaretakers.core.presentation.components.HyperLinkText
import com.example.houseopscaretakers.core.presentation.model.RoutePath
import com.example.houseopscaretakers.core.presentation.utils.UtilsViewModel
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.LoginButtons
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.LoginSVG
import com.example.houseopscaretakers.feature_authentication.login.presentation.components.LoginTextFields
import com.example.houseopscaretakers.feature_authentication.login.presentation.viewmodel.LoginViewModel
import com.example.houseopscaretakers.navigation.Direction

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    val context = LocalContext.current
    val direction = Direction(navHostController)

    val vm: UtilsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val coreVM: CoreViewModel = hiltViewModel()

    //  get the current user type
    val userType = coreVM.userTypeFlow.collectAsState(initial = Constants.routePaths[0].title).value

    LaunchedEffect(key1 = Unit) {
        vm.isShowing.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  SVG Image
        LoginSVG(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        //  login text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = Constants.LOGIN_TITLE,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Icon(
                imageVector = Icons.Outlined.ArrowRight,
                contentDescription = "Arrow right",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            //  user type
            userType?.let { user ->

                val routePath: RoutePath = Constants.routePaths.filter { it.title == user }[0]

                HomePillBtns(
                    icon = routePath.icon,
                    title = routePath.title,
                    primaryColor = MaterialTheme.colorScheme.primary,
                    tertiaryColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {}
                )
            }

        }

        //  Login text fields
        LoginTextFields(
            onEmailInput = { emailInput = it },
            onPasswordInput = { passwordInput = it },
            onForgotPassword = {},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
                            Log.d("login", "success")
                            //  navigate and pop
                            direction.navigateAndPopRoute(
                                com.example.houseopscaretakers.core.Constants.HOME_ROUTE,
                                com.example.houseopscaretakers.core.Constants.AUTHENTICATION_ROUTE
                            )
                        },
                        onFailure = {
                            Toast.makeText(context, "Oops, couldn't log you in", Toast.LENGTH_SHORT)
                                .show()
                        },
                        onLoading = {
                            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                        }
                    )
                } else {
                    Toast.makeText(context, "Please fill in all details", Toast.LENGTH_SHORT).show()
                }
            },
            onLoginWithGoogle = {
                //  login with Google
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        //  Our hyperlink text
        HyperLinkText(
            direction = direction,
            fullText = "Don't have an account? create one",
            linkText = listOf("create one"),
            hyperlinks = listOf(com.example.houseopscaretakers.core.Constants.SIGN_UP_SCREEN_ROUTE),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )

    }

}


























