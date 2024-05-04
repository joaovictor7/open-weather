package com.composetest.feature.login.login.data.repositories

import com.composetest.feature.login.login.data.datasources.LoginDataSource
import com.composetest.feature.login.login.domain.models.LoginModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource
) {
    fun login(login: LoginModel) = loginDataSource.login(login)
}