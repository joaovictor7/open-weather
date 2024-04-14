package com.composetest.core.managers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceManager @Inject constructor(
    private val buildConfigManager: BuildConfigManager
) {
    fun <DataSource> getDataSource(
        apiDataSource: DataSource,
        mockDataSource: DataSource
    ): DataSource =
        if (buildConfigManager.buildConfigModel.useMock) mockDataSource else apiDataSource
}