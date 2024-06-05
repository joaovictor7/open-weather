package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.remote.base.BaseRemoteDataSource
import com.composetest.core.data.domain.converters.AuthenticationResponseConverter
import com.composetest.core.data.domain.models.network.requests.AuthenticationRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

internal class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val converter: AuthenticationResponseConverter,
    context: Context
) : BaseRemoteDataSource(context), FirebaseAuthDataSource {

    override fun authentication(request: AuthenticationRequest) = flow {
        val authResult = safeRemoteCall {
            firebaseAuth.signInWithEmailAndPassword(request.login, request.password).await()
        }
        val tokenResult = safeRemoteCall {
            authResult.user?.getIdToken(false)?.await()
        }
        emit(converter.convertTo(authResult.user, tokenResult))
    }
}