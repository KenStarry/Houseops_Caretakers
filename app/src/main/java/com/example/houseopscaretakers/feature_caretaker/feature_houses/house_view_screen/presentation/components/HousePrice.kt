package com.example.houseopscaretakers.feature_caretaker.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HousePrice(
    house: HouseModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = buildAnnotatedString {
                append("Ksh ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                ) { append(house.housePrice) }
            },
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )
        
        Spacer(modifier = Modifier.width(8.dp))

        PillButton(
            value = house.housePriceCategory,
            icon = Icons.Outlined.AlternateEmail,
            iconColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            paddingVertical = 8.dp,
            paddingHorizontal = 8.dp
        ) {

        }

    }

}