package com.composetest.core.data.datasources.fake

import com.composetest.core.data.datasources.remote.LoginDataSource
import com.composetest.core.data.domain.requests.LoginRequest
import kotlinx.coroutines.flow.flow

internal class LoginDataSourceFakeImpl: LoginDataSource {

    override fun login(login: LoginRequest) = flow {
        emit(true)
    }
}