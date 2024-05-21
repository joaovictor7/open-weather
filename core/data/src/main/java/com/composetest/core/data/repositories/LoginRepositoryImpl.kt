package com.composetest.core.data.repositories

import android.content.Context
import com.composetest.core.data.datasources.remote.LoginDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import com.composetest.core.data.datasources.base.BaseRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val loginDataSource: LoginDataSource
) : BaseRemoteDataSource(context), LoginRepository {
    override fun login(login: LoginRequest) = loginDataSource.login(login)
}