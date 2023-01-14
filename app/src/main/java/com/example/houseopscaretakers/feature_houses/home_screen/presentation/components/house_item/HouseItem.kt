package com.example.houseopscaretakers.feature_houses.home_screen.presentation.components.house_item

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel

@Composable
fun HouseItem(
    house: HouseModel,
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
        ) {

            //  row

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
    HouseItem(house = HouseModel()) {

    }
}
















