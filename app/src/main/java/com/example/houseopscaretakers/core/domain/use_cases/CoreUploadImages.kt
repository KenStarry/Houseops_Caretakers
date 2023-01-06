package com.example.houseopscaretakers.core.domain.use_cases

import android.content.Context
import android.net.Uri
import com.example.houseopscaretakers.core.domain.repository.CoreRepository
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

class CoreUploadImages(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        imageUriList: List<Uri>,
        context: Context,
        houseModel: HouseModel,
        apartmentName: String
    ) {
        repository.uploadImagesToStorage(
            imageUriList, context, houseModel, apartmentName
        )
    }
}