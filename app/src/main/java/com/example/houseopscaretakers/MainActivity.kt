package com.example.houseopscaretakers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.utils.UtilsViewModel
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_authentication.login.presentation.LoginScreen
import com.example.houseopscaretakers.feature_houses.MainScreen
import com.example.houseopscaretakers.feature_houses.home_screen.presentation.HomeScreen
import com.example.houseopscaretakers.navigation.Direction
import com.example.houseopscaretakers.navigation.Screen
import com.example.houseopscaretakers.navigation.graphs.RootNavGraph
import com.example.houseopscaretakers.ui.theme.HouseopsCaretakersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm: UtilsViewModel by viewModels()
        var keepSplashScreenOn = true

        //  initialize our Splash screen
        installSplashScreen().apply {
//            this.setKeepOnScreenCondition { vm.isShowing.value }
        }
        setContent {
            HouseopsCaretakersTheme {
                SetupNavGraph()
            }
        }
    }

}

@Composable
fun SetupNavGraph(
    coreViewModel: CoreViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    RootNavGraph(
        navHostController = navController,
        isLoggedIn = coreViewModel.isUserLoggedIn()
    )
}













