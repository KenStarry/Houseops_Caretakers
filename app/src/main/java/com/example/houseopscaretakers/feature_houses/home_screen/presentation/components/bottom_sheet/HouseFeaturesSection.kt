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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent

//  house features
@Composable
fun HouseFeaturesSection(
    viewModel: HomeViewModel = hiltViewModel(),
    houseFeatures: (List<HouseFeatures>) -> Unit
) {

//    var houseFeaturesState = viewModel.houseFeatures

    var houseFeaturesState by remember {
        mutableStateOf(
            listOf(
                HouseFeatures("Security", Icons.Outlined.Security, false),
                HouseFeatures("Water", Icons.Outlined.Water, false),
                HouseFeatures("Electricity", Icons.Outlined.Thunderstorm, false),
                HouseFeatures("Parking", Icons.Outlined.Park, false),
                HouseFeatures("Shops", Icons.Outlined.Shop, false),
                HouseFeatures("Rooftop", Icons.Outlined.Roofing, false)
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
                    houseFeaturesState
                ) { index, item ->


                    PillButton(
                        value = item.featureName,

                        backgroundColor = if (item.featureSelected)
                            BlueAccentTransparent
                        else
                            MaterialTheme.colorScheme.onSecondary,

                        iconColor = if (item.featureSelected)
                            MaterialTheme.colorScheme.onSecondaryContainer
                        else
                            MaterialTheme.colorScheme.primary,

                        icon = item.featureIcon,
                        onClick = {
                            houseFeaturesState = houseFeaturesState.mapIndexed { j, feature ->
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

    houseFeatures(houseFeaturesState)
}