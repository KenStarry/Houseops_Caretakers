package com.example.houseopscaretakers.core.presentation.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun getSingleImageFromGallery(): Uri? {

    var imageUri: Uri? = null

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    return imageUri
}

@Composable
fun getMultipleImagesFromGallery(): List<Uri>? {

    var imageUriList: List<Uri>? = null

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri> ->
            imageUriList = uris
        }
    )

    return imageUriList
}





















