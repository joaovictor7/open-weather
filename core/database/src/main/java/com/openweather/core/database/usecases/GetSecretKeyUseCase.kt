package com.openweather.core.database.usecases

import com.openweather.common.providers.BuildConfigProvider
import com.openweather.core.database.data.repositories.DatabaseRepository
import com.openweather.core.security.providers.CipherProvider
import com.openweather.core.security.utils.getAlphanumericRandomKey
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class GetSecretKeyUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val buildConfigProvider: BuildConfigProvider,
    private val cipherProvider: CipherProvider
) {

    operator fun invoke() = if (buildConfigProvider.get.isRelease) runBlocking {
        val encryptedKey = databaseRepository.getEncryptedSecretKey()
        if (encryptedKey == null) getAlphanumericRandomKey(SECRET_KEY_SIZE).also {
            databaseRepository.setEncryptedSecretKey(cipherProvider.encrypt(it))
        } else cipherProvider.decrypt(encryptedKey)
    } else null

    private companion object {
        const val SECRET_KEY_SIZE = 300
    }
}