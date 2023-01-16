package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparentAlt
import com.example.houseopscaretakers.ui.theme.RedOrange
import com.example.houseopscaretakers.ui.theme.RedOrangeDull

@Composable
fun HouseDeleteDialog(
    house: HouseModel,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  house category
        IconBtn(
            icon = Icons.Outlined.DeleteForever,
            shape = CircleShape,
            containerColor = RedOrangeDull,
            contentColor = RedOrange
        ) {}

        //  delete title
        Text(
            text = "Delete Category",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        //  delete description
        Text(
            text = "Would you like to delete the '${house.houseCategory}' house category?" +
                    " This action is permanent and cannot be undone!",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End
        ) {

            TextButton(onClick = { onCancel() }) {
                Text(text = "cancel")
            }

            Spacer(modifier = Modifier.width(8.dp))

            TextButton(onClick = { onConfirm() }) {
                Text(text = "confirm")
            }

        }

    }

}

@Preview
@Composable
fun DeleteDialogPrev() {
    HouseDeleteDialog(HouseModel(), {}, {})
}