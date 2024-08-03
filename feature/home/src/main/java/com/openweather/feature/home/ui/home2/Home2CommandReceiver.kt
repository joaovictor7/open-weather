package com.openweather.feature.home.ui.home2

import com.openweather.core.ui.interfaces.CommandReceiver

internal interface Home2CommandReceiver : CommandReceiver<Home2CommandReceiver> {
    fun returnHome()
}