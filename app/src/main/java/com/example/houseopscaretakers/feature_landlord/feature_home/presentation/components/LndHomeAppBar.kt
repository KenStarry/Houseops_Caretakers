package com.example.houseopscaretakers.feature_landlord.feature_home.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LndHomeAppBar(
    context: Context,
    imageUri: Uri?
) {

    TopAppBar(

        title = {
            Text(
                text = "Home",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        },

        navigationIcon = {
            if (imageUri != null) {
                CoilImage(
                    context = context,
                    imageUri = imageUri,
                    placeholder = R.drawable.houseops_light_final
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Navigation,
                    contentDescription = "Hamburger button"
                )
            }
        },

        actions = {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "More Icon"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )
    )
}