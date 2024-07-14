package com.composetest.core.domain.throwables

class InvalidCredentialsThrowable : Throwable() {
    override val message = "Invalid login credentials"
}