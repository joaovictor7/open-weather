package com.openweather.common.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.openweather.common.enums.Theme

val Theme.isDarkMode
    @Composable get() = when (this) {
        Theme.AUTO -> isSystemInDarkTheme()
        else -> this == Theme.DARK
    }