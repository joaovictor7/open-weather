package com.composetest.feature.login.ui.login

sealed interface LoginEvent {
    data class WriteData(val email: String? = null, val password: String? = null) : LoginEvent
    data class SetCustomTheme(val enterScreen: Boolean) : LoginEvent
    data object CheckShowInvalidEmailMsg : LoginEvent
    data object Login : LoginEvent
    data object DismissErrorAlertDialog: LoginEvent
}