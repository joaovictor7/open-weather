package com.composetest.core.providers

class DataSourceProvider(private val buildConfigProvider: BuildConfigProvider) {
    fun <DataSource> getDataSource(
        apiDataSource: DataSource,
        mockDataSource: DataSource
    ): DataSource = if (buildConfigProvider.buildConfigModel.useMock) mockDataSource else apiDataSource
}