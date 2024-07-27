package com.composetest.common.analytics.interfaces

interface AnalyticEvent : AnalyticScreen {
    val tag: String
    val params: Map<String, *>
}