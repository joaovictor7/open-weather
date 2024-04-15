package com.composetest.feature.home.navigation

import androidx.compose.runtime.Composable
import com.composetest.feature.home.ui.HomeScreen
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.domain.enums.Destinations
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeDestination @Inject constructor() : ScreenDestination {
    override val destination = Destinations.HOME
    override val screen = @Composable { HomeScreen() }
}