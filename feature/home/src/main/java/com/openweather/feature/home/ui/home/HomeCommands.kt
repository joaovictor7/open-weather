package com.openweather.feature.home.ui.home

import com.openweather.common.enums.Theme
import com.openweather.core.ui.interfaces.Command

internal data object NavigateToHome2: Command<HomeCommandReceiver> {
    override fun execute(receiver: HomeCommandReceiver) {
        receiver.navigateToHome2()
    }
}

internal data class ChangeAppTheme(
    val theme: Theme? = null,
    val dynamicColors: Boolean? = null
) : Command<HomeCommandReceiver> {
    override fun execute(receiver: HomeCommandReceiver) {
        receiver.changeAppTheme(theme, dynamicColors)
    }
}