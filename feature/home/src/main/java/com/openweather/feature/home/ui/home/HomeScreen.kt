package com.openweather.feature.home.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openweather.core.designsystem.components.graphics.SimpleScatterPlotGraphic
import com.openweather.core.designsystem.dimensions.spacings
import com.openweather.core.designsystem.theme.OpenWeatherTheme
import com.openweather.core.ui.interfaces.Command
import com.openweather.core.ui.interfaces.Screen
import com.openweather.feature.home.R

internal object HomeScreen : Screen<HomeUiState, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        Scaffold { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.feature_home_weather_forecast_now),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.padding(horizontal = spacings.four))
                    Text(
                        text = "16º",
                        style = MaterialTheme.typography.displayMedium
                    )
                }
                SimpleScatterPlotGraphic(
                    modifier = Modifier
                        .padding(all = spacings.sixteen)
                        .height(200.dp),
                    yPoints = listOf(1f),
                    labelCount = 2f,
                    minLabel = 1f,
                    maxLabel = 30f
                )
                val teste = listOf(
                    listOf("12", "13", "14", "14", "14", "14", "14", "14"),
                    listOf("12", "13", "14", "14", "14", "14", "14", "14"),
                    listOf("12", "13", "14", "14", "14", "14", "14", "14"),
                    listOf("12", "13", "14", "14", "14", "14", "14", "14"),
                    listOf("12", "13", "14", "14", "14", "14", "14", "14")
                )
                FutureWeatherForecasts(teste)
            }
        }
    }
}

@Composable
private fun FutureWeatherForecasts(forecasts: List<List<String>>) = forecasts.forEach {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Teste")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            it.forEach { temp ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = temp)
                    Text(text = "dia")
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OpenWeatherTheme {
        HomeScreen(uiState = HomeUiState())  { }
    }
}