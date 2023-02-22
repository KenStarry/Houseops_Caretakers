package com.example.houseopscaretakers.feature_caretaker.core.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackPressTopBar(
    startIcon: ImageVector?,
    title: String?,
    onBackPressed: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = title ?: "")
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




























