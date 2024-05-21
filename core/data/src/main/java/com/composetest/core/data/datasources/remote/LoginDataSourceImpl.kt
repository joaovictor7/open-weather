package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.base.BaseRemoteDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

internal class LoginDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    context: Context
): BaseRemoteDataSource(context), LoginDataSource {
    override fun login(login: LoginRequest) = flow {
        remoteCall {
            firebaseAuth.signInWithEmailAndPassword(login.login, login.password).await()
        }
        emit(true)
    }
}