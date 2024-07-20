package com.composetest.core.router.providers

import androidx.navigation.NavHostController

interface NavControllerProvider {
    val navController: NavHostController

    fun setNavController(navController: NavHostController)
    fun isCurrentScreen(destination: Any): Boolean
}