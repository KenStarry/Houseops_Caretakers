package com.example.houseopscaretakers.core.data.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

fun getFileExtension(uri: Uri, context: Context): String? {

    val cr = context.contentResolver
    val mimeTypeMap = MimeTypeMap.getSingleton()

    return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
}