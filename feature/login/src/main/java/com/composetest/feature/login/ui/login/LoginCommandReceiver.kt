package com.composetest.feature.login.ui.login

import com.composetest.common.enums.Theme
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface LoginCommandReceiver : CommandReceiver<LoginCommandReceiver> {
    fun writeData(email: String? = null, password: String? = null)
    fun setCustomTheme(enterScreen: Boolean, currentAppTheme: Theme)
    fun checkShowInvalidEmailMsg()
    fun login()
    fun handleLoginError(throwable: Throwable? = null)
}