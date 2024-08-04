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
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openweather.core.designsystem.components.graphics.SimpleScatterPlotGraphic
import com.openweather.core.designsystem.dimensions.components
import com.openweather.core.designsystem.dimensions.spacings
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
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {
                CurrentWeatherForecast(uiState = uiState)
                Spacer(Modifier.height(30.dp))
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
private fun CurrentWeatherForecast(uiState: HomeUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = AbsoluteAlignment.Right) {
            Text(
                text = uiState.weatherNowWithCity,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = uiState.weatherNow,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(Modifier.padding(horizontal = spacings.ten))
        Text(
            text = uiState.temperatureNow,
            style = MaterialTheme.typography.displaySmall
        )
    }
    SimpleScatterPlotGraphic(
        modifier = Modifier.height(components.homeWeatherForecastGraphicHeight),
        yPoints = listOf(1f),
        labelCount = 2.5f,
        minLabel = uiState.minTemperature,
        maxLabel = uiState.maxTemperature
    )
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
        HomeScreen(
            uiState = HomeUiState(
                weatherNow = "Céu limpo",
                city = "Porto",
                temperatureNow = "20º",
                maxTemperature = 30f,
                minTemperature = 15f
            )
        ) { }
    }
}