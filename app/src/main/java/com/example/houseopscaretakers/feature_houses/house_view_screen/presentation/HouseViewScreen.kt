package com.example.houseopscaretakers.feature_houses.house_view_screen.presentation

import android.view.RoundedCorner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.presentation.components.PillButton
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.viewmodels.HomeViewModel
import com.example.houseopscaretakers.feature_houses.house_view_screen.presentation.components.*
import com.example.houseopscaretakers.ui.theme.RedOrangeDull
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(
    ExperimentalPagerApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    houseCategory: String,
    apartmentName: String
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val coreViewModel: CoreViewModel = hiltViewModel()

    //  get the current house
    coreViewModel.onEvent(
        CoreEvents.GetHouse(
            apartmentName = apartmentName,
            category = houseCategory
        )
    )
    val currentHouse = coreViewModel.currentHouse

    Scaffold(
        topBar = {
            HouseViewAppbar(
                category = houseCategory,
                units = currentHouse?.houseUnits ?: "0",
                onBackPressed = { /*TODO*/ },
                onHouseEdit = {}
            )
        }
    ) { contentPadding ->

        //  Box to hold our contents
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                //  Image & Navigation icons
                currentHouse?.let {

                    //  our house pager
                    HousePager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                        currentHouseImages = it.houseImageUris
                    )

                    //  house price
                    HousePrice(it)

                    //  house units
                    HouseUnits(
                        house = it,
                        isEditMode = false
                    )

                    //  house features
                    HouseFeatures(house = it)

                    //  house description
                    HouseDescription(it)

                }
            }
        }
    }
}























