package com.example.houseopscaretakers.feature_caretaker.feature_houses.house_view_screen.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseViewAppbar(
    modifier: Modifier = Modifier,
    category: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPressed: () -> Unit,
    onHouseEdit: () -> Unit
) {

    LargeTopAppBar(
        title = {

            Text(
                text = category,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Arrow"
                )
            }
        },
        actions = {
            IconButton(onClick = { onHouseEdit() }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Icon"
                )
            }

            IconButton(onClick = { TODO("add onClick for more button") }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More Icon"
                )
            }

        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            scrolledContainerColor = MaterialTheme.colorScheme.onPrimary
        ),
        scrollBehavior = scrollBehavior
    )
}




























