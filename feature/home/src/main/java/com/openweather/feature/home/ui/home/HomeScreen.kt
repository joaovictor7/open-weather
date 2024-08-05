package com.openweather.feature.home.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.openweather.core.designsystem.components.graphics.SimpleScatterPlotGraphic
import com.openweather.core.designsystem.dimensions.components
import com.openweather.core.designsystem.dimensions.spacings
import com.openweather.core.designsystem.theme.OpenWeatherTheme
import com.openweather.core.ui.interfaces.Command
import com.openweather.core.ui.interfaces.Screen
import com.openweather.feature.home.models.FutureWeatherDailyForecastScreenModel
import com.openweather.feature.home.models.FutureWeatherForecastScreenModel
import com.openweather.feature.home.models.TodayWeatherForecastScreenModel
import com.openweather.feature.home.models.WeatherNowScreenModel

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
                    .padding(spacings.twenty)
            ) {
                WeatherNow(uiState = uiState)
                Spacer(Modifier.height(spacings.thirty))
                WeatherForecastGraphic(uiState = uiState)
                Spacer(Modifier.height(spacings.thirty))
                FutureWeatherForecasts(
                    futureWeatherForecastScreenModels = uiState.futureWeatherForecastScreenModels
                )
            }
        }
    }
}

@Composable
private fun WeatherNow(uiState: HomeUiState) {
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
                text = uiState.weatherNowModel.description,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(Modifier.padding(horizontal = spacings.ten))
        Text(
            text = uiState.weatherNowModel.temperature,
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
private fun WeatherForecastGraphic(uiState: HomeUiState) {
    Card {
        SimpleScatterPlotGraphic(
            modifier = Modifier
                .height(components.homeWeatherForecastGraphicHeight)
                .padding(spacings.twelve),
            yPoints = uiState.todayWeatherForecastScreenModel.temperatures,
            minLabel = uiState.todayWeatherForecastScreenModel.minTemperature,
            maxLabel = uiState.todayWeatherForecastScreenModel.maxTemperature,
            labelFormat = "%sº"
        )
    }
}

@Composable
private fun FutureWeatherForecasts(
    futureWeatherForecastScreenModels: List<FutureWeatherForecastScreenModel>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        futureWeatherForecastScreenModels.forEach { futureWeatherForecastModel ->
            Column {
                Text(
                    text = futureWeatherForecastModel.day,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(spacings.ten))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    futureWeatherForecastModel.weatherForecasts.forEach {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = it.temperature,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(Modifier.padding(vertical = spacings.four))
                            Text(
                                text = it.hour,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Spacer(Modifier.height(spacings.twentyTwo))
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
                weatherNowModel = WeatherNowScreenModel(
                    city = "Porto",
                    temperature = "20º",
                    iconId = "",
                    description = "Céu limpo"
                ),
                todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
                    minTemperature = 17f,
                    maxTemperature = 25f
                ),
                futureWeatherForecastScreenModels = listOf(
                    FutureWeatherForecastScreenModel(
                        day = "terça",
                        weatherForecasts = listOf(
                            FutureWeatherDailyForecastScreenModel(
                                iconId = "",
                                temperature = "19",
                                hour = "07"
                            ),
                            FutureWeatherDailyForecastScreenModel(
                                iconId = "",
                                temperature = "19",
                                hour = "07"
                            ),
                            FutureWeatherDailyForecastScreenModel(
                                iconId = "",
                                temperature = "19",
                                hour = "07"
                            ),
                            FutureWeatherDailyForecastScreenModel(
                                iconId = "",
                                temperature = "19",
                                hour = "07"
                            )
                        )
                    ),
                    FutureWeatherForecastScreenModel(
                        day = "terça",
                        weatherForecasts = listOf(
                            FutureWeatherDailyForecastScreenModel(
                                iconId = "",
                                temperature = "19",
                                hour = "07h"
                            )
                        )
                    )
                )
            )
        ) { }
    }
}