package com.composetest.feature.login.ui

sealed class LoginEvent {
    data class WriteData(val email: String? = null, val password: String? = null) : LoginEvent()
    data object CheckShowInvalidEmailMsg : LoginEvent()
    data object Login: LoginEvent()
}