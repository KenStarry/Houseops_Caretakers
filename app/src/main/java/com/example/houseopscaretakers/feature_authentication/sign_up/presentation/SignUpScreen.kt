package com.example.houseopscaretakers.feature_authentication.sign_up.presentation

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.Constants.textFieldsList
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.FormTextField
import com.example.houseopscaretakers.core.presentation.utils.getSingleImageFromGallery
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationEvent
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.SignUpFormEvent
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.SignUpAppBar
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.SignUpForms
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseopscaretakers.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signUpVM: SignUpViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val context = LocalContext.current
    val direction = Direction(navHostController)
    val coreVM: CoreViewModel = hiltViewModel()

    //  get the current user type
    val userType = coreVM.userTypeFlow.collectAsState(initial = Constants.routePaths[0].title).value

    var caretakerImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = getSingleImageFromGallery(onResult = {
        caretakerImageUri = it
    })

    LaunchedEffect(key1 = context) {
        signUpVM.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
//                    val verificationResponse = signUpVM.verifyCaretakerDetails(
//                        userName = usernameInput,
//                        id = idInput,
//                        apartment = apartmentInput,
//                        email = emailInput,
//                        newPassword = newPassInput,
//                        confirmPassword = confirmPassInput
//                    )
//                    val caretaker = Caretaker(
//                        caretakerEmail = emailInput,
//                        caretakerName = usernameInput,
//                        caretakerImage = caretakerImageUri.toString(),
//                        caretakerApartment = apartmentInput,
//                        caretakerId = idInput,
//                        caretakerPassword = newPassInput
//                    )
//
//                    //  verify user details and create user
//                    if (verificationResponse == com.example.houseopscaretakers.core.Constants.AUTH_SUCCESSFUL) {
//
//                        //  create user account
//                        signUpVM.createCaretakerWithEmailAndPassword(
//                            email = emailInput,
//                            password = newPassInput,
//                            onSuccess = {
//
//                                //  create caretaker collection
//                                signUpVM.createCaretakerCollection(
//                                    caretaker = caretaker,
//                                    onSuccess = {
//                                        //  store the url in firestore
//                                        signUpVM.uploadImageToStorage(
//                                            caretaker = caretaker,
//                                            imageUri = caretakerImageUri,
//                                            context = context,
//                                            onSuccess = {
//                                                Toast.makeText(
//                                                    context,
//                                                    "Image stored properly",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            },
//                                            onFailure = {
//                                                Toast.makeText(
//                                                    context,
//                                                    "Image Couldn't be stored to firebase",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            }
//                                        )
//
//                                        //  open caretaker activity
//                                        direction.navigateToRoute(
//                                            com.example.houseopscaretakers.core.Constants.HOME_ROUTE
//                                        )
//                                    },
//                                    onFailure = {
//                                        Toast.makeText(
//                                            context,
//                                            "Couldn't create collection",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }
//                                )
//                            },
//                            onFailure = {
//                                Toast.makeText(
//                                    context,
//                                    "Something went wrong",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        )
//
//                    } else {
//                        Toast.makeText(context, verificationResponse, Toast.LENGTH_SHORT)
//                            .show()
//                    }
                }
                is ValidationEvent.Failure -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            SignUpAppBar(
                startIcon = Icons.Sharp.ArrowBack,
                title = "Create Account",
                userType = userType,
                onBackPressed = {
                    //  navigate back to login page
                    direction.navigateUp()
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(5f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    //  Caretaker Image
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .background(MaterialTheme.colorScheme.tertiary)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                //   grab image uri from gallery
                                launcher.launch("image/*")
                            },
                        contentAlignment = Alignment.Center
                    ) {

                        //  check if caretaker image is null or not
                        if (caretakerImageUri == null) {

                            Icon(
                                imageVector = Icons.Sharp.ImageSearch,
                                contentDescription = "Image Search",
                                modifier = Modifier
                                    .size(36.dp)
                            )

                        } else {
                            CoilImage(
                                context = LocalContext.current,
                                imageUri = caretakerImageUri,
                                placeholder = R.drawable.houseops_dark_final,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .fillMaxSize()
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    SignUpForms(
                        onUserNameChanged = {
                            signUpVM.onFormEvent(SignUpFormEvent.UserNameChanged(it))
                        },
                        onEmailChanged = {
                            signUpVM.onFormEvent(SignUpFormEvent.EmailChanged(it))
                        },
                        onPasswordChanged = {
                            signUpVM.onFormEvent(SignUpFormEvent.PasswordChanged(it))
                        },
                        onRepeatPasswordChanged = {
                            signUpVM.onFormEvent(SignUpFormEvent.RepeatedPasswordChanged(it))
                        }
                    )

                    //  Signup Button
                    Button(
                        onClick = {
                            signUpVM.onFormEvent(SignUpFormEvent.Submit)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        Text(text = "Create Account")
                    }

                }

            }
        }
    }
}




























