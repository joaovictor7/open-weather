package com.composetest.feature.login.ui.login

import com.composetest.common.interfaces.Command

internal data class WriteData(
    val email: String? = null,
    val password: String? = null
) : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.writeData()
    }
}

data class SetCustomTheme(val enterScreen: Boolean) : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.setCustomTheme(enterScreen)
    }
}

data object CheckShowInvalidEmailMsg : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.checkShowInvalidEmailMsg()
    }
}

data object Login : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.login()
    }
}

data object HandleLoginError : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.handleLoginError()
    }
}