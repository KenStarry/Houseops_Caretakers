package com.example.houseopscaretakers.feature_landlord.feature_home.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_landlord.feature_home.domain.model.LndHomeEvents
import com.example.houseopscaretakers.feature_landlord.feature_home.presentation.components.LndHomeAppBar
import com.example.houseopscaretakers.feature_landlord.feature_home.presentation.viewmodel.LndHomeViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordHome(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val lndHomeVM: LndHomeViewModel = hiltViewModel()

    lndHomeVM.onEvent(LndHomeEvents.GetLandlordDetails(
        email = coreVM.currentUser()?.email ?: "no user"
    ))

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val currentHour by remember {
        mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY))
    }

    Scaffold(
        topBar = {
            LndHomeAppBar(
                context = context,
                imageUri = lndHomeVM.landlordDetails.value?.landlordImage?.toUri()
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {



            }
        }

    }

}
























