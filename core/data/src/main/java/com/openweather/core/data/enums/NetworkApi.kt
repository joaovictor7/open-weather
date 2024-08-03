package com.openweather.core.data.enums

import io.ktor.http.URLProtocol

enum class NetworkApi(
    val baseUrl: String,
    val port: Int = 0,
    val protocol: URLProtocol = URLProtocol.HTTPS
) {
    OPEN_WEATHER("api.openweathermap.org/data/2.5")
}