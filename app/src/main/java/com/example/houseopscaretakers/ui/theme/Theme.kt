package com.example.houseopscaretakers.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = BlueAccent,
    secondary = PinkAccent,
    tertiary = BlueAccentLight,
    onPrimary = BlackBackgroundAlt,
    onSecondary = DarkBackground,
    onSecondaryContainer = TextWhite900
)

private val LightColorScheme = lightColorScheme(
    primary = BlueAccent,
    secondary = PinkAccent,
    tertiary = BlueAccentLight,
    onPrimary = GreyBackground,
    onSecondary = WhiteBackground,
    onSecondaryContainer = TextBlack900

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun HouseopsCaretakersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    //  using accompanyst library
    val systemUiController = rememberSystemUiController()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {

            //  status bar background
            systemUiController.setStatusBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )

            //  naviagtion bar
            systemUiController.setNavigationBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )

        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}