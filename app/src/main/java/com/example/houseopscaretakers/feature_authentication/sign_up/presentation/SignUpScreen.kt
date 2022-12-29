package com.example.houseopscaretakers.feature_authentication.sign_up.presentation

import android.net.Uri
import android.view.RoundedCorner
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.core.Constants.textFieldsList
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.model.TextFieldContent
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components.SignUpTextField
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            BackPressTopBar(
                startIcon = Icons.Sharp.ArrowBack,
                title = "Sign Up",
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
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                //  Caretaker Image
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp)
                        .background(BlueAccentTransparent)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            //   grab image uri from gallery

                        },
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Sharp.Image,
                        contentDescription = "Image Search",
                        modifier = Modifier
                            .size(36.dp)
                    )
                }

                //  textfields
                textFieldsList.forEach { item ->
                    SignUpTextField(
                        onValueChange = {},
                        placeholder = item.placeholder,
                        leadingIcon = item.icon,
                        inputType = item.inputType,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                    )
                }

                //  Signup Button
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Sign Up")
                }

            }
        }
    }
}




























