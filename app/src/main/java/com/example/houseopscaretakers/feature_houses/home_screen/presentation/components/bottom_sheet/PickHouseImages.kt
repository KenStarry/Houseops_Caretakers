package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.bottom_sheet

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.sharp.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.BottomSheetEvents
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.ui.theme.PinkAccent

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
            viewModel.onBottomSheetEvent(BottomSheetEvents.UpdateGalleryImages(uris = it))
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
                                    viewModel.onBottomSheetEvent(
                                        BottomSheetEvents.DeleteImageFromList(
                                            index
                                        )
                                    )
                                }
                            )
                        }
                    },
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                )
            }

            PillButton(
                value = "Pick Images",
                backgroundColor = MaterialTheme.colorScheme.onSecondary,
                icon = Icons.Outlined.Image,
                iconColor = MaterialTheme.colorScheme.primary
            ) {
                launcher.launch("image/*")
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
            imageUri = imageUri,
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