package com.openweather.feature.home.ui.home3

import com.openweather.core.ui.interfaces.Command

internal data object ReturnLogin: Command<Home3CommandReceiver> {
    override fun execute(receiver: Home3CommandReceiver) {
        receiver.returnLogin()
    }
}