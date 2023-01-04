package com.example.houseopscaretakers.core.presentation.utils

import android.content.pm.LauncherActivityInfo
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun getSingleImageFromGallery(
    onResult: (uri: Uri) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                onResult(it)
            }
        }
    )

    return launcher
}

@Composable
fun getMultipleImagesFromGallery(
    onResult: (uriList: ArrayList<Uri>) -> Unit
): ManagedActivityResultLauncher<String, List<Uri>> {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri> ->
            onResult(uris as ArrayList<Uri>)
        }
    )

    return launcher
}





















