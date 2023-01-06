package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cabin
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HouseItemImage(
    modifier: Modifier = Modifier,
    houseModel: HouseModel
) {

    val context = LocalContext.current

    Box(
        modifier = modifier
    ) {

        //  image covering the area
        if (houseModel.houseImageUris.isNotEmpty()) {
            CoilImage(
                context = context,
                imageUri = houseModel.houseImageUris[0].toUri(),
                placeholder = R.drawable.houseops_light_final,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        //  black transparent layer on top
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        //  pill button for the house category & number of images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  house category
            PillButton(
                value = houseModel.houseCategory,
                backgroundColor = MaterialTheme.colorScheme.onSecondary,
                iconColor = MaterialTheme.colorScheme.primary,
                icon = Icons.Outlined.Cabin,
                onClick = {
                    //  do something
                }
            )

            //  house images count
            PillButton(
                value = houseModel.houseImageUris.size.toString(),
                backgroundColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
                iconColor = MaterialTheme.colorScheme.primary,
                icon = Icons.Outlined.Add,
                onClick = {
                    //  do something
                }
            )

        }

    }
}












