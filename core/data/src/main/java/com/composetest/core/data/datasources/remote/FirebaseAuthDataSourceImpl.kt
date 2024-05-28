package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.remote.base.BaseRemoteDataSource
import com.composetest.core.data.domain.models.network.requests.LoginRequest
import com.composetest.core.data.domain.models.network.responses.UserResponse
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
        emit(
            UserResponse(
                id = result?.user?.uid.orEmpty(),
                email = result?.user?.email.orEmpty()
            )
        )
    }
}