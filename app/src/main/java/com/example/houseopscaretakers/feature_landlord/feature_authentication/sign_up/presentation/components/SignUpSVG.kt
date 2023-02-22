package com.example.houseopscaretakers.feature_landlord.feature_authentication.sign_up.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.houseopscaretakers.R

@Composable
fun SignUpSVG(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {

        Image(
            painter = painterResource(id = R.drawable.undraw_new_ideas_re_asn4),
            contentDescription = "Sign Up SVG",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}