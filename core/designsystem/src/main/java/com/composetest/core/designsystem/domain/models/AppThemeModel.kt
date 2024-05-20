package com.composetest.core.designsystem.domain.models

import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.composetest.core.designsystem.domain.emuns.AppTheme
import com.composetest.core.designsystem.domain.models.AppThemeModel.DefaultThemeColors.defaultDarkScrim
import com.composetest.core.designsystem.domain.models.AppThemeModel.DefaultThemeColors.defaultLightScrim

data class AppThemeModel(
    val theme: AppTheme = AppTheme.AUTO,
    val dynamicColors: Boolean = false,
    val customTheme: AppTheme? = null
) {
    val isDarkMode
        @Composable get() = if (theme == AppTheme.AUTO) {
            isSystemInDarkTheme()
        } else {
            theme == AppTheme.DARK
        }

    val systemBarStyles
        get() = when (customTheme ?: theme) {
            AppTheme.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultDarkScrim)
            AppTheme.DARK -> SystemBarStyle.dark(Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultDarkScrim) { true }
            AppTheme.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultLightScrim) { false }
        }

    private object DefaultThemeColors {
        // Same that `EdgeToEdge.kt`
        val defaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
        val defaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
    }
}