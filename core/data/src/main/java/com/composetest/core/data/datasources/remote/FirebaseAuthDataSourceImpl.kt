package com.composetest.core.data.datasources.remote

import com.composetest.common.providers.RemoteCallProvider
import com.composetest.core.data.converters.AuthenticationResponseConverter
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

internal class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val authenticationResponseConverter: AuthenticationResponseConverter,
    private val remoteCallProvider: RemoteCallProvider
) : FirebaseAuthDataSource {

    override fun authentication(request: AuthenticationRequest) = flow {
        val authResult = remoteCallProvider.safeRemoteCall {
            firebaseAuth.signInWithEmailAndPassword(request.login, request.password).await()
        }
        val tokenResult = remoteCallProvider.safeRemoteCall {
            authResult.user?.getIdToken(false)?.await()
        }
        emit(authenticationResponseConverter.convertTo(authResult.user, tokenResult))
    }
}