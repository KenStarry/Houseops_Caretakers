package com.example.houseopscaretakers.feature_landlord.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LndHomeGreetings(
    modifier: Modifier = Modifier,
    landlordName: String,
    greetingsText: String
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = greetingsText,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
        )

        Text(
            text = landlordName,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

    }
}