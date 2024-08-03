package com.openweather.core.router.interfaces

import androidx.navigation.NavType
import kotlin.reflect.KType

internal interface NavType {
    val navTypes: Map<KType, NavType<*>>
}