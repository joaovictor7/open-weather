package com.composetest.common.providers

import java.time.LocalDateTime

interface DateTimeProvider {
    val nowDateTime: LocalDateTime
}