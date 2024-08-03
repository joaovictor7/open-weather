package com.openweather.core.database.data.repositories

internal interface DatabaseRepository {
    suspend fun getEncryptedSecretKey(): String?

    suspend fun setEncryptedSecretKey(encryptedKey: String)
}