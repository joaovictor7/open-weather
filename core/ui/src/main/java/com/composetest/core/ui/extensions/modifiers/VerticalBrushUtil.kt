package com.composetest.core.ui.extensions.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun Modifier.verticalBackgroundBrush() = if (!isSystemInDarkTheme()) {
    val colorStops = arrayOf(
        0.6f to MaterialTheme.colorScheme.surface,
        1f to MaterialTheme.colorScheme.primary,
    )
    Modifier.background(brush = Brush.verticalGradient(colorStops = colorStops))
} else this