package com.composetest.feature.login.infra.repositories

import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource
) {
    fun login(login: LoginModel) = loginDataSource.login(login)
}