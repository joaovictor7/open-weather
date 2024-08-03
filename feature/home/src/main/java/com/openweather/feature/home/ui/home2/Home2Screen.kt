package com.openweather.feature.home.ui.home2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.openweather.core.ui.interfaces.Command
import com.openweather.core.ui.interfaces.Screen
import com.openweather.core.designsystem.theme.OpenWeatherTheme

internal object Home2Screen : Screen<Home2UiState, Home2CommandReceiver> {

    @Composable
    override fun invoke(
        uiState: Home2UiState,
        onExecuteCommand: (Command<Home2CommandReceiver>) -> Unit
    ) {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
        ) {
            Button(onClick = { onExecuteCommand(ReturnHome) }) {
                Text(text = uiState.t)
            }
            Text(text = "Home2")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OpenWeatherTheme {
        Home2Screen(uiState = Home2UiState()) { }
    }
}