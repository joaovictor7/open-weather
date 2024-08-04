package com.openweather.common.extensions

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

val Long.unixToLocalDateTime: LocalDateTime
    get() = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
