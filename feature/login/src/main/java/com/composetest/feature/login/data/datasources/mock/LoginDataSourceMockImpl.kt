package com.composetest.feature.login.data.datasources.mock

import com.composetest.feature.login.data.datasources.LoginDataSource
import com.composetest.feature.login.domain.models.LoginModel
import kotlinx.coroutines.flow.flow

class LoginDataSourceMockImpl: LoginDataSource {

    override fun login(login: LoginModel) = flow {
        emit(true)
    }

}