package com.openweather.ui

import com.openweather.core.ui.interfaces.CommandReceiver

internal interface MainCommandReceiver : CommandReceiver<MainCommandReceiver> {
    fun updateWeatherForecast()
}