package com.composetest.core.data.repositories.local

import com.composetest.core.data.constants.preferencesdatastore.PreferencesDataKeys.sqliteSecretKey
import com.composetest.core.data.datasources.local.PreferenceDataSource
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DatabaseRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : DatabaseRepository {

    override suspend fun getEncryptedSecretKey() = preferenceDataSource.getData { preferences ->
        preferences[sqliteSecretKey]
    }.firstOrNull()

    override suspend fun setEncryptedSecretKey(encryptedKey: String) =
        preferenceDataSource.setData(sqliteSecretKey, encryptedKey)
}