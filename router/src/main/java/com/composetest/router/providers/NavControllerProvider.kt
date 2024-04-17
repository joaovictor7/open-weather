package com.composetest.router.providers

import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavControllerProvider @Inject constructor() {

    var navController: NavHostController? = null
        private set

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }
}