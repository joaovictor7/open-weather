package com.openweather.core.designsystem.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.openweather.core.designsystem.theme.OpenWeatherTheme

@Composable
fun Button(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    OpenWeatherTheme {
        Button(text = "Label") { }
    }
}