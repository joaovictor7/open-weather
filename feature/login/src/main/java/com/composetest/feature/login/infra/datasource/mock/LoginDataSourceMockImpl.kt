package com.composetest.feature.login.infra.datasource.mock

import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.models.LoginModel
import kotlinx.coroutines.flow.flow

class LoginDataSourceMockImpl: LoginDataSource {

    override fun login(login: LoginModel) = flow {
        emit(true)
    }

}