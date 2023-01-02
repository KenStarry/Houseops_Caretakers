package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

import android.content.Context
import android.net.Uri
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository

class UploadCaretakerImage(
    private val repo: SignupRepository
) {

    suspend operator fun invoke(
        caretaker: Caretaker,
        imageUri: Uri?,
        context: Context
    ): CreateUserResponse = repo.uploadCaretakerImageToCloudStorage(
        caretaker, imageUri, context
    )
}