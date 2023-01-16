package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.houseopscaretakers.core.domain.model.PillBtnModel
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.example.houseopscaretakers.ui.theme.BlueAccent
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparent
import com.example.houseopscaretakers.ui.theme.BlueAccentTransparentAlt

@Composable
fun HouseItem(
    house: HouseModel,
    context: Context,
    onViewClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .clickable { onViewClick() },
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            //  house category & units
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  house category
                PillButton(
                    value = house.houseCategory,
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    icon = Icons.Outlined.Apartment,
                    iconColor = MaterialTheme.colorScheme.primary,
                    onClick = {}
                )

                //  units count
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) { append(house.houseUnits) }

                        append(" units")
                    },
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 2.sp
                )

            }

            //  house images
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  images text
                Text(
                    text = "Images",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                IconBtn(
                    icon = Icons.Outlined.ArrowRight,
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onClick = {}
                )

                //  house images array
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    var spacing: Dp = 0.dp

                    house.houseImageUris.forEach {

                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                        ) {

                            Spacer(modifier = Modifier.width(spacing))

                            //  image 1
                            CoilImage(
                                context = context,
                                imageUri = it.toUri(),
                                placeholder = com.example.houseopscaretakers.R.drawable.houseops_light_final,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .size(50.dp)
                                    .border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            )
                        }

                        spacing += 16.dp
                    }

                }

            }

            //  house view icon
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  item icons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val houseItemPillIcons = listOf(
                        PillBtnModel("0", Icons.Outlined.Notifications),
                        PillBtnModel("", Icons.Outlined.Notes),
                        PillBtnModel(house.houseImageUris.size.toString(), Icons.Outlined.Image),
                        PillBtnModel(
                            house.houseFeatures.size.toString(),
                            Icons.Outlined.FlashOn
                        )
                    )

                    houseItemPillIcons.forEach {
                        PillButton(
                            value = it.pillText,
                            backgroundColor = BlueAccentTransparentAlt,
                            icon = it.pillIcon,
                            iconColor = MaterialTheme.colorScheme.primary,
                            paddingHorizontal = 4.dp,
                            paddingVertical = 4.dp
                        ) {

                        }
                    }
                }

                //  item view button
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "View",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                    IconBtn(
                        icon = Icons.Outlined.NavigateNext,
                        shape = CircleShape,
                        containerColor = BlueAccentTransparentAlt,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            onViewClick()
                        }
                    )
                }

            }

        }

    }

}

@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun HomeItemPrev() {
    HouseItem(
        house = HouseModel(
            "One Bedroom",
            emptyList(),
            "7",
            emptyList(),
            "A beautiful house for you and your kin"
        ),
        LocalContext.current
    ) {

    }
}
















