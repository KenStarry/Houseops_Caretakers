package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.home_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.StatsCard
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.model.StatsCardModel

@Composable
fun StatsCards(
    statsCardsList: List<StatsCardModel>
) {

    LazyRow(
        content = {
            itemsIndexed(
                statsCardsList
            ) { index, card ->

                StatsCard(
                    title = card.title,
                    icon = card.icon,
                    iconColor = card.iconColor,
                    value = card.value,
                    onClick = { title ->
                        //  navigate to card page
                    }
                )
            }
        },
        state = rememberLazyListState(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )

}