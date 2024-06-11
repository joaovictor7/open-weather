package com.composetest.feature.home.ui.home2

import com.composetest.common.interfaces.Command

internal data object ReturnHome: Command<Home2CommandReceiver> {
    override fun execute(receiver: Home2CommandReceiver) {
        receiver.returnHome()
    }
}