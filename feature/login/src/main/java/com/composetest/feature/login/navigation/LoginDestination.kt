package com.composetest.feature.login.navigation

import androidx.compose.runtime.Composable
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.domain.enums.Destination

object LoginDestination : ScreenDestination {
    override val destination = Destination.FEATURE_LOGIN
    override val screen = @Composable { LoginScreen() }
}