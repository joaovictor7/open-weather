package com.composetest.feature.login.login.ui

internal sealed class LoginEvent {
    data class WriteData(val email: String? = null, val password: String? = null) : LoginEvent()
    data object CheckShowInvalidEmailMsg : LoginEvent()
    data object Login: LoginEvent()
}