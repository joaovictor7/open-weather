package com.composetest.core.domain.di

import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.domain.managers.AppThemeManagerImpl
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.managers.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagerModule {

    @Binds
    abstract fun sessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @Binds
    abstract fun appThemeManager(appThemeManagerImpl: AppThemeManagerImpl): AppThemeManager
}