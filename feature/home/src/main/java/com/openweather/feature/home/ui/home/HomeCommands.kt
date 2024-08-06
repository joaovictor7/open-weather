package com.openweather.feature.home.ui.home

import com.openweather.core.ui.interfaces.Command

internal sealed interface HomeCommands : Command<HomeCommandReceiver> {

    data object Refresh : HomeCommands {
        override fun execute(receiver: HomeCommandReceiver) {
            receiver.refresh()
        }
    }
}