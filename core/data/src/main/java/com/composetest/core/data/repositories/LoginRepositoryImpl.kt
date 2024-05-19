package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.LoginDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override fun login(login: LoginRequest) = loginDataSource.login(login)
}