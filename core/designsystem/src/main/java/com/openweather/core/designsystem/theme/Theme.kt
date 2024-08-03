package com.openweather.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun OpenWeatherTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(dynamicColor, isDarkMode)
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = {
            Surface(content = content)
        }
    )
}

@Composable
private fun getColorScheme(dynamicColor: Boolean, isDarkMode: Boolean) = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    isDarkMode -> darkColorScheme
    else -> lightColorScheme
}