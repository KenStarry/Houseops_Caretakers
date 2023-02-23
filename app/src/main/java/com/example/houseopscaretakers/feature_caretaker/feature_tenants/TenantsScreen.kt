package com.example.houseopscaretakers.feature_caretaker.feature_tenants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.presentation.components.MyLottie

@Composable
fun TenantsScreen(
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MyLottie(
            lottieRaw = R.raw.rocket,
            isPlaying = true,
            iterations = 5,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.7f)
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Coming Soon...",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

    }

}