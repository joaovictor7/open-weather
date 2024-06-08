package com.composetest.core.security.providers

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.composetest.core.security.domain.enums.SecureSharedPreferenceKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SecureSharedPreferencesProviderImpl @Inject constructor(
    @ApplicationContext context: Context
) : SecureSharedPreferencesProvider {

    private val keyGenParameterSpec = KeyGenParameterSpec.Builder(
        KEY_STORE_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(KEY_SIZE)
        .build()

    private val masterKey = MasterKey.Builder(context, KEY_STORE_ALIAS)
        .setKeyGenParameterSpec(keyGenParameterSpec)
        .build()

    private val preferences = EncryptedSharedPreferences.create(
        context,
        KEY_STORE_FILENAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun valueExists(key: SecureSharedPreferenceKey) = preferences.contains(key.name)

    override fun setValue(key: SecureSharedPreferenceKey, value: Any) {
        val edit = preferences.edit()
        when (value) {
            is String -> edit.putString(key.name, value)
            is Long -> edit.putLong(key.name, value)
            is Int -> edit.putInt(key.name, value)
            is Boolean -> edit.putBoolean(key.name, value)
            is Float -> edit.putFloat(key.name, value)
        }
        edit.apply()
    }

    override fun getString(key: SecureSharedPreferenceKey, defaultValue: String) =
        preferences.getString(key.name, defaultValue)

    override fun getLong(key: SecureSharedPreferenceKey, defaultValue: Long) =
        preferences.getLong(key.name, defaultValue)

    override fun getInt(key: SecureSharedPreferenceKey, defaultValue: Int) =
        preferences.getInt(key.name, defaultValue)

    override fun getBoolean(key: SecureSharedPreferenceKey, defaultValue: Boolean) =
        preferences.getBoolean(key.name, defaultValue)

    override fun getFloat(key: SecureSharedPreferenceKey, defaultValue: Float) =
        preferences.getFloat(key.name, defaultValue)

    private companion object {
        const val KEY_SIZE = 256
        const val KEY_STORE_ALIAS = "KEY_STORE_ALIAS"
        const val KEY_STORE_FILENAME = "key_store"
    }
}