package com.composetest.core.data.network.requests

open class AnalyticRequest(
    open val tag: String,
    open val params: Map<String, *>
)