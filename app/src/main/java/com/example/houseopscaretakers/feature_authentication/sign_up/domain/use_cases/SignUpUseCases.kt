package com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases

data class SignUpUseCases(
    val createUserInFirebase: CreateUserInFirebase,
    val createCaretakerCollection: CreateCaretakerCollection,
    val uploadCaretakerImage: UploadCaretakerImage,
    val createLandlordCollection: CreateLandlordCollection,
    val uploadLandlordImage: UploadLandlordImage
)
