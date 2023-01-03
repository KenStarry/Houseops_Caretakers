package com.example.houseopscaretakers.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.ui.theme.BlueAccentLight
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

@Composable
fun PillButton(
    value: String,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.onSecondary)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = "Pill Icon",
                tint = BlueAccentLight,
                modifier = Modifier
                    .size(16.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = value,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

    }
}