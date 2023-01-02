package com.example.houseopscaretakers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_authentication.login.presentation.LoginScreen
import com.example.houseopscaretakers.feature_authentication.sign_up.presentation.SignUpScreen
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph
import com.example.houseopscaretakers.ui.theme.HouseopsCaretakersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {

        //  initialize our Splash screen
        installSplashScreen().apply {
        }

        super.onCreate(savedInstanceState)
        setContent {
            HouseopsCaretakersTheme {

                val coreViewModel: CoreViewModel = hiltViewModel()
                navController = rememberNavController()

                if (coreViewModel.isUserLoggedIn()) {
                    RootNavGraph(
                        navHostController = navController,
                        startDestinationRoute = Constants.HOME_ROUTE
                    )
                } else {
                    RootNavGraph(
                        navHostController = navController,
                        startDestinationRoute = Constants.AUTHENTICATION_ROUTE
                    )
                }

//                val coreViewModel: CoreViewModel = hiltViewModel()
//
//                if (coreViewModel.isUserLoggedIn())
//                    HomeScreen(navController)
//                else
//                    LoginScreen(navHostController = navController)
            }
        }
    }
}













