package com.example.houseopscaretakers.feature_caretaker.feature_main_screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.navigation.BottomNavScreens

//  navigation bar item
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.MainBottomBarItem(
    navHostController: NavHostController,
    currentDestination: NavDestination?,
    screen: BottomNavScreens
) {

    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = {
            navHostController.navigate(route = screen.route) {
                popUpTo(BottomNavScreens.Home.route)
                launchSingleTop = true
            }
        },

        alwaysShowLabel = true,

        label = {
            Text(text = screen.title)
        },

        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Bottom Nav Icon"
            )
        },

        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            indicatorColor = MaterialTheme.colorScheme.tertiary
        ),

        )

}


















