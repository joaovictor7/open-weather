package com.openweather.core.router.providers

import androidx.navigation.NavHostController
import androidx.navigation.get
import javax.inject.Inject

internal class NavHostControllerProviderImpl @Inject constructor() : NavHostControllerProvider {

    override lateinit var navController: NavHostController
        private set

    override val navBackStackEntryFlow get() = navController.currentBackStackEntryFlow

    override fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    override fun isCurrentScreen(destination: Any) =
        navController.graph[destination] == navController.currentDestination
}