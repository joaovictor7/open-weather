package com.openweather.core.data.constants.preferencesdatastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal object PreferencesDataKeys {
    val appTheme = stringPreferencesKey("app_theme")
    val dynamicColor = booleanPreferencesKey("dynamic_color")
    val sqliteSecretKey = stringPreferencesKey("sqlite_secret_key")
}
