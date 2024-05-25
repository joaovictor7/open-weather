package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.domain.models.bases.BaseRemoteDataSource
import com.composetest.core.data.domain.models.requests.LoginRequest
import com.composetest.core.data.domain.models.responses.LoginResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

internal class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    context: Context
): BaseRemoteDataSource(context), FirebaseAuthDataSource {
    override fun login(login: LoginRequest) = flow {
        val result = safeRemoteCall {
            firebaseAuth.signInWithEmailAndPassword(login.login, login.password).await()
        }
        emit(LoginResponse(result?.user?.email.orEmpty()))
    }
}