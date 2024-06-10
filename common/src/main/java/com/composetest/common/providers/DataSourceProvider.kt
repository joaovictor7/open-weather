package com.composetest.common.providers

interface DataSourceProvider {
    fun <DataSource> getDataSource(dataSource: DataSource, fakeDataSource: DataSource): DataSource
}