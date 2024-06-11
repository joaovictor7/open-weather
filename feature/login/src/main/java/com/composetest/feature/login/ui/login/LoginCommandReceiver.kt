package com.composetest.feature.login.ui.login

import com.composetest.common.interfaces.Command
import com.composetest.common.interfaces.CommandReceiver

interface LoginCommandReceiver : CommandReceiver<LoginCommandReceiver> {
    override fun executeCommand(command: Command<LoginCommandReceiver>) {
        command.execute(this)
    }

    fun writeData(email: String? = null, password: String? = null)
    fun setCustomTheme(enterScreen: Boolean)
    fun checkShowInvalidEmailMsg()
    fun login()
    fun handleLoginError(throwable: Throwable? = null)
}