package com.example.houseopscaretakers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_caretaker.feature_main_screen.MainScreen
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph
import com.example.houseopscaretakers.ui.theme.HouseopsCaretakersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)

        val isLoading = mutableStateOf(true)

        //  initialize our Splash screen
        splash.setKeepOnScreenCondition { isLoading.value }

        setContent {
            HouseopsCaretakersTheme {
//                MainScreen(rememberNavController())
                val coreVM: CoreViewModel = hiltViewModel()
                val userType = coreVM.userTypeFlow.collectAsState(initial = null).value

                if (userType != null) {
                    isLoading.value = false
                }

                RootNavGraph(
                    navHostController = rememberNavController(),
                    isLoggedIn = coreVM.isUserLoggedIn(),
                    userType = userType
                )
            }
        }
    }

}













