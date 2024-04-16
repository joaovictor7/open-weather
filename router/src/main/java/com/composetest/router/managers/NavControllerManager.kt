package com.composetest.router.managers

import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavControllerManager @Inject constructor() {

    var navController: NavHostController? = null
        private set

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }
}