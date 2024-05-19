package com.composetest.core.data.repositories

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesDataRepository {
    suspend fun <T> setData(key: Preferences.Key<T>, value: T)
    fun <T> getData(onGetValues: (Preferences) -> T): Flow<T>
}