package com.composetest.router.navigation

import kotlinx.serialization.Serializable

sealed interface HomeDestinations {
    @Serializable
    data class Home(val teste: String): HomeDestinations
    @Serializable
    data class Home2(val teste: String, val r: String): HomeDestinations
}