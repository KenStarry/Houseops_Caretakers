package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.houseopscaretakers.core.presentation.components.CustomAlertDialog
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment

@Composable
fun ApartmentsDialog(
    apartments: List<Apartment>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {

    CustomAlertDialog(
        icon = Icons.Outlined.Apartment,
        primaryColor = MaterialTheme.colorScheme.primary,
        tertiaryColor = MaterialTheme.colorScheme.tertiary,
        title = "Apartments",
        content = {
            LazyColumn(content = {

            })
        },
        onConfirm = { /*TODO*/ },
        onDismiss = {})
}