package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HouseFeatures(
    house: HouseModel
) {

    Text(
        text = "Features",
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
    )

    LazyRow(content = {
        items(
            items = house.houseFeatures
        ) {
            PillButton(
                value = it,
                backgroundColor = MaterialTheme.colorScheme.onSecondary
            ) {}

            Spacer(modifier = Modifier.width(16.dp))
        }
    })

}