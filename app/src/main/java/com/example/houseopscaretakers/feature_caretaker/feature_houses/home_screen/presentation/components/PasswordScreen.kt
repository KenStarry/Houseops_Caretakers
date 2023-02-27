package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.CustomTextField
import com.example.houseopscaretakers.core.presentation.components.DoneCancelButtons
import com.example.houseopscaretakers.core.presentation.components.MyLottie
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.data.HomeConstants
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HomeEvents
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel

@Composable
fun PasswordScreen(
    homeVM: HomeViewModel,
    onDone: (
        apartmentName: String,
        caretakerId: String
    ) -> Unit,
    onCancel: () -> Unit
) {

    //  DIALOGS
    AnimatedVisibility(visible = homeVM.isApartmentsDialogVisible.value) {
        ApartmentsDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MyLottie(
            lottieRaw = R.raw.biometric_failed,
            isPlaying = true,
            iterations = 1,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Authentication",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Fill the details below to verify your identity.",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(48.dp))

        //  apartment name alert dialog
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Apartment,
                    contentDescription = "apartment icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .clickable {
                        //  open apartments dialog
                        homeVM.onHomeScreenEvent(HomeEvents.ToggleAlertDialog(
                            isVisible = true,
                            dialogType = HomeConstants.APARTMENTS_ALERT_DIALOG
                        ))
                    }
                    .padding(
                        horizontal = 16.dp,
                        vertical = 4.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Apartment In-charge",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                IconButton(onClick = {
                    //  open apartments dialog
                    homeVM.onHomeScreenEvent(HomeEvents.ToggleAlertDialog(
                        isVisible = true,
                        dialogType = HomeConstants.APARTMENTS_ALERT_DIALOG
                    ))
                }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "downward arrow",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //  caretaker id number
        CustomTextField(
            textFieldValue = homeVM.caretakerId.value,
            startIcon = Icons.Outlined.PermIdentity,
            endIcon = null,
            placeholder = "Caretaker ID Number",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            onInput = {
                homeVM.caretakerId.value = it
            },
            containerColor = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.height(48.dp))

        DoneCancelButtons(
            onDone = {
                onDone(
                    homeVM.apartmentName.value,
                    homeVM.caretakerId.value
                )
            },
            onCancel = { onCancel() }
        )

    }
}








































