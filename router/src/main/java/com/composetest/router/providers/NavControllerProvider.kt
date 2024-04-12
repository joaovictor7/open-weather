package com.composetest.router.providers

import androidx.navigation.NavHostController
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@DefineComponent(parent = SingletonComponent::class)
interface NavControllerComponent

@DefineComponent.Builder
sealed interface NavControllerComponentBuilder {
    fun setNavController(@BindsInstance navController: NavHostController): NavControllerComponentBuilder
    fun build(): NavControllerComponent
}

@EntryPoint
@InstallIn(NavControllerComponent::class)
internal interface NavHostControllerEntryPoint {
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