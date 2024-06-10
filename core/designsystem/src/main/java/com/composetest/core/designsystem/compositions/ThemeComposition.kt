package com.composetest.core.designsystem.compositions

import androidx.compose.runtime.compositionLocalOf
import com.composetest.common.enums.Theme

val LocalThemeProvider = compositionLocalOf { Theme.AUTO }
