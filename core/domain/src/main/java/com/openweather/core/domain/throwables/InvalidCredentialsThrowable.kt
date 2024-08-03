package com.openweather.core.domain.throwables

class InvalidCredentialsThrowable : Throwable() {
    override val message = "Invalid login credentials"
}