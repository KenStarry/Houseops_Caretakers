package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.FormTextField

//  Units remaining
@Composable
fun UnitsRemaining(
    unitsRemaining: (Int) -> Unit
) {

    var unitsRem by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        //  units title
        Text(
            text = "Units",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            FormTextField(
                inputType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                onValueChange = {

                    if (it.isBlank() || it.isEmpty() || it != "." || it != ",") {
                        unitsRem = 0
                    } else {
                        unitsRem = it.trim().toInt()
                        unitsRemaining(unitsRem)
                    }

                },
                placeholder = "Units Remaining",
                leadingIcon = Icons.Outlined.Numbers,
                modifier = Modifier
                    .wrapContentWidth()
            )
        }

    }

}