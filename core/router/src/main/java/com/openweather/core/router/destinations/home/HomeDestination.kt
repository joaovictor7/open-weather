package com.openweather.core.router.destinations.home

import com.openweather.core.router.interfaces.NavType
import com.openweather.core.router.destinations.home.navtypes.InnerHome
import com.openweather.core.router.utils.navType
import kotlinx.serialization.Serializable

@Serializable
data class HomeDestination(
    val teste: String,
    val innerHome: InnerHome
) {
    companion object : NavType {
        override val navTypes = mapOf(
            navType<InnerHome>()
        )
    }
}