package com.composetest.core.router.providers

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow

interface NavHostControllerProvider {
    val navController: NavHostController
    val navBackStackEntryFlow: Flow<NavBackStackEntry>

    fun setNavController(navController: NavHostController)
    fun isCurrentScreen(destination: Any): Boolean
}