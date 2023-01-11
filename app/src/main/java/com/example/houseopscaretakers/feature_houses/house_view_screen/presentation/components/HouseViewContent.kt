package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.CustomPagerIndicator
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HouseViewContent(
    apartmentName: String,
    currentHouse: HouseModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        //  toolbar
        HouseViewAppbar(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(8.dp),
            category = currentHouse.houseCategory,
            onBackPressed = {

            }
        )

        //  Main scrollable content
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    )
                )
                .fillMaxWidth()
                .weight(4f)
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            //  apartment name & price
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = apartmentName,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                //  house pricing
                Text(
                    text = buildAnnotatedString {
                        append("ksh.")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                        ) { append("15,000") }

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) { append("/m") }
                    },
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

            }

            //  location
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                Text(
                    text = "Sichirai, Kakamega",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }

        }

    }
}






















