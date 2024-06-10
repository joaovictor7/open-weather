package com.composetest.core.database.providers

import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.composetest.core.security.domain.enums.SecureSharedPreferenceKey
import com.composetest.core.security.providers.SecureSharedPreferencesProvider
import com.composetest.core.security.utils.getRandomAlphanumericKey
import com.composetest.common.providers.BuildConfigProvider
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SqlCipherProvider @Inject constructor(
    private val secureSharedPreferencesProvider: SecureSharedPreferencesProvider,
    private val buildConfigProvider: BuildConfigProvider
) {

    private val cipherActivated get() = buildConfigProvider.get.isRelease

    init {
        loadCipherLibrary()
    }

    fun getFactory(): SupportSQLiteOpenHelper.Factory? = if (cipherActivated) {
        val cipherPassword = getSqlCipherPassword()
        SupportOpenHelperFactory(cipherPassword.toByteArray())
    } else null

    private fun getSqlCipherPassword() =
        if (!secureSharedPreferencesProvider.valueExists(SecureSharedPreferenceKey.SQLITE_CIPHER_KEY)) {
            getRandomAlphanumericKey().also {
                secureSharedPreferencesProvider.setValue(SecureSharedPreferenceKey.SQLITE_CIPHER_KEY, it)
            }
        } else {
            secureSharedPreferencesProvider.getString(SecureSharedPreferenceKey.SQLITE_CIPHER_KEY).orEmpty()
        }

    private fun loadCipherLibrary() {
        if (cipherActivated) {
            System.loadLibrary("sqlcipher")
        }
    }
}