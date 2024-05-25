package com.composetest.core.data.datasources.local

import com.composetest.core.database.appdatabase.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DatabaseDataSource @Inject constructor(
    val appDatabase: AppDatabase
)