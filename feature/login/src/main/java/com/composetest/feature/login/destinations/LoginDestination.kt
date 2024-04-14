package com.composetest.feature.login.destinations

import androidx.compose.runtime.Composable
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.router.destinations.ScreenDestination
import com.composetest.router.destinations.Destination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDestination @Inject constructor() : ScreenDestination {
    override val destination = Destination.LOGIN
    override val screen = @Composable { LoginScreen() }
}