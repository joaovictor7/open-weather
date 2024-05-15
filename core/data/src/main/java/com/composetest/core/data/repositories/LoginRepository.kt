package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.LoginDataSource
import com.composetest.core.data.domain.requests.LoginRequest
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource
) {
    fun login(login: LoginRequest) = loginDataSource.login(login)
}