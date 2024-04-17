package com.composetest.core.providers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceProvider @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {
    fun <DataSource> getDataSource(
        apiDataSource: DataSource,
        mockDataSource: DataSource
    ): DataSource =
        if (buildConfigProvider.buildConfigModel.useMock) mockDataSource else apiDataSource
}