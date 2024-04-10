package com.composetest.router.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface ScreenDestination {
    val route: String
    val screen: @Composable (NavHostController) -> Unit
}