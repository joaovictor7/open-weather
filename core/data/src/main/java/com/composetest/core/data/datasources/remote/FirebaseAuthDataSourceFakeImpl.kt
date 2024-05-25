package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.domain.models.bases.BaseRemoteDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import com.composetest.core.data.domain.models.responses.LoginResponse
import kotlinx.coroutines.flow.flow

internal class FirebaseAuthDataSourceFakeImpl(context: Context) :
    BaseRemoteDataSource(context), FirebaseAuthDataSource {

    override fun login(login: LoginRequest) = flow {
        safeRemoteCall {}
        emit(LoginResponse(String()))
    }
}