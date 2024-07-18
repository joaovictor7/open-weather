package com.composetest.core.security.providers

interface CipherProvider {
    fun encrypt(inputText: String): String

    fun decrypt(data: String): String
}