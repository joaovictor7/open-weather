package com.composetest.core.data.datasources.remote

import com.composetest.core.data.converters.AuthenticationResponseConverter
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseAuthDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authenticationResponseConverter: AuthenticationResponseConverter,
) {
    suspend fun authentication(request: AuthenticationRequest): AuthenticationResponse {
        val user = firebaseAuth.signInWithEmailAndPassword(
            request.login,
            request.password
        ).await().user
        val token = user?.getIdToken(false)?.await()
        return authenticationResponseConverter(user, token)
    }
}