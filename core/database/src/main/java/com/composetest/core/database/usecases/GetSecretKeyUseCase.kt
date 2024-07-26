package com.composetest.core.database.usecases

import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.database.data.repositories.DatabaseRepository
import com.composetest.core.security.providers.CipherProvider
import com.composetest.core.security.utils.getAlphanumericRandomKey
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