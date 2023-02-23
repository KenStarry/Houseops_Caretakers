package com.example.houseopscaretakers.core.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackPressTopBar(
    startIcon: ImageVector?,
    title: String?,
    onBackPressed: () -> Unit
) {

    LargeTopAppBar(
        title = {
            Text(
                text = title ?: "",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            startIcon?.let {
                IconButton(onClick = {
                    onBackPressed()
                }) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Start Icon"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}




























