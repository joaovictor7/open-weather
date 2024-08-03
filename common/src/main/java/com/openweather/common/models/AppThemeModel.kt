package com.openweather.common.models

import android.graphics.Color
import androidx.activity.SystemBarStyle
import com.openweather.common.models.AppThemeModel.DefaultThemeColors.defaultDarkScrim
import com.openweather.common.models.AppThemeModel.DefaultThemeColors.defaultLightScrim
import com.openweather.common.enums.Theme

data class AppThemeModel(
    val theme: Theme = Theme.AUTO,
    val dynamicColors: Boolean = false,
    val customTheme: Theme? = null
) {
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