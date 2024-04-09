package com.composetest.feature.login.ui

sealed class LoginAction {
    data class WriteData(val email: String, val password: String) : LoginAction()
    data object ClickEnter: LoginAction()
}