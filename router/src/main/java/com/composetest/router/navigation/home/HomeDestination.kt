package com.composetest.router.navigation.home

import com.composetest.router.domain.NavType
import com.composetest.router.navigation.home.navtypes.InnerHome
import com.composetest.router.utils.navType
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