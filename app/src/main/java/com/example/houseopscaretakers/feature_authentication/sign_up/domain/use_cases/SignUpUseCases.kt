package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

data class SignUpUseCases(
    val createCaretakerInFirebase: CreateCaretakerInFirebase,
    val createCaretakerCollection: CreateCaretakerCollection,
    val uploadCaretakerImage: UploadCaretakerImage
)
