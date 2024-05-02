package com.composetest.router.navigation

import kotlinx.serialization.Serializable

sealed interface LoginDestinations {
    @Serializable
    data object Login: LoginDestinations
}