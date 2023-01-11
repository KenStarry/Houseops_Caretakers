package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton

@Composable
fun HouseViewAppbar(
    modifier: Modifier = Modifier,
    category: String,
    onBackPressed: () -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {

        //  back arrow
        IconBtn(
            icon = Icons.Outlined.ArrowBack,
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {
            onBackPressed()
        }

        PillButton(
            value = category,
            backgroundColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
            onClick = {}
        )

    }
}