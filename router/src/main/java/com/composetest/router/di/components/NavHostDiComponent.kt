package com.composetest.router.di.components

import androidx.navigation.NavHostController
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@DefineComponent(parent = SingletonComponent::class)
interface NavControllerComponent

@DefineComponent.Builder
interface NavControllerComponentBuilder {
    fun setNavController(@BindsInstance navController: NavHostController): NavControllerComponentBuilder
    fun build(): NavControllerComponent
}