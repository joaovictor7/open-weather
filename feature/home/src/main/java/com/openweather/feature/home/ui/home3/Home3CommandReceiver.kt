package com.openweather.feature.home.ui.home3

import com.openweather.core.ui.interfaces.CommandReceiver

internal interface Home3CommandReceiver : CommandReceiver<Home3CommandReceiver> {
    fun returnLogin()
}