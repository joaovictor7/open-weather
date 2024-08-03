package com.openweather.feature.home.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.openweather.core.designsystem.theme.OpenWeatherTheme
import com.openweather.core.ui.interfaces.Command
import com.openweather.core.ui.interfaces.Screen

internal object HomeScreen : Screen<HomeUiState, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        Scaffold { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {

            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OpenWeatherTheme {
        HomeScreen(uiState = HomeUiState()) { }
    }
}