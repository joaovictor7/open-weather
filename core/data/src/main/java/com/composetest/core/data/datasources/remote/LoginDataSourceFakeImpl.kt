package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.base.BaseRemoteDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import kotlinx.coroutines.flow.flow

internal class LoginDataSourceFakeImpl(
    context: Context
) : BaseRemoteDataSource(context), LoginDataSource {

    override fun login(login: LoginRequest) = flow {
        remoteCall {}
        emit(true)
    }
}