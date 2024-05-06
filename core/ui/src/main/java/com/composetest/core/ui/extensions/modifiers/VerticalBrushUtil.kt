package com.composetest.core.ui.extensions.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun Modifier.verticalTopBackgroundBrush(
    isDarkMode: Boolean = isSystemInDarkTheme()
) = if (!isDarkMode) {
    val colorStops = arrayOf(
        0.1f to MaterialTheme.colorScheme.primary,
        0.99f to MaterialTheme.colorScheme.surface,
    )
    Modifier.background(brush = Brush.verticalGradient(colorStops = colorStops))
} else this