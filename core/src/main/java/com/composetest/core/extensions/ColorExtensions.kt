package com.composetest.core.extensions

import androidx.compose.ui.graphics.Color

fun Color.opacity(value: Float) = if (value in 0f..1f)
    this.copy(alpha = value)
else
    throw IndexOutOfBoundsException("value aren't between 0 and 1")