package com.composetest.feature.home.destinations

import androidx.compose.runtime.Composable
import com.composetest.feature.home.ui.HomeScreen
import com.composetest.router.destinations.ScreenDestination
import com.composetest.router.destinations.Destination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeDestination @Inject constructor() : ScreenDestination {
    override val destination = Destination.HOME
    override val screen = @Composable { HomeScreen() }
}