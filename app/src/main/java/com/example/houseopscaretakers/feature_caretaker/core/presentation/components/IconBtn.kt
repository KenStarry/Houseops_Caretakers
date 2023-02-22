package com.example.houseopscaretakers.feature_caretaker.core.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconBtn(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    shape: Shape? = CircleShape,
    containerColor: Color? = MaterialTheme.colorScheme.onSecondary,
    contentColor: Color? = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
    onClick: () -> Unit
) {
    FilledIconButton(
        onClick = {
            onClick()
        },
        shape = shape ?: CircleShape,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor ?: MaterialTheme.colorScheme.onSecondary,
            contentColor = contentColor ?: MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icon Button"
        )
    }
}