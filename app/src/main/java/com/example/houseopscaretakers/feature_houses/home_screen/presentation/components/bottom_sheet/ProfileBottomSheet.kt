package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.PillBtnModel
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.PillButton

@Composable
fun ProfileBottomSheet(
    caretaker: Caretaker?,
    context: Context,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CoilImage(
            context = context,
            imageUri = caretaker?.caretakerImage?.toUri(),
            placeholder = R.drawable.houseops_light_final,
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp)
        )

        LazyRow(content = {
            items(
                items = listOf(
                    PillBtnModel("Notifications", Icons.Outlined.Notifications),
                    PillBtnModel("Settings", Icons.Outlined.Settings),
                    PillBtnModel("Verified Status", Icons.Outlined.Verified)
                )
            ) {
                PillButton(
                    value = it.pillText,
                    backgroundColor = MaterialTheme.colorScheme.tertiary,
                    icon = it.pillIcon,
                    iconColor = MaterialTheme.colorScheme.primary,
                    onClick = {}
                )
            }
        })

    }

}






















