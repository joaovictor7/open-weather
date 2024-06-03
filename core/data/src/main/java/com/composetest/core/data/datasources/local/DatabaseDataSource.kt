package com.composetest.core.data.datasources.local

import com.composetest.core.database.database.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DatabaseDataSource @Inject constructor(
    val appDatabase: AppDatabase
)