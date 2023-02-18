package com.example.houseopscaretakers.feature_houses.house_add_screen.presentation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseAddAppBar() {

    LargeTopAppBar(
        title = {
            Text(
                text = "Add House",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "back arrow"
                )
            }
        },
        windowInsets = WindowInsets(
            left = 16.dp,
            right = 16.dp
        ),
         colors = TopAppBarDefaults.largeTopAppBarColors(
             containerColor = MaterialTheme.colorScheme.onPrimary,
             scrolledContainerColor = MaterialTheme.colorScheme.onSecondary,
             titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
             navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
             actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
         )
    )
}