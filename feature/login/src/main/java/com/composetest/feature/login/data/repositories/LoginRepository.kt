package com.composetest.feature.login.data.repositories

import com.composetest.feature.login.data.datasources.LoginDataSource
import com.composetest.feature.login.domain.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource
) {
    fun login(login: LoginModel) = loginDataSource.login(login)
}