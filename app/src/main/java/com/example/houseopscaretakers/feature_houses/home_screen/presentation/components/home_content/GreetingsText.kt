package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.home_content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel

@Composable
fun GreetingsText(
    homeViewModel: HomeViewModel = hiltViewModel(),
    caretaker: Caretaker?
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Hello",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                    alpha = 0.6f
                )
            )

            Text(
                text = caretaker?.caretakerName ?: "",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                    alpha = 0.9f
                )
            )
        }

        PillButton(
            value = caretaker?.caretakerApartment ?: "",
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            onClick = {}
        )

    }

}