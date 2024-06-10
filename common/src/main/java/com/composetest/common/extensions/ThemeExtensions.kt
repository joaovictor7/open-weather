package com.composetest.common.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.composetest.common.enums.Theme

val Theme.isDarkMode
    @Composable get() = when (this) {
        Theme.AUTO -> isSystemInDarkTheme()
        else -> this == Theme.DARK
    }