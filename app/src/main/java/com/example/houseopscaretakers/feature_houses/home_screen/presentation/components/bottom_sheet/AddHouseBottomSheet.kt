package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel

@Composable
fun AddHouseBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  Bottom sheet Icon
        Icon(
            imageVector = Icons.Outlined.Minimize,
            contentDescription = "Dash icon",
            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        //  title
        Text(
            text = "House Category",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        //  house category
        HouseCategory(viewModel)

        //  pick house images
        PickHouseImages(viewModel)

        UnitsRemaining() {}

        //  House Features
        HouseFeaturesSection()

        //  description
        Description(
            onInput = {
                //  save input
            }
        )
    }
}


















