package com.openweather.core.router.results.home

import com.openweather.core.router.interfaces.ResultParam
import kotlinx.parcelize.Parcelize

@Parcelize
data class Home2Result(
    val e: String
) : ResultParam
