package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.viewmodels.HomeViewModel

@Composable
fun HomeApartmentTitle(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    apartmentName: String,
    onFilter: () -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  apartment name
        Text(
            text = viewModel.addApartmentSuffix(apartmentName),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.ExtraBold,
            softWrap = true,
            modifier = Modifier
                .weight(4f),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        //  Filter Icon
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FilledTonalIconButton(
                onClick = { onFilter() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = "Expand Icon"
                )
            }
        }

    }
}
























