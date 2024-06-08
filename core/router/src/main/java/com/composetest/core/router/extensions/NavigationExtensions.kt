package com.composetest.core.router.extensions

import androidx.navigation.NavType
import androidx.navigation.toRoute
import com.composetest.core.router.providers.NavigationProvider
import kotlin.reflect.KType

inline fun <reified Destination : Any> NavigationProvider.getParam(
    typeMap: Map<KType, NavType<*>> = emptyMap()
) = savedStateHandle.toRoute<Destination>(typeMap)