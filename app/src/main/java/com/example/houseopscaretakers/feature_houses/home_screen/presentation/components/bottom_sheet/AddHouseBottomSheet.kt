package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel

@Composable
fun AddHouseBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    apartmentName: String,
    onHouseAdd: (HouseModel) -> Unit
) {

    val coreViewModel: CoreViewModel = hiltViewModel()

    var units by remember {
        mutableStateOf(0)
    }
    var description by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("00")
    }
    var priceCategory by remember {
        mutableStateOf(Constants.priceCategories[0])
    }
    var houseFeaturesList by remember {
        mutableStateOf<List<HouseFeatures>>(emptyList())
    }

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

        //  house price
        HousePrice(
            viewModel = viewModel,
            onPriceEntered = { price = it },
            onPriceCategory = { priceCategory = it }
        )

        //  pick house images
        PickHouseImages(viewModel)

        UnitsRemaining() {
            units = it
        }

        //  House Features
        HouseFeaturesSection(viewModel) {
            houseFeaturesList = it
        }

        //  description
        Description(
            onInput = {
                //  save input
                description = it
            }
        )


        val house = HouseModel(
            houseCategory = viewModel.pillName.value,
            housePurchaseType = "For Rent",
            houseImageUris = emptyList(),
            houseUnits = units.toString(),
            houseFeatures = houseFeaturesList.filter { it.featureSelected }
                .map { it.featureName },
            houseDescription = description,
            houseLikes = "67",
            houseApartmentName = apartmentName,
            houseComments = "",
            housePriceCategory = priceCategory,
            housePrice = price
        )

        //  add house button
        Button(
            onClick = {

                //  validate details
                onHouseAdd(house)
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(
                text = "Add House",
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}


















