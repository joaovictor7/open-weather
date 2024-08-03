package com.openweather.core.designsystem.components.textfields.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

internal fun textFieldHelpedText(text: String?): @Composable (() -> Unit)? =
    text?.let { { Text(text = it) } }
