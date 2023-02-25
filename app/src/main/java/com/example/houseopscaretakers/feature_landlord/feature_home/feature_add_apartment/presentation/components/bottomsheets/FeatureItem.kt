package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.ApartmentFeature

@Composable
fun FeatureItem(
    apartmentFeature: ApartmentFeature
) {

    Card(
        modifier = Modifier
            .size(
                width = 180.dp,
                height = 200.dp
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = apartmentFeature.title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Text(
                text = apartmentFeature.description,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

        }

    }

}

@Preview
@Composable
fun FeatureItemPreview(
    apartmentFeature: ApartmentFeature = ApartmentFeature(
        "Security",
        "Security has been enforced through the use of mbwa kali!"
    )
) {
    FeatureItem(apartmentFeature = apartmentFeature)
}