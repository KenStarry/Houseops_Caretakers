package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Title
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.CustomTextField
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel

@Composable
fun FeaturesBottomSheet(
    lndAddApartmentVM: LndAddApartmentViewModel,
    onDone: (
        title: String,
        description: String
    ) -> Unit,
    onCancel: () -> Unit,
) {

    var featureTitle by remember { mutableStateOf("") }
    var featureDescription by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = "Add Feature",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  title
        CustomTextField(
            textFieldValue = lndAddApartmentVM.featureTitle.value,
            startIcon = Icons.Outlined.Title,
            endIcon = null,
            placeholder = "Feature Title",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            onInput = {
                lndAddApartmentVM.featureTitle.value = it
            }
        )

        //  description
        CustomTextField(
            textFieldValue = lndAddApartmentVM.featureDescription.value,
            startIcon = Icons.Outlined.Description,
            endIcon = null,
            placeholder = "Feature description",
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            singleLine = false,
            maxLines = Int.MAX_VALUE,
            onInput = {
                lndAddApartmentVM.featureDescription.value = it
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  save button
            TextButton(
                onClick = {
                    onDone(featureTitle, featureDescription)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {

                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "cancel button"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Cancel")
            }

            Spacer(modifier = Modifier.width(16.dp))

            //  save button
            TextButton(onClick = {
                featureTitle = ""
                featureDescription = ""
                onCancel()
            }) {

                Icon(
                    imageVector = Icons.Outlined.Done,
                    contentDescription = "Save button"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Done")
            }
        }

    }

}