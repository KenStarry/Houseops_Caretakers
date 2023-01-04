package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.FormTextField
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseFeatures
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.ui.theme.BlueAccentLight
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent
import com.example.houseopscaretakers.ui.theme.PinkAccent

@Composable
fun AddHouseBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  Bottom sheet Icon
        Icon(
            imageVector = Icons.Outlined.Minimize,
            contentDescription = "Dash icon",
            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        //  title
        Text(
            text = "House Category",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        //  house category
        HouseCategory(viewModel)

        //  pick house images
        PickHouseImages(viewModel)

        UnitsRemaining() {}

        //  House Features
        HouseFeatures()
    }
}

//  House Category
@Composable
fun ColumnScope.HouseCategory(
    viewModel: HomeViewModel
) {

    var toggleCategories by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  toggle row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Apartment,
                contentDescription = "Houe category icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
            )

            //  drop down bar
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .clickable {
                        //  toggle house categories menu
                        toggleCategories = !toggleCategories
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  choose house category
                Text(
                    text = viewModel.pillName.value,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                    modifier = Modifier
                        .weight(2f)
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = "Dropdown arrow",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                )

            }

        }

        //  categories menu
        AnimatedVisibility(
            visible = toggleCategories,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    items(
                        items = Constants.houseCategories
                    ) {

                        val pillCategory = it.pillText

                        // create a pill button
                        PillButton(
                            value = it.pillText,
                            icon = it.pillIcon,

                            backgroundColor = if (pillCategory == viewModel.pillName.value)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSecondary,

                            iconColor = if (pillCategory == viewModel.pillName.value)
                                Color.Black
                            else
                                BlueAccentLight,

                            onClick = {
                                //  toggle a blue color on the pill button
                                viewModel.onEvent(BottomSheetEvents.TogglePillCategory(pillCategory))

                                //  toggle visibility
                                toggleCategories = false
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )
        }

    }
}

//  pick house images
@Composable
fun PickHouseImages(
    viewModel: HomeViewModel
) {

    val state = viewModel.selectedImagesState
    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetMultipleContents()
        ) {
            viewModel.onEvent(BottomSheetEvents.UpdateGalleryImages(uris = it))
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  images title
        Text(
            text = "Images",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        //  images section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AnimatedVisibility(visible = state.listOfSelectedImages.isNotEmpty()) {
                LazyRow(
                    content = {
                        itemsIndexed(
                            items = state.listOfSelectedImages
                        ) { index, uri ->

                            //  display the images in an image container
                            ImageContainer(
                                imageUri = uri,
                                onDelete = {
                                    viewModel.onEvent(BottomSheetEvents.DeleteImageFromList(index))
                                }
                            )
                        }
                    },
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                )
            }

            //  pick image from gallery button
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  pick image text
                Text(
                    text = "Pick Images",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Icon(
                    imageVector = Icons.Sharp.ArrowRight,
                    contentDescription = "Arrow Icon"
                )

                //  pick image button
                IconBtn(
                    icon = Icons.Outlined.Image,
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                    onClick = {
                        launcher.launch("image/*")
                    }
                )

            }

        }

    }

}

@Composable
fun ImageContainer(
    imageUri: Uri,
    onDelete: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        CoilImage(
            context = context,
            imageUriString = imageUri,
            placeholder = R.drawable.houseops_dark_final,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(150.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Delete Image")

            //  delete button
            IconBtn(
                icon = Icons.Outlined.DeleteOutline,
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = PinkAccent,
                onClick = {
                    //  delete the image from the arraylist
                    onDelete()
                },
                modifier = Modifier
            )
        }
    }

}

//  Units remaining
@Composable
fun UnitsRemaining(
    unitsRemaining: (Int) -> Unit
) {

    var unitsRem by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        //  units title
        Text(
            text = "Units",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            FormTextField(
                inputType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                onValueChange = {

                    if (it.isBlank() || it.isEmpty() || it != "." || it != ",") {
                        unitsRem = 0
                    } else {
                        unitsRem = it.trim().toInt()
                        unitsRemaining(unitsRem)
                    }

                },
                placeholder = "Units Remaining",
                leadingIcon = Icons.Outlined.Numbers,
                modifier = Modifier
                    .wrapContentWidth()
            )
        }

    }

}


//  house features
@Composable
fun HouseFeatures() {

    var houseFeatures by remember {
        mutableStateOf(
            listOf(
                HouseFeatures("Security", Icons.Outlined.Security, false),
                HouseFeatures("Water", Icons.Outlined.Water, false),
                HouseFeatures("Electricity", Icons.Outlined.Thunderstorm, false),
                HouseFeatures("Parking", Icons.Outlined.Park, false),
                HouseFeatures("Shops", Icons.Outlined.Shop, false),
                HouseFeatures("Rooftop", Icons.Outlined.Roofing, false)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {

        //  features title
        Text(
            text = "Features",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Select all that apply.",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            content = {
                itemsIndexed(
                    houseFeatures
                ) { index, item ->


                    PillButton(
                        value = item.featureName,

                        backgroundColor = if (item.featureSelected)
                            BlueAccentTransparent
                        else
                            MaterialTheme.colorScheme.onSecondary,

                        iconColor = MaterialTheme.colorScheme.primary,
                        icon = item.featureIcon,
                        onClick = {
                            houseFeatures = houseFeatures.mapIndexed { j, feature ->
                                //  check if index == j
                                if (index == j) {
                                    //  only change the selected value and pass it to our copied feature
                                    feature.copy(featureSelected = !item.featureSelected)
                                } else
                                    feature
                            }
                        }
                    )
                }
            },
            state = rememberLazyListState(),
            contentPadding = PaddingValues(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = true
        )

    }
}


















