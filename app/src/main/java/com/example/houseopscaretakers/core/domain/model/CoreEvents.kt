package com.example.houseopscaretakers.core.domain.model

import android.content.Context
import android.net.Uri
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

sealed class CoreEvents {

    data class UploadImageEvent(
        val imageUriList: List<Uri>,
        val context: Context,
        val houseModel: HouseModel,
        val apartmentName: String
    ) : CoreEvents()
}
