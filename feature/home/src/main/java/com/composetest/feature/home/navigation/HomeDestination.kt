package com.composetest.feature.home.navigation

import androidx.compose.runtime.Composable
import com.composetest.feature.home.ui.HomeScreen
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.domain.enums.Destination

object HomeDestination: ScreenDestination {
    override val destination = Destination.FEATURE_HOME
    override val screen = @Composable { HomeScreen() }
}