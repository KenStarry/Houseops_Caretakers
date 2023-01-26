package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.FormTextField
import com.example.houseopscaretakers.core.presentation.components.IconBtn

//  Units remaining
@Composable
fun UnitsRemaining(
    unitsRemaining: (Int) -> Unit
) {

    var unitsRem by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  units title
        Text(
            text = "Vacant Units",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            unitsRemaining(unitsRem)

            //  minus button
            IconBtn(
                icon = Icons.Outlined.Remove,
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {

                unitsRem -= 1
                unitsRemaining(unitsRem)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = unitsRem.toString(),
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

                unitsRem += 1
                unitsRemaining(unitsRem)
            }

        }

//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 16.dp)
//        ) {
//            FormTextField(
//                inputType = KeyboardType.Number,
//                imeAction = ImeAction.Next,
//                onValueChange = {
//
//                    if (it.isBlank() || it.isEmpty()) {
//                        unitsRem = 0
//                    } else {
//                        unitsRem = it.trim().toInt()
//                        unitsRemaining(unitsRem)
//                    }
//
//                },
//                placeholder = "Units Remaining",
//                leadingIcon = Icons.Outlined.Numbers,
//                modifier = Modifier
//                    .wrapContentWidth()
//            )
//        }

    }

}