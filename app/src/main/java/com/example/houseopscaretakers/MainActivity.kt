package com.example.houseopscaretakers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_main_screen.MainScreen
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph
import com.example.houseopscaretakers.ui.theme.HouseopsCaretakersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  initialize our Splash screen
        installSplashScreen()

        setContent {
            HouseopsCaretakersTheme {
                MainScreen(rememberNavController())
            }
        }
    }

}













