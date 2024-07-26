package com.composetest.core.data.data.datasources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.composetest.common.di.qualifiers.Dispatcher
import com.composetest.common.enums.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PreferenceDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : PreferenceDataSource {

    override suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        withContext(ioDispatcher) {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    override fun <T> getData(onGetValue: (Preferences) -> T) = dataStore.data.map {
        onGetValue.invoke(it)
    }.flowOn(ioDispatcher)
}