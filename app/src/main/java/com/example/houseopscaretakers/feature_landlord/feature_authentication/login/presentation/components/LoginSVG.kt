package com.example.houseopscaretakers.feature_landlord.feature_authentication.login.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.houseopscaretakers.R

@Composable
fun LoginSVG(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = R.drawable.undraw_welcoming_re_x0qo),
        contentDescription = "Welcome SVG",
        modifier = modifier
    )
}