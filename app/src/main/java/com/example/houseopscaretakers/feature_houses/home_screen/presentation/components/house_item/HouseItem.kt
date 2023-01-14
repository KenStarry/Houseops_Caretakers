package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.IconBtn
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

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
                .padding(16.dp)
                .clickable { },
            verticalArrangement = Arrangement.SpaceEvenly
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
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
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

            Spacer(modifier = Modifier.height(16.dp))

            //  house images
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

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
                            )
                        }

                        spacing += 8.dp
                    }

                }

            }

        }

    }


//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(16.dp))
//            .fillMaxSize(1f)
//            .height(400.dp)
//            .background(MaterialTheme.colorScheme.onPrimary)
//    ) {
//
//        //  main image
//        HouseItemImage(
//            houseModel = house,
//            modifier = Modifier
//                .clip(RoundedCornerShape(16.dp))
//                .fillMaxWidth()
//                .height(250.dp)
//        )
//
//        //  house item details
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(150.dp))
//
//            HouseItemDetails(
//                houseModel = house,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .fillMaxWidth(0.9f)
//                    .height(200.dp)
//                    .background(MaterialTheme.colorScheme.onSecondary)
//                    .padding(16.dp),
//                onViewClick = {
//                    onViewClick()
//                }
//            )
//        }
//
//
//    }

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
















