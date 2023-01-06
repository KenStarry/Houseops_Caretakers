package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HouseItem(
    house: HouseModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(400.dp)
            .background(MaterialTheme.colorScheme.onSecondary)
    ) {

        //  main image


    }

}