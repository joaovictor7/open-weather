package com.composetest.core.security.providers

import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.composetest.core.security.domain.enums.SecureKey
import com.composetest.core.security.utils.getRandomAlphanumericKey
import com.composetest.core.utility.providers.BuildConfigProvider
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SqlCipherProvider @Inject constructor(
    private val secureSharedPreferencesProvider: SecureSharedPreferencesProvider,
    private val buildConfigProvider: BuildConfigProvider
) {

    private val encryptionActivated get() = buildConfigProvider.get.isRelease

    init {
        loadCipherLibrary()
    }

    fun getFactory(): SupportSQLiteOpenHelper.Factory? = if (encryptionActivated) {
        val cipherPassword = getSqlCipherPassword()
        SupportOpenHelperFactory(cipherPassword.toByteArray())
    } else null

    private fun getSqlCipherPassword() =
        if (!secureSharedPreferencesProvider.valueExists(SecureKey.SQLITE_CIPHER_KEY)) {
            getRandomAlphanumericKey().also {
                secureSharedPreferencesProvider.setValue(SecureKey.SQLITE_CIPHER_KEY, it)
            }
        } else {
            secureSharedPreferencesProvider.getString(SecureKey.SQLITE_CIPHER_KEY).orEmpty()
        }

    private fun loadCipherLibrary() {
        if (encryptionActivated) {
            System.loadLibrary("sqlcipher")
        }
    }
}