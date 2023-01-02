package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeTopAppBar(
    title: String,
    onClickMore: () -> Unit,
    onClickFilter: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        elevation = 0.dp,
        actions = {

            //  Filter icon
            IconButton(onClick = { onClickFilter() }) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = "Expand Icon"
                )
            }

            //  More icon
            IconButton(onClick = { onClickMore() }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "Expand Icon"
                )
            }
        }
    )
}
































