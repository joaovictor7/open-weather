package com.composetest.router.destinations

import androidx.compose.runtime.Composable

interface ScreenDestination {
    val destination: Destination
    val screen: @Composable () -> Unit
    val route: String get() = destination.name.lowercase()
}