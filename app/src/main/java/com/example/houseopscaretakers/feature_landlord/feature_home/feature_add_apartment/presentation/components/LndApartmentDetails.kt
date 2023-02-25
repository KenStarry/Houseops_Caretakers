package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.CustomTextField
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel

@Composable
fun LndApartmentDetails(
    modifier: Modifier = Modifier,
    lndAddApartmentVM: LndAddApartmentViewModel,
    onLocationClicked: () -> Unit,
    onHouseFeaturesClicked: () -> Unit
) {

    val listState = rememberLazyListState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  add apartment
        CustomTextField(
            startIcon = Icons.Outlined.Apartment,
            endIcon = null,
            placeholder = "Apartment Name",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            onInput = {
                //  verify the details
            }
        )

        //  location section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  section items
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //  location
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Location",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                    Icon(
                        imageVector = Icons.Outlined.ArrowRight,
                        contentDescription = "Right arrow",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(MaterialTheme.colorScheme.tertiary)
                            .clickable {
                                //  open location bottomsheet
                                onLocationClicked()
                            }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = lndAddApartmentVM.pickedLocation.value,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )

                        Icon(
                            imageVector = Icons.Outlined.ExpandMore,
                            contentDescription = "expand arrow",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )

                    }

                }

            }
        }

        //  caretaker id section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  section items
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //  caretaker id
                CustomTextField(
                    startIcon = Icons.Outlined.Numbers,
                    endIcon = null,
                    placeholder = "Caretaker ID Number",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number,
                    primaryColor = MaterialTheme.colorScheme.primary,
                    tertiaryColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    onInput = {
                        //  verify the details
                    }
                )

            }
        }

        //  apartment features section
        //  section items
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  house categories
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Apartment Features",
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowRight,
                    contentDescription = "Right arrow",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                Button(onClick = { onHouseFeaturesClicked() }) {
                    Text(
                        text = "Add Feature",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )
                }

            }

            //  categories added
            LazyRow(content = {
                items(
                    items = lndAddApartmentVM.apartmentFeatures
                ) {

                    //  feature item

                }
            })

        }



        //  apartment features e.g security type, social ammenities... etc (list)
        //  apartment terms and conditions (list)

    }
}














