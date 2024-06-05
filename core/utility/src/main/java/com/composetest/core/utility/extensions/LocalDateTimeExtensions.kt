package com.composetest.core.utility.extensions

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

val Long.secondsToLocalDateTime: LocalDateTime
    get() = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
