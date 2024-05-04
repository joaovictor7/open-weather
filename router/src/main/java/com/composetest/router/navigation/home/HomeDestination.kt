package com.composetest.router.navigation.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class HomeDestination(val teste: String, val innerHome: InnerHome)

@Serializable
@Parcelize
data class InnerHome(val teste: String, val r: String): Parcelable