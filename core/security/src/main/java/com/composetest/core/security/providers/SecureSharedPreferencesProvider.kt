package com.composetest.core.security.providers

import com.composetest.core.security.domain.enums.SecureSharedPreferenceKey

interface SecureSharedPreferencesProvider {
    fun valueExists(key: SecureSharedPreferenceKey): Boolean
    fun setValue(key: SecureSharedPreferenceKey, value: Any)
    fun getString(key: SecureSharedPreferenceKey, defaultValue: String = String()): String?
    fun getLong(key: SecureSharedPreferenceKey, defaultValue: Long = 0L): Long
    fun getInt(key: SecureSharedPreferenceKey, defaultValue: Int = 0): Int
    fun getBoolean(key: SecureSharedPreferenceKey, defaultValue: Boolean = false): Boolean
    fun getFloat(key: SecureSharedPreferenceKey, defaultValue: Float = 0f): Float
}