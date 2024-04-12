package com.composetest.router.providers

import androidx.navigation.NavHostController
import com.composetest.router.providers.components.NavControllerComponentBuilder
import com.composetest.router.providers.components.NavHostControllerEntryPoint
import dagger.hilt.EntryPoints
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavControllerProvider @Inject constructor(
    private val builder: NavControllerComponentBuilder
) {

    var navController: NavHostController? = null
        private set

    fun setNavController(navController: NavHostController) {
        val component = builder.setNavController(navController).build()
        this.navController = EntryPoints.get(
            component,
            NavHostControllerEntryPoint::class.java
        ).navController
    }
}