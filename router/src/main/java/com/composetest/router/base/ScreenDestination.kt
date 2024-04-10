package com.composetest.router.base

import androidx.compose.runtime.Composable

interface ScreenDestination {
    val route: String
    val screen: @Composable () -> Unit
}