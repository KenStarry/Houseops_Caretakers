package com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.houseopscaretakers.R

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    context: Context,
    imageUriString: Uri?,
    placeholder: Int
) {

    imageUriString?.let {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(it)
                .crossfade(true)
                .placeholder(placeholder)
                .build(),
            contentDescription = "User image",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

    }
}