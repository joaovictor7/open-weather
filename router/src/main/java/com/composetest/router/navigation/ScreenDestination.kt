package com.composetest.router.navigation

import androidx.compose.runtime.Composable
import com.composetest.router.domain.enums.Destinations

interface ScreenDestination {
    val destination: Destinations
    val screen: @Composable () -> Unit
    val route: String get() = destination.name.lowercase()
}