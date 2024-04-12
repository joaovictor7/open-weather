package com.composetest.core.di

import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.providers.DataSourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun dataSourceProvider(buildConfigProvider: BuildConfigProvider) =
        DataSourceProvider(buildConfigProvider)
}
