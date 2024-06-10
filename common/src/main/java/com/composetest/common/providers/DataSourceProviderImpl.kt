package com.composetest.common.providers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataSourceProviderImpl @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) : DataSourceProvider {
    override fun <DataSource> getDataSource(
        dataSource: DataSource,
        fakeDataSource: DataSource
    ) = if (buildConfigProvider.get.isDebug) fakeDataSource else dataSource
}