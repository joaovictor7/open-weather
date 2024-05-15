package com.composetest.core.data.providers

import com.composetest.common.utility.providers.BuildConfigProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataSourceProvider @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {
    fun <DataSource> getDataSource(
        apiDataSource: DataSource,
        mockDataSource: DataSource
    ): DataSource =
        if (buildConfigProvider.buildConfigModel.useMock) mockDataSource else apiDataSource
}