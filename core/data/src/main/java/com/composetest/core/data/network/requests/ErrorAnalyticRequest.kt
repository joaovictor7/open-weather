package com.composetest.core.data.network.requests

data class ErrorAnalyticRequest(
    override val tag: String,
    override val params: Map<String, *>,
    val throwable: Throwable,
) : AnalyticRequest(tag, params)