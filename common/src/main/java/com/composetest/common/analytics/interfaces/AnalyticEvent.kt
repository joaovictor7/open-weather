package com.composetest.common.analytics.interfaces

interface AnalyticEvent {
    val tag: String
    val params: Map<String, *>
}