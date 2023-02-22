package com.example.houseopscaretakers.feature_caretaker.core.presentation.components

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ExtendedFab(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {

    ExtendedFloatingActionButton(onClick = { onClick() }) {

        Icon(
            imageVector = icon,
            contentDescription = "Fab icon"
        )

        Text(text = text)
    }
}