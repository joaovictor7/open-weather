package com.composetest.router.navigation.home.navtypes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class InnerHome(val teste: String, val r: String): Parcelable