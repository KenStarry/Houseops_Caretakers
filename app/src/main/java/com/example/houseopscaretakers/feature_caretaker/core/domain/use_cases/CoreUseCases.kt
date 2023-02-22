package com.example.houseopscaretakers.feature_caretaker.core.domain.use_cases

data class CoreUseCases(
    val connection: Connection,
    val currentUser: CurrentUser,
    val getCaretaker: GetCaretaker,
    val isCaretakerLoggedIn: IsCaretakerLoggedIn,
    val coreUploadImages: CoreUploadImages,
    val getHouse: GetHouse
)
