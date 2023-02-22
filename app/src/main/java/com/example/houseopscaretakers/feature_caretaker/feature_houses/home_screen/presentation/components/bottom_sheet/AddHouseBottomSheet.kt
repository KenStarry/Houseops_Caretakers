package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components.bottom_sheet

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.feature_caretaker.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseFeatures
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.viewmodels.HomeViewModel

@Composable
fun AddHouseBottomSheet(
    modifier: Modifier = Modifier,
    homeVM: HomeViewModel = hiltViewModel(),
    apartmentName: String,
    onHouseAdd: (HouseModel) -> Unit
) {

    val coreViewModel: CoreViewModel = hiltViewModel()
    val context: Context = LocalContext.current

    var units by remember {
        mutableStateOf(0)
    }
    var description by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var priceCategory by remember {
        mutableStateOf(com.example.houseopscaretakers.feature_caretaker.core.Constants.priceCategories[0])
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
        HouseCategory(homeVM)

        //  house price
        HousePrice(
            viewModel = homeVM,
            onPriceEntered = { price = it },
            onPriceCategory = { priceCategory = it }
        )

        //  pick house images
        PickHouseImages(homeVM)

        UnitsRemaining() {
            units = it
        }

        //  House Features
        HouseFeaturesSection(homeVM) {
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
            houseId = homeVM.generateHouseID(
                apartmentName = apartmentName,
                houseCategory = homeVM.pillName.value
            ),
            houseCategory = homeVM.pillName.value,
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
            housePrice = price,
            houseUsersBooked = emptyList()
        )

        //  add house button
        Button(
            onClick = {

                //  validate details
                homeVM.onBottomSheetEvent(BottomSheetEvents.ValidateDetails(house))

                if (homeVM.areDetailsValid) {
                    onHouseAdd(house)

                } else {
                    Toast.makeText(context, homeVM.validationMessage.value, Toast.LENGTH_SHORT)
                        .show()
                }
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


















