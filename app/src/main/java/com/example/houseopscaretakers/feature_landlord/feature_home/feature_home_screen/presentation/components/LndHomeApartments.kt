package com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.utils.LndHomeConstants
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel

@Composable
fun LndHomeApartments(
    lndHomeVM: LndHomeViewModel
) {

    val alphabetListState = rememberLazyListState()

    LazyColumn(
        content = {
            items(
                items = LndHomeConstants.alphabets
            ) {
                //  alphabet item
                AlphabetItem(
                    alphabet = it.toString(),
                    apartmentList = lndHomeVM.landlordApartments.value.filter { apartment ->
                        apartment.apartmentName.first() == it
                    }
                )
            }
        },
        state = alphabetListState
    )
}