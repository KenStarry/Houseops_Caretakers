package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material.icons.sharp.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.core.presentation.utils.getMultipleImagesFromGallery
import com.example.houseopscaretakers.core.presentation.utils.getSingleImageFromGallery
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HomeEvents
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.ui.theme.BlueAccentLight

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
                                viewModel.onEvent(HomeEvents.TogglePillCategory(pillCategory))

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

    var imageUriList by remember {
        mutableStateOf<List<Uri>?>(null)
    }

    val launcher = getMultipleImagesFromGallery(onResult = {
        imageUriList = it
    })

    var isImagesSectionVisible by remember {
        mutableStateOf(imageUriList.isNullOrEmpty())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  images section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AnimatedVisibility(visible = isImagesSectionVisible) {
                imageUriList?.let {
                    LazyRow(
                        content = {
                            items(
                                items = it
                            ) {

                                //  display the images in an image container
                                ImageContainer(imageUri = it)
                            }
                        },
                        state = rememberLazyListState(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    )
                }
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
    imageUri: Uri
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {
        CoilImage(
            context = context,
            imageUriString = imageUri,
            placeholder = R.drawable.houseops_dark_final,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(150.dp)
        )

        //  delete button
    }

}





















