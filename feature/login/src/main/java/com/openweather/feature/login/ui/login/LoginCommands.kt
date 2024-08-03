package com.openweather.feature.login.ui.login

import com.openweather.common.enums.Theme
import com.openweather.core.ui.interfaces.Command

internal data class WriteData(
    val email: String? = null,
    val password: String? = null
) : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.writeData(email, password)
    }
}

internal data class SetCustomTheme(
    val enterScreen: Boolean,
    val currentAppTheme: Theme
) : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.setCustomTheme(enterScreen, currentAppTheme)
    }
}

internal data object CheckShowInvalidEmailMsg : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.checkShowInvalidEmailMsg()
    }
}

internal data object Login : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.login()
    }
}

internal data object HandleLoginError : Command<LoginCommandReceiver> {
    override fun execute(receiver: LoginCommandReceiver) {
        receiver.handleLoginError()
    }
}