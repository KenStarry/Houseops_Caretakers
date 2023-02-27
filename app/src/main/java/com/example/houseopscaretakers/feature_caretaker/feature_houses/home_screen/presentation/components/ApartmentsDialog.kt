package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.presentation.components.CustomAlertDialog
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HomeEvents
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.components.AlphabetItem
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.utils.LndHomeConstants

@Composable
fun ApartmentsDialog(
    homeVM: HomeViewModel,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {

    //  get all apartments
    homeVM.onHomeScreenEvent(HomeEvents.GetApartments(
        response = {}
    ))

    val listState = rememberLazyListState()

    CustomAlertDialog(
        icon = Icons.Outlined.Apartment,
        primaryColor = MaterialTheme.colorScheme.primary,
        tertiaryColor = MaterialTheme.colorScheme.tertiary,
        title = "Apartments",
        content = {
            LazyColumn(
                content = {
                    items(
                        items = LndHomeConstants.alphabets
                    ) {

                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
        },
        onConfirm = { onConfirm() },
        onDismiss = { onDismiss() })
}