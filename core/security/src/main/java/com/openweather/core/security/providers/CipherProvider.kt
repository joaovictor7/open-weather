package com.openweather.core.security.providers

interface CipherProvider {
    fun encrypt(inputText: String): String

    fun decrypt(data: String): String
}