package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.ui.theme.BlueAccent
import com.example.houseopscaretakers.ui.theme.RedOrangeDull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsCard(
    title: String,
    icon: ImageVector,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    value: String,
    onClick: (title: String) -> Unit
) {

    Card(
        onClick = { onClick(title) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
    ) {
        Column(
            modifier = Modifier
                .size(width = 130.dp, height = 130.dp)
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            //  icon
            Icon(
                imageVector = icon,
                contentDescription = "card icon",
                tint = iconColor

            )

            //  value
            Text(
                text = value,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            //  value
            Text(
                text = title,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }
    }


}

@Preview
@Composable
fun StastsItemPrev() {
    StatsCard(
        "Completed Items",
        Icons.Outlined.Notifications,
        BlueAccent,
        "25"
    ) {}
}