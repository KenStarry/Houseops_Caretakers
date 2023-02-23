package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

import android.content.Context
import android.net.Uri
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository

class UploadLandlordImage(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(
        landlord: Landlord,
        imageUri: Uri?,
        context: Context,
        response: (response: Response<*>) -> Unit
    ) = repository.uploadLandlordImageToCloudStorage(
        landlord = landlord,
        imageUri = imageUri,
        context = context,
        response = { response(it) }
    )
}