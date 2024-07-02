package com.composetest.core.router.providers

import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavControllerProviderImpl @Inject constructor() : NavControllerProvider {

    override lateinit var navController: NavHostController
        private set

    override fun setNavController(navController: NavHostController) {
        this.navController = navController
    }
}