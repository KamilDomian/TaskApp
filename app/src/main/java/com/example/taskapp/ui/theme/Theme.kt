@file:Suppress("DEPRECATION")

package com.example.taskapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LightBlue,
    primaryContainer = Blue,
    surface = DarkSurface
)

private val LightColorScheme = lightColorScheme(
   // primary = Blue,
   // onPrimary = LightGreen,
   // primaryContainer = Color.Red,
   // onPrimaryContainer = Color.Yellow,
  //  surface = Color.Yellow,
  //  onSurface = Color.Magenta,
   // surfaceVariant = Color.DarkGray,
  //  onSurfaceVariant = Color.Green,
  //  outline = Color.Red,
  //  outlineVariant = Color.Yellow

    primary = Blue,
    primaryContainer = LightBlue,
    surface = LightSurface
)

@Composable
fun TasksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode){
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}