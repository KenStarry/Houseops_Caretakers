package com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.components.bottom_sheet

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseopscaretakers.feature_caretaker.core.presentation.components.FormTextField
import com.example.houseopscaretakers.feature_caretaker.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_caretaker.core.presentation.utils.NumberCommaTransformation
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.ui.theme.WhiteBackground

@Composable
fun HousePrice(
    viewModel: HomeViewModel = hiltViewModel(),
    onPriceEntered: (price: String) -> Unit,
    onPriceCategory: (category: String) -> Unit,
) {

    var isPriceCategoriesVisible by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  units title
        Text(
            text = "Price",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            FormTextField(
                inputType = KeyboardType.Number,
                onValueChange = {

                    if (it.length <= Long.MAX_VALUE.toString().length && it.isDigitsOnly()) {
                        onPriceEntered(it)
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                },
                placeholder = "Ksh...",
                leadingIcon = Icons.Outlined.Payment,
                imeAction = ImeAction.Default,
                visualTransformation = NumberCommaTransformation(),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            )

            //  pill button
            PillButton(
                value = viewModel.priceCategory.value,
                backgroundColor = MaterialTheme.colorScheme.tertiary,
                icon = Icons.Outlined.AlternateEmail,
                iconColor = MaterialTheme.colorScheme.primary,
            ) {
                isPriceCategoriesVisible = !isPriceCategoriesVisible
            }

        }

        //  price categories
        AnimatedVisibility(visible = isPriceCategoriesVisible) {

            LazyRow(
                contentPadding = PaddingValues(8.dp),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {

                    itemsIndexed(
                        com.example.houseopscaretakers.feature_caretaker.core.Constants.priceCategories
                    ) { index, category ->

                        val priceCategory = category

                        PillButton(
                            value = category,

                            backgroundColor = if (priceCategory == viewModel.priceCategory.value)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.tertiary,

                            icon = Icons.Outlined.AlternateEmail,

                            iconColor = if (priceCategory == viewModel.priceCategory.value)
                                WhiteBackground
                            else
                                MaterialTheme.colorScheme.primary,
                        ) {

                            viewModel.onBottomSheetEvent(
                                BottomSheetEvents.TogglePriceCategory(
                                    category
                                )
                            )

                            onPriceCategory(viewModel.priceCategory.value)

                            isPriceCategoriesVisible = false
                        }

                        Spacer(modifier = Modifier.width(8.dp))
                    }
                })

        }

    }

}















