package com.composetest.core.database.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DatabaseRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DatabaseRepository {

    override suspend fun getEncryptedSecretKey() = dataStore.data.map { preferences ->
        preferences[sqliteSecretKey]
    }.firstOrNull()

    override suspend fun setEncryptedSecretKey(encryptedKey: String) {
        dataStore.edit { it[sqliteSecretKey] = encryptedKey }
    }

    private companion object {
        val sqliteSecretKey = stringPreferencesKey("sqlite_secret_key")
    }
}