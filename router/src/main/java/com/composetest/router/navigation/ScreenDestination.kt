package com.composetest.router.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDeepLink
import com.composetest.router.domain.enums.Destination

interface ScreenDestination {
    val destination: Destination
    val screen: @Composable () -> Unit
    val deepLinks: List<NavDeepLink> get() = emptyList()
}