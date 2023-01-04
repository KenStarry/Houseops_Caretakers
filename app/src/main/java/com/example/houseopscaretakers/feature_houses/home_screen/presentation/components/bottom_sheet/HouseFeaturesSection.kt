package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

//  house features
@Composable
fun HouseFeaturesSection() {

    var houseFeatures by remember {
        mutableStateOf(
            listOf(
                com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures("Security", Icons.Outlined.Security, false),
                com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures("Water", Icons.Outlined.Water, false),
                com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures("Electricity", Icons.Outlined.Thunderstorm, false),
                com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures("Parking", Icons.Outlined.Park, false),
                com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures("Shops", Icons.Outlined.Shop, false),
                com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures("Rooftop", Icons.Outlined.Roofing, false)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {

        //  features title
        Text(
            text = "Features",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Select all that apply.",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            content = {
                itemsIndexed(
                    houseFeatures
                ) { index, item ->


                    PillButton(
                        value = item.featureName,

                        backgroundColor = if (item.featureSelected)
                            BlueAccentTransparent
                        else
                            MaterialTheme.colorScheme.onSecondary,

                        iconColor = MaterialTheme.colorScheme.primary,
                        icon = item.featureIcon,
                        onClick = {
                            houseFeatures = houseFeatures.mapIndexed { j, feature ->
                                //  check if index == j
                                if (index == j) {
                                    //  only change the selected value and pass it to our copied feature
                                    feature.copy(featureSelected = !item.featureSelected)
                                } else
                                    feature
                            }
                        }
                    )
                }
            },
            state = rememberLazyListState(),
            contentPadding = PaddingValues(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = true
        )

    }
}