package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.ui.theme.PinkAccent

@Composable
fun HouseItemDetails(
    houseModel: HouseModel,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
    ) {

        //  item units left and item price
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  units left
            Text(
                text = "${houseModel.houseUnits} units left",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = if (houseModel.houseUnits.toInt() < 5)
                    PinkAccent
                else
                    MaterialTheme.colorScheme.onSecondaryContainer,
            )

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

    }


}















