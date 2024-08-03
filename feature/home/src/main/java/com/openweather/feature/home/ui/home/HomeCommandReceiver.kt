package com.openweather.feature.home.ui.home

import com.openweather.common.enums.Theme
import com.openweather.core.ui.interfaces.CommandReceiver

internal interface HomeCommandReceiver : CommandReceiver<HomeCommandReceiver> {
    fun navigateToHome2()
    fun changeAppTheme(theme: Theme? = null, dynamicColors: Boolean? = null)
}