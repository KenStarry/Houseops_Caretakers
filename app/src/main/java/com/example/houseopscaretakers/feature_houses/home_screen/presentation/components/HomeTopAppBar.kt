package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.CoilImage

@Composable
fun HomeTopAppBar(
    context: Context,
    title: String,
    userImageUri: Uri?,
    placeholderImage: Int,
    onClickMore: () -> Unit,
    onClickFilter: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        navigationIcon = {
            Spacer(modifier = Modifier.width(10.dp))
            CoilImage(
                context = context,
                imageUri = userImageUri,
                placeholder = placeholderImage,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        elevation = 0.dp,
        actions = {

            //  Filter icon
            IconButton(onClick = { onClickFilter() }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
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
































