package com.composetest.core.security.providers

import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.composetest.core.security.domain.enums.SecureSharedPreferenceKey
import com.composetest.core.security.utils.getRandomAlphanumericKey
import com.composetest.common.providers.BuildConfigProvider
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SqlCipherProviderImpl @Inject constructor(
    private val secureSharedPreferencesProvider: SecureSharedPreferencesProvider,
    private val buildConfigProvider: BuildConfigProvider
) : SqlCipherProvider {

    private val cipherActivated get() = buildConfigProvider.get.isRelease

    init {
        loadCipherLibrary()
    }

    override fun getFactory(): SupportSQLiteOpenHelper.Factory? = if (cipherActivated) {
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