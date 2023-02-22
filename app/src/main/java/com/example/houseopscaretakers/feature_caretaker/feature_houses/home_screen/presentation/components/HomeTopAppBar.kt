package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_caretaker.core.presentation.components.CoilImage

@Composable
fun HomeTopAppBar(
    context: Context,
    title: String,
    userImageUri: Uri?,
    placeholderImage: Int,
    onClickMore: () -> Unit,
    onClickNotifications: () -> Unit,
    onClickWatchlist: () -> Unit,
    onClickImage: () -> Unit
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
                    .clickable { onClickImage() }
            )
        },
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        elevation = 0.dp,
        actions = {

            //  Watchlist Icon
            IconButton(onClick = { onClickWatchlist() }) {
                Icon(
                    imageVector = Icons.Outlined.TrackChanges,
                    contentDescription = "Watchlist Icon"
                )
            }

            //  Notifications icon
            IconButton(onClick = { onClickNotifications() }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications Icon"
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
































