package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.models.requests.LoginRequest
import kotlinx.coroutines.flow.flow

internal class LoginDataSourceFakeImpl: LoginDataSource {

    override fun login(login: LoginRequest) = flow {
        emit(true)
    }
}