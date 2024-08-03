package com.openweather.feature.home.ui.home2

import com.openweather.core.ui.interfaces.Command

internal data object ReturnHome: Command<Home2CommandReceiver> {
    override fun execute(receiver: Home2CommandReceiver) {
        receiver.returnHome()
    }
}