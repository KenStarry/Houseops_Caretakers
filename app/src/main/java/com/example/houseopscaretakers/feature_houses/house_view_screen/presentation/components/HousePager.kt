package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseEvents
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HousePager(
    modifier: Modifier = Modifier,
    currentHouseImages: List<String>
) {
    val context = LocalContext.current

    HorizontalPager(
        count = currentHouseImages.size,
        state = rememberPagerState(),
        modifier = modifier,
        itemSpacing = 16.dp
    ) { page ->

        //  display image
        CoilImage(
            context = context,
            imageUri = currentHouseImages[page].toUri(),
            placeholder = R.drawable.houseops_light_final,
            modifier = Modifier
                .fillMaxSize()
        )


    }

}