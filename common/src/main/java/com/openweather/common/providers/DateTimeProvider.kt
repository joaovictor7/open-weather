package com.openweather.common.providers

import java.time.LocalDateTime

interface DateTimeProvider {
    val nowDateTime: LocalDateTime
    val nowDateTimeInSeconds: Long
}