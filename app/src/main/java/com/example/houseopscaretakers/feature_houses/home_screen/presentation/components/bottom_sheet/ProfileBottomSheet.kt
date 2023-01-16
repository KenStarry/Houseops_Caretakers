package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.presentation.components.CoilImage

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

    }

}






















