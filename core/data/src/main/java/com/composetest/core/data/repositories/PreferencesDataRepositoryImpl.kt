package com.composetest.core.data.repositories

import androidx.datastore.preferences.core.Preferences
import com.composetest.core.data.datasources.local.PreferenceDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PreferencesDataRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : PreferencesDataRepository {
    override suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        preferenceDataSource.setData(key, value)
    }

    override fun <T> getData(onGetValues: (Preferences) -> T) =
        preferenceDataSource.getData(onGetValues)
}