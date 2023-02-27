package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel

@Composable
fun AlphabetItem(
    alphabet: String,
    apartmentList: List<Apartment>
) {

    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        //  title
        Text(
            text = alphabet,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        LazyColumn(
            content = {
                items(
                    items = apartmentList
                ) {

                    ApartmentItem(
                        apartment = it,
                        primaryColor = MaterialTheme.colorScheme.primary,
                        tertiaryColor = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(MaterialTheme.colorScheme.onSecondary)
                            .padding(16.dp)
                    )

                }
            },
            state = listState,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
        )

    }

}