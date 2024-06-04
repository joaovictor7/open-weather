package com.composetest.core.domain.di

import com.composetest.core.domain.usecases.AppThemeUseCase
import com.composetest.core.domain.usecases.AppThemeUseCaseImpl
import com.composetest.core.domain.usecases.SessionUseCase
import com.composetest.core.domain.usecases.SessionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun appThemeUseCase(appThemeUseCaseImpl: AppThemeUseCaseImpl): AppThemeUseCase

    @Binds
    abstract fun sessionUseCase(sessionUseCaseImpl: SessionUseCaseImpl): SessionUseCase
}