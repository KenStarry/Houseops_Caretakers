package com.example.houseopscaretakers.feature_authentication.sign_up.presentation

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.utils.getSingleImageFromGallery
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_authentication.login.domain.model.ValidationEvent
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.SignUpEvents
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.SignUpFormEvent
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.SignUpAppBar
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.SignUpForms
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseopscaretakers.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signUpVM: SignUpViewModel = hiltViewModel(),
    navHostController: NavHostController,
    userType: String
) {

    val context = LocalContext.current
    val direction = Direction(navHostController)
    val coreVM: CoreViewModel = hiltViewModel()

    //  get the current user type
//    val userType = coreVM.userTypeFlow.collectAsState(initial = null).value

    var userImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = getSingleImageFromGallery(onResult = {
        userImageUri = it
    })

    Toast.makeText(context, "$userType!", Toast.LENGTH_SHORT)
        .show()

    LaunchedEffect(key1 = context) {
        signUpVM.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {

                    val landlord = Landlord(
                        landlordName = signUpVM.formState.username,
                        landlordEmail = signUpVM.formState.email,
                        landlordImage = userImageUri.toString(),
                        landlordPassword = signUpVM.formState.password,
                        isLandlordVerified = false
                    )

                    val caretaker = Caretaker(
                        caretakerName = signUpVM.formState.username,
                        caretakerId = "",
                        caretakerApartment = "",
                        caretakerEmail = signUpVM.formState.email,
                        caretakerImage = userImageUri.toString(),
                        caretakerPassword = signUpVM.formState.password,
                        isCaretakerVerified = false
                    )

                    //  create user in firebase
                    signUpVM.onEvent(SignUpEvents.CreateUserInFirebase(
                        email = signUpVM.formState.email,
                        password = signUpVM.formState.password,
                        onResponse = {
                            when (it) {
                                is Response.Success -> {

                                    Toast.makeText(context, "User is $userType!", Toast.LENGTH_SHORT)
                                        .show()

                                    //  create the user in firestore
                                    if (userType == Constants.routePaths[0].title) {
                                        signUpVM.onEvent(SignUpEvents.CreateLandlordCollection(
                                            landlord = landlord,
                                            response = { res ->
                                                when (res) {
                                                    is Response.Success -> {

                                                        //  go to desired activity
                                                        direction.navigateAndPopRoute(
                                                            Constants.LANDLORD_ROUTE,
                                                            Constants.LANDLORD_ROUTE
                                                        )

                                                        Toast.makeText(context, "Logged in successfully as $userType!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                    is Response.Failure -> {
                                                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                            }
                                        ))

                                        signUpVM.onEvent(SignUpEvents.UploadLandlordImageToStorage(
                                            landlord = landlord,
                                            imageUri = userImageUri,
                                            context = context,
                                            response = {}
                                        ))

                                    } else if (userType == Constants.routePaths[1].title) {

                                        signUpVM.onEvent(SignUpEvents.CreateCaretakerCollection(
                                            caretaker = caretaker,
                                            response = { res ->
                                                when (res) {
                                                    is Response.Success -> {

                                                        //  go to desired activity
                                                        direction.navigateAndPopRoute(
                                                            Constants.HOME_ROUTE,
                                                            Constants.HOME_ROUTE
                                                        )

                                                        Toast.makeText(context, "Logged in successfully as $userType!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                    is Response.Failure -> {
                                                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                            }
                                        ))

                                        signUpVM.onEvent(SignUpEvents.UploadCaretakerImageToStorage(
                                            caretaker = caretaker,
                                            imageUri = userImageUri,
                                            context = context,
                                            response = {}
                                        ))
                                    }

                                }
                                is Response.Failure -> {
                                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    ))
                }
                is ValidationEvent.Failure -> {
                    Toast.makeText(context, "Check details", Toast.LENGTH_SHORT).show()
                }
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
                        if (userImageUri == null) {

                            Icon(
                                imageVector = Icons.Sharp.ImageSearch,
                                contentDescription = "Image Search",
                                modifier = Modifier
                                    .size(36.dp)
                            )

                        } else {
                            CoilImage(
                                context = LocalContext.current,
                                imageUri = userImageUri,
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




























