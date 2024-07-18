package com.composetest.core.data.data.repositories.local

interface DatabaseRepository {
    suspend fun getEncryptedSecretKey(): String?

    suspend fun setEncryptedSecretKey(encryptedKey: String)
}