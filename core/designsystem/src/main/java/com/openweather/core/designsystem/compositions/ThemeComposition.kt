package com.openweather.core.designsystem.compositions

import androidx.compose.runtime.compositionLocalOf
import com.openweather.common.enums.Theme

val LocalThemeProvider = compositionLocalOf { Theme.AUTO }
