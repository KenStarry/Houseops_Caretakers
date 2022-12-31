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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.Constants.textFieldsList
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.core.presentation.utils.getSingleImageFromGallery
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.CoilImage
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.SignUpSVG
import com.example.houseopscaretakers.core.presentation.components.FormTextField
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    var context = LocalContext.current

    var caretakerImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = getSingleImageFromGallery(onResult = {
        caretakerImageUri = it
    })

    var usernameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var apartmentInput by remember { mutableStateOf("") }
    var idInput by remember { mutableStateOf("") }
    var newPassInput by remember { mutableStateOf("") }
    var confirmPassInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BackPressTopBar(
                startIcon = Icons.Sharp.ArrowBack,
                title = "",
                onBackPressed = {
                    //  navigate back to login page
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

                SignUpSVG(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Create Account",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

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
                            .background(BlueAccentTransparent)
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
                                imageUriString = caretakerImageUri,
                                placeholder = R.drawable.houseops_dark_final,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .fillMaxSize()
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    //  User name
                    FormTextField(
                        onValueChange = {
                            usernameInput = it
                        },
                        placeholder = textFieldsList[0].placeholder,
                        leadingIcon = textFieldsList[0].icon,
                        inputType = textFieldsList[0].inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )

                    //  Email Address
                    FormTextField(
                        onValueChange = {
                            emailInput = it
                        },
                        placeholder = textFieldsList[1].placeholder,
                        leadingIcon = textFieldsList[1].icon,
                        inputType = textFieldsList[1].inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )

                    //  Apartment name
                    FormTextField(
                        onValueChange = {
                            apartmentInput = it
                        },
                        placeholder = textFieldsList[5].placeholder,
                        leadingIcon = textFieldsList[5].icon,
                        inputType = textFieldsList[5].inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )

                    //  ID number
                    FormTextField(
                        onValueChange = {
                            idInput = it
                        },
                        placeholder = textFieldsList[2].placeholder,
                        leadingIcon = textFieldsList[2].icon,
                        inputType = textFieldsList[2].inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )

                    //  New Password
                    FormTextField(
                        onValueChange = {
                            newPassInput = it
                        },
                        placeholder = textFieldsList[3].placeholder,
                        leadingIcon = textFieldsList[3].icon,
                        inputType = textFieldsList[3].inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )

                    //  confirm Pass
                    FormTextField(
                        onValueChange = {
                            confirmPassInput = it
                        },
                        placeholder = textFieldsList[4].placeholder,
                        leadingIcon = textFieldsList[4].icon,
                        inputType = textFieldsList[4].inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )

                    //  Signup Button
                    Button(
                        onClick = {

                            val verificationResponse = viewModel.verifyCaretakerDetails(
                                userName = usernameInput,
                                id = idInput,
                                apartment = apartmentInput,
                                email = emailInput,
                                newPassword = newPassInput,
                                confirmPassword = confirmPassInput
                            )
                            val caretaker = Caretaker(
                                caretakerEmail = emailInput,
                                caretakerName = usernameInput,
                                caretakerImage = caretakerImageUri,
                                caretakerApartment = "Blessed",
                                caretakerId = idInput,
                                caretakerPassword = newPassInput
                            )

                            //  verify user details and create user
                            if (verificationResponse == Constants.AUTH_SUCCESSFUL) {

                                //  create user account
                                viewModel.createCaretakerWithEmailAndPassword(
                                    email = emailInput,
                                    password = newPassInput,
                                    onSuccess = {
                                    },
                                    onFailure = {
                                        Toast.makeText(
                                            context,
                                            "Something went wrong",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )

                                //  create caretaker collection
                                viewModel.createCaretakerCollection(
                                    caretaker = caretaker,
                                    onSuccess = {
                                        //  store the url in firestore
                                        viewModel.uploadImageToStorage(
                                            caretaker = caretaker,
                                            context = context,
                                            onSuccess = {
                                                Toast.makeText(
                                                    context,
                                                    "Image stored properly",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            },
                                            onFailure = {
                                                Toast.makeText(
                                                    context,
                                                    "Image Couldn't be stored to firebase",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        )
                                    },
                                    onFailure = {
                                        Toast.makeText(
                                            context,
                                            "Couldn't create collection",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )

                            } else {
                                Toast.makeText(context, verificationResponse, Toast.LENGTH_SHORT)
                                    .show()
                            }
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




























