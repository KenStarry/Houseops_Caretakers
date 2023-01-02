package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Filter
import androidx.compose.material.icons.rounded.Filter1
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun HomeApartmentTitle(
    modifier: Modifier = Modifier,
    apartmentName: String,
    onFilter: () -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  apartment name
        Text(
            text = apartmentName,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.ExtraBold,
            softWrap = true,
            modifier = Modifier
                .weight(4f)
        )

        //  Filter Icon
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FilledTonalIconButton(
                onClick = { onFilter() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Icon(imageVector = Icons.Rounded.Filter, contentDescription = "Filter icon")
            }
        }

    }
}























