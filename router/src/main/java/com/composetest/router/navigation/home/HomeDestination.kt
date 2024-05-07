package com.composetest.router.navigation.home

import com.composetest.router.navigation.home.navtypes.InnerHome
import kotlinx.serialization.Serializable

@Serializable
data class HomeDestination(val teste: String, val innerHome: InnerHome)