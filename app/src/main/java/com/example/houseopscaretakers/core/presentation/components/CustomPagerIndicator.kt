package com.example.houseopscaretakers.core.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    height: Dp = 5.dp,
    width: Dp = 5.dp,
    inactiveColor: Color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
    activeColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = CircleShape
) {
    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = modifier,
        inactiveColor = inactiveColor,
        activeColor = activeColor,
        indicatorWidth = width,
        indicatorHeight = height,
        indicatorShape = shape
    )
}
















