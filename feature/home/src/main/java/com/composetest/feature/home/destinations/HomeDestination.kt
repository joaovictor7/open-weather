package com.composetest.feature.home.destinations

import androidx.compose.runtime.Composable
import com.composetest.feature.home.ui.HomeScreen
import com.composetest.router.destinations.HomeDestinations

object HomeDestination : HomeDestinations.Home {
    override val route = "home"
    override val screen = @Composable { HomeScreen() }
}