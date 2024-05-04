package com.composetest.feature.login.login.data.datasources.mock

import com.composetest.feature.login.login.data.datasources.LoginDataSource
import com.composetest.feature.login.login.domain.models.LoginModel
import kotlinx.coroutines.flow.flow

internal class LoginDataSourceMockImpl: LoginDataSource {

    override fun login(login: LoginModel) = flow {
        emit(true)
    }

}