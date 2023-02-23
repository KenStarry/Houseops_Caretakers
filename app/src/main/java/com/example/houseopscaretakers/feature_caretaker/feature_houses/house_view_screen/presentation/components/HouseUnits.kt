package com.example.houseopscaretakers.feature_caretaker.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HouseUnits(
    house: HouseModel,
    isEditMode: Boolean
) {

    val houseUnits by remember {
        mutableStateOf(house.houseUnits)
    }

    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Units",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  minus button
            IconBtn(
                icon = Icons.Outlined.Remove,
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {

            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = houseUnits,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.width(16.dp))

            //  add button
            IconBtn(
                icon = Icons.Outlined.Add,
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {

            }

        }

    }


}

















