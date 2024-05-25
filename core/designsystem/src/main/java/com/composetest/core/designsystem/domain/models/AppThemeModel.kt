package com.composetest.core.designsystem.domain.models

import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.composetest.core.designsystem.domain.emuns.Theme
import com.composetest.core.designsystem.domain.models.AppThemeModel.DefaultThemeColors.defaultDarkScrim
import com.composetest.core.designsystem.domain.models.AppThemeModel.DefaultThemeColors.defaultLightScrim

data class AppThemeModel(
    val theme: Theme = Theme.AUTO,
    val dynamicColors: Boolean = false,
    val customTheme: Theme? = null
) {
    val isDarkMode
        @Composable get() = if (theme == Theme.AUTO) {
            isSystemInDarkTheme()
        } else {
            theme == Theme.DARK
        }

    val systemBarStyles
        get() = when (customTheme ?: theme) {
            Theme.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultDarkScrim)
            Theme.DARK -> SystemBarStyle.dark(Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultDarkScrim) { true }
            Theme.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultLightScrim) { false }
        }

    private object DefaultThemeColors {
        // Same that `EdgeToEdge.kt`
        val defaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
        val defaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
    }
}