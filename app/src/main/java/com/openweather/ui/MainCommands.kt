package com.openweather.ui

import com.openweather.core.ui.interfaces.Command

internal class VerifySession : Command<MainCommandReceiver> {
    override fun execute(receiver: MainCommandReceiver) {
        receiver.verifySession()
    }
}