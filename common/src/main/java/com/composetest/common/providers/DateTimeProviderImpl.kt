package com.composetest.common.providers

import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DateTimeProviderImpl @Inject constructor() : DateTimeProvider {
    override val nowDateTime: LocalDateTime get() = LocalDateTime.now()
}