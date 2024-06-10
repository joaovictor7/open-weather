package com.composetest.common.providers

import androidx.navigation.NavHostController

interface NavControllerProvider {

    val navController: NavHostController?

    fun setNavController(navController: NavHostController)
}