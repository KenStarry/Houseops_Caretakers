package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        val contentPadding = if (currentHouseImages.size < 2)
            PaddingValues(end = 0.dp)
        else
            PaddingValues(end = 48.dp)

        HorizontalPager(
            count = currentHouseImages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = contentPadding
        ) { page ->

            //  display image
            CoilImage(
                context = context,
                imageUri = currentHouseImages[page].toUri(),
                placeholder = R.drawable.houseops_light_final,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight()
            )

        }

        //  horizontal item indicator
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            CustomPagerIndicator(
                pagerState = pagerState,
                height = 7.dp,
                width = 7.dp,
                inactiveColor = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}


















