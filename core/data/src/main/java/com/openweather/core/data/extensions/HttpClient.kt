package com.openweather.core.data.extensions

import com.openweather.core.data.enums.NetworkApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.post

internal fun HttpClient.addBaseApiUrl(networkApi: NetworkApi) = apply {
    plugin(HttpSend).intercept { request ->
        request.host = networkApi.baseUrl
        request.url {
            port = networkApi.port
            protocol = networkApi.protocol
        }
        execute(request)
    }
}

internal suspend inline fun <reified Response> HttpClient.post(
    url: String,
    request: HttpRequestBuilder.() -> Unit
) = post(url, request).body<Response>()

internal suspend inline fun <reified Response> HttpClient.get(
    url: String,
    request: HttpRequestBuilder.() -> Unit = {}
) = get(url, request).body<Response>()