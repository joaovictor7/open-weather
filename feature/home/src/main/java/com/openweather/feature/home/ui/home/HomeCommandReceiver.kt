package com.openweather.feature.home.ui.home

import com.openweather.core.ui.interfaces.CommandReceiver

internal interface HomeCommandReceiver : CommandReceiver<HomeCommandReceiver> {
    fun refresh()
}