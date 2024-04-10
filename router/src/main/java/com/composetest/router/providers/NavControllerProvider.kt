package com.composetest.router.providers

import androidx.navigation.NavHostController
import com.composetest.router.di.components.NavControllerComponent
import com.composetest.router.di.components.NavControllerComponentBuilder
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import javax.inject.Inject

@EntryPoint
@InstallIn(NavControllerComponent::class)
interface NavHostControllerEntryPoint {
    val navController: NavHostController
}

class NavControllerProvider @Inject constructor(private val builder: NavControllerComponentBuilder) {

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