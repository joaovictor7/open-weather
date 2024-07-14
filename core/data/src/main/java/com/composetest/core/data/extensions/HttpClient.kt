package com.composetest.core.data.extensions

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post

internal suspend inline fun <reified Response> HttpClient.post(
    url: String,
    request: HttpRequestBuilder.() -> Unit
) = post(url, request).body<Response>()