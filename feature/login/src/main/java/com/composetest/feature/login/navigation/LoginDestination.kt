package com.composetest.feature.login.navigation

import androidx.compose.runtime.Composable
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.domain.enums.Destinations
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDestination @Inject constructor() : ScreenDestination {
    override val destination = Destinations.LOGIN
    override val screen = @Composable { LoginScreen() }
}