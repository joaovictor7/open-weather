package com.composetest.core.router.providers

import androidx.navigation.NavHostController
import androidx.navigation.get
import javax.inject.Inject

internal class NavControllerProviderImpl @Inject constructor() : NavControllerProvider {

    override lateinit var navController: NavHostController
        private set

    override fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    override fun isCurrentScreen(destination: Any) =
        navController.graph[destination] == navController.currentDestination
}