package com.openweather.common.throwables

class BadRequestThrowable(
    override val message: String
) : Throwable()