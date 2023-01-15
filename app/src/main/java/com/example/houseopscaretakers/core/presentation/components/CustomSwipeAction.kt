package com.example.houseopscaretakers.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import me.saket.swipe.SwipeAction

@Composable
fun customSwipeAction(
    icon: ImageVector,
    iconTint: Color,
    background: Color,
    onSwipe: () -> Unit
): SwipeAction {

    return SwipeAction(
        onSwipe = {
            onSwipe()
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "Swipe icon",
                tint = iconTint,
                modifier = Modifier
                    .padding(16.dp)
             )
        },
        background = background
    )
}