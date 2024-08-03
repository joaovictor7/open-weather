package com.openweather.common.throwables

class NetworkThrowable : Throwable() {
    override val message = "Not internet connection"
}
