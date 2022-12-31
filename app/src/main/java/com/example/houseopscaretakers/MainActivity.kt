package com.example.houseopscaretakers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.feature_authentication.login.presentation.LoginScreen
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.SignUpScreen
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.ui.theme.HouseopsCaretakersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //  initialize our Splash screen
        installSplashScreen().apply {
            //  check if the user is already logged in
//            this.setKeepOnScreenCondition()
        }

        super.onCreate(savedInstanceState)
        setContent {
            HouseopsCaretakersTheme {
                HomeScreen()
            }
        }
    }
}