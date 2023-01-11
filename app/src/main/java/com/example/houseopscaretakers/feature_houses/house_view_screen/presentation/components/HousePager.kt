package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.CustomPagerIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HousePager(
    modifier: Modifier = Modifier,
    currentHouseImages: List<String>
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
    ) {

        HorizontalPager(
            count = currentHouseImages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            itemSpacing = 16.dp,
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

        //  horizontal item indicator
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.weight(2f))

            CustomPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.weight(1f),
                height = 7.dp,
                width = 7.dp,
                inactiveColor = MaterialTheme.colorScheme.onSecondary
            )
        }

    }
}


















