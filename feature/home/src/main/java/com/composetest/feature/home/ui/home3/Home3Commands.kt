package com.composetest.feature.home.ui.home3

import com.composetest.common.interfaces.Command

internal data object ReturnLogin: Command<Home3CommandReceiver> {
    override fun execute(receiver: Home3CommandReceiver) {
        receiver.returnLogin()
    }
}