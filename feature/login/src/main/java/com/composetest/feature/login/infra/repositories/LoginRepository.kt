package com.composetest.feature.login.infra.repositories

import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.models.LoginModel
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDataSource: LoginDataSource) {
    fun login(login: LoginModel) = loginDataSource.login(login)
}