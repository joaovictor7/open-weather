package com.composetest.feature.login.destinations

import androidx.compose.runtime.Composable
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.router.destinations.LoginDestinations

object LoginDestination : LoginDestinations.Login {
    override val route = "login"
    override val screen = @Composable { LoginScreen() }
}