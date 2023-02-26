package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.presentation.components.BackPressTopBar
import com.example.houseopscaretakers.core.presentation.components.DoneCancelButtons
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.model.PlacesAPIResult
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.example.houseopscaretakers.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LndApartmentMain(
    direction: Direction,
    lndAddApartmentVM: LndAddApartmentViewModel,
    onLocationClicked: () -> Unit,
    onHouseFeaturesClicked: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            BackPressTopBar(
                startIcon = Icons.Outlined.ArrowBack,
                title = "Add Apartment",
                onBackPressed = {
                    direction.navigateUp()
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                //  apartment details
                LndApartmentDetails(
                    modifier = Modifier
                        .fillMaxSize(),
                    lndAddApartmentVM = lndAddApartmentVM,
                    onLocationClicked = {
                        //  open places api
                        onLocationClicked()
                    },
                    onHouseFeaturesClicked = {
                        onHouseFeaturesClicked()
                    }
                )

                //  cancel and done buttons
                DoneCancelButtons(
                    onDone = {
                        lndAddApartmentVM.onEvent(
                            LndApartmentEvents.AddApartment(
                                apartment = Apartment(
                                    apartmentName = lndAddApartmentVM.apartmentName.value,
                                    apartmentLocation = lndAddApartmentVM.apartmentLocation.value,
                                    apartmentCaretakerId = lndAddApartmentVM.apartmentCaretakerId.value,
                                    apartmentFeatures = lndAddApartmentVM.apartmentFeatures
                                ),
                                response = {
                                    when (it) {
                                        is Response.Success -> {
                                            Toast.makeText(
                                                context,
                                                "Apartment Added Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        is Response.Loading -> {}
                                        is Response.Failure -> {
                                            Toast.makeText(
                                                context,
                                                "Something went wrong.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            )
                        )
                    },
                    onCancel = {}
                )

            }

        }

    }
}