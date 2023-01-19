package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HouseDescription(
    house: HouseModel
) {

    //  house description title
    Text(
        text = "Description",
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
    )

    //  house description
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = house.houseDescription,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

    }
}