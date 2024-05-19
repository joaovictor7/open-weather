package com.composetest.core.data.providers

import com.composetest.core.utility.providers.BuildConfigProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataSourceProvider @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {
    fun <DataSource> getDataSource(
        dataSource: DataSource,
        fakeDataSource: DataSource
    ) = if (buildConfigProvider.get.isDebug) fakeDataSource else dataSource
}