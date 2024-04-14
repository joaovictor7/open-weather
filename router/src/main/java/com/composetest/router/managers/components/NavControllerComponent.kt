package com.composetest.router.managers.components

import androidx.navigation.NavHostController
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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