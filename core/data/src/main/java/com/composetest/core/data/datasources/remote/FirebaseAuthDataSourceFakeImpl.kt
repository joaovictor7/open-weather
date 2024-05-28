package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.remote.base.BaseRemoteDataSource
import com.composetest.core.data.domain.models.network.requests.LoginRequest
import com.composetest.core.data.domain.models.network.responses.UserResponse
import kotlinx.coroutines.flow.flow

internal class FirebaseAuthDataSourceFakeImpl(context: Context) :
    BaseRemoteDataSource(context), FirebaseAuthDataSource {

    override fun login(login: LoginRequest) = flow {
        safeRemoteCall {}
        emit(UserResponse(String(), String()))
    }
}