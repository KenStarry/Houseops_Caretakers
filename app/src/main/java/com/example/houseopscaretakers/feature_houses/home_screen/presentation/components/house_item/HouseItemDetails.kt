package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.ui.theme.PinkAccent

@Composable
fun HouseItemDetails(
    houseModel: HouseModel,
    modifier: Modifier = Modifier,
    onViewClick: () -> Unit
) {


    Column(
        modifier = modifier
    ) {

        //  item units left and item price
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  units left
            PillButton(
                value = "${houseModel.houseUnits} units left",
                backgroundColor = MaterialTheme.colorScheme.onPrimary,
                paddingHorizontal = 8.dp,
                paddingVertical = 8.dp
            ) {

            }

            //  house pricing
            Text(
                text = buildAnnotatedString {
                    append("ksh. ")

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
                    ) { append(" /month") }
                },
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

        //  item description
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = houseModel.houseDescription,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )


        }

        //  item features
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  features
            LazyRow(
                content = {
                    items(
                        items = houseModel.houseFeatures
                    ) {
                        PillButton(
                            value = it,
                            backgroundColor = MaterialTheme.colorScheme.onPrimary,
                            iconColor = MaterialTheme.colorScheme.primary,
                            paddingHorizontal = 8.dp,
                            paddingVertical = 8.dp
                        ) {

                        }
                    }
                },
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(2f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            //  viewmore button
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = {
                    onViewClick()
                }) {
                    Text(
                        text = "view",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

        }

    }


}















